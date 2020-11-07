package hr.danisoka.bulkmailer.app.controllers;

import hr.danisoka.bulkmailer.app.AppConstants;
import hr.danisoka.bulkmailer.app.db.AppDatabase;
import hr.danisoka.bulkmailer.app.db.DAOs.impl.SessionDaoImpl;
import hr.danisoka.bulkmailer.app.loggers.MailLoggerHandler;
import hr.danisoka.bulkmailer.app.models.RawSessionData;
import hr.danisoka.bulkmailer.app.models.Session;
import hr.danisoka.bulkmailer.app.utils.CsvUtils;
import hr.danisoka.bulkmailer.app.utils.FileUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import hr.danisoka.bulkmailer.app.contracts.SessionWinContract;

public class SessionDataController implements SessionWinContract.Controller {

    private SessionWinContract.View view;
    private MailLoggerHandler.LoggerErrorListener errorListener;
    
    public SessionDataController(SessionWinContract.View view) {
        this.view = view; 
    }
    
    public void setErrorListener(MailLoggerHandler.LoggerErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    @Override
    public List<String> fetchHeadersFromStudentsData(File file) {
        List<String> data = null;
        try {
            data = CsvUtils.getHeaderFromFile(file, ";");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SessionDataController.class.getName()).log(Level.SEVERE, null, ex);
            if(errorListener != null) {
                errorListener.onErrorOccurred(ex, String.format("Ne mogu pronaći datoteku: '%s'", file.getAbsolutePath()));
            }
        } catch (IOException ex) {
            Logger.getLogger(SessionDataController.class.getName()).log(Level.SEVERE, null, ex);
            if(errorListener != null) {
                errorListener.onErrorOccurred(ex, String.format("Ne mogu pročitati datoteku: '%s'", file.getAbsolutePath()));
            }
        } 
        return data;
    }

    @Override
    public void analyzeStudentData(File file) {
        try {
            List<String> headers = fetchHeadersFromStudentsData(file);
            List<List<String>> data = CsvUtils.getDataRowFromFile(file, ";", 0, 15);
            
            handleEmailColumn(headers);
            handleGroupColumn(data, headers);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SessionDataController.class.getName()).log(Level.SEVERE, null, ex);
            if(errorListener != null) {
                errorListener.onErrorOccurred(ex, String.format("Ne mogu pronaći datoteku: '%s'", file.getAbsolutePath()));
            }
        }catch (IOException ex) {
            Logger.getLogger(SessionDataController.class.getName()).log(Level.SEVERE, null, ex);
            if(errorListener != null) {
                errorListener.onErrorOccurred(ex, String.format("Ne mogu pročitati datoteku: '%s'", file.getAbsolutePath()));
            }
        }
    }
    
    private void handleEmailColumn(List<String> headers) {
        String[] possibleColumns = new String[]{"email", "e-mail", "mail", "pošta", "poruka", "primatelj", "posta"};
        int matchesCount = 0;
        String matchValue = null;
        for(String header : headers) {
            if(Arrays.asList(possibleColumns).contains(header.toLowerCase())) {
                matchesCount++;
                if(matchesCount > 1) {
                    break;
                }
                matchValue = header;
            }
        }
        view.updateEmailColumnCombobox(matchesCount == 1 ? matchValue : null);
    }
    
    private void handleGroupColumn(List<List<String>> data, List<String> headers) {
        Integer[] counterArray = new Integer[headers.size()];
        String[] lastValueBuffer = new String[headers.size()];
        
        int maxCount = 0;
        int maxCountIndex = -1;
        int internalCount = 0;
        
        for(int i = 0; i < counterArray.length; i++) counterArray[i] = 0;
        
        for(List<String> line : data) {
            for(int i = 0; i < line.size(); i++) {
                String item = line.get(i);
                if(lastValueBuffer[i] != null && lastValueBuffer[i].equals(item)) {
                    counterArray[i]++;
                    if(counterArray[i] > maxCount) {
                        maxCount = counterArray[i];
                        maxCountIndex = i;
                    }
                    internalCount = 0;
                    for(int j = 0; j < counterArray.length; j++) {
                        if(counterArray[j] == maxCount) {
                            internalCount++;
                        }
                        if(internalCount > 1) break;
                    }
                    if(internalCount > 1) break;
                } else {
                    lastValueBuffer[i] = item;
                    counterArray[i] = 1;
                }
            }
            if(maxCount > 2 || internalCount > 1) {
                break;
            }
        }
        String header = internalCount > 1 ? null : headers.get(maxCountIndex);
        view.updateGroupIndicator(header != null);
        view.updateGroupColumnCombobox(header);
    }

    @Override
    public void analyzeTemplate(File file) {
        try {
            String content = FileUtils.getFileContent(file);
            Pattern pattern = Pattern.compile("([^\\w\\s\\/\"])\\1", Pattern.MULTILINE);
            Matcher m  = pattern.matcher(content);
            
            String possibleHolder1 = "";
            int holder1_count = 0;
            String possibleHolder2 = "";
            int holder2_count = 0;
            
            int count = 0;
            while(m.find()) {
               String match = m.group(0);
               if(count < 2) {
                   if(count == 0) {
                       possibleHolder1 = match;
                   } else {
                       possibleHolder2 = match;
                   }
               } else {
                   if(count%2 == 0 && possibleHolder1.equals(match)) {
                       holder1_count++;
                   } else if(count%2 == 1 && possibleHolder2.equals(match)) {
                       holder2_count++;
                   }
               }
               count++;
               if(holder1_count > 2 && holder2_count > 2 && holder1_count == holder2_count) {
                   break;
               }
            }
            
            if(holder1_count > 2 && holder2_count > 2 && holder1_count == holder2_count) {
                view.updateHolderStart(possibleHolder1);
                view.updateHolderEnd(possibleHolder2);
            }
        } catch (IOException ex) {
            Logger.getLogger(SessionDataController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createOrUpdateSession(RawSessionData sessionData, Session session) {
        try {
            File dataDir = FileUtils.getDirectory(AppConstants.AppSettings.Folders.CSV_FOLDER);
            String newDataFileName = FileUtils.isNameEqual(sessionData.getDataFile(), sessionData.getDataFileName()) ? null : sessionData.getDataFileName();
            File dataFile = new File(dataDir, sessionData.getDataFile().getName());
            if(sessionData.isDataUploaded()|| (!sessionData.isDataUploaded() && (!dataFile.exists() || !FileUtils.isNameEqual(dataFile, sessionData.getDataFileName())))) {
                dataFile = FileUtils.storeFile(sessionData.getDataFile(), dataDir, newDataFileName);
            }
                        
            File templateDir = FileUtils.getDirectory(AppConstants.AppSettings.Folders.TEMPLATES_FOLDER);
            String newTemplateFileName = FileUtils.isNameEqual(sessionData.getTemplateFile(), sessionData.getTemplateFileName()) ? null : sessionData.getTemplateFileName();          
            File templateFile = new File(templateDir, sessionData.getTemplateFile().getName());
            if(sessionData.isTemplateUploaded()|| (!sessionData.isTemplateUploaded() && (!templateFile.exists() || !FileUtils.isNameEqual(templateFile, sessionData.getTemplateFileName())))) {
                templateFile = FileUtils.storeFile(sessionData.getTemplateFile(), templateDir, newTemplateFileName);
            }
            
            sessionData.setDataFile(dataFile, null);
            sessionData.setTemplateFile(templateFile, null);
            
            Session session = Session.convertFrom(sessionData);
            AppDatabase db = AppDatabase.getInstance();
            SessionDaoImpl dao = db.sessionDaoImpl;
            dao.create(session);
            view.sessionCreated(session);

        } catch (IOException ex) {
            Logger.getLogger(SessionDataController.class.getName()).log(Level.SEVERE, null, ex);
            if(errorListener != null) {
                errorListener.onErrorOccurred(ex, String.format("Ne mogu kreirati datoteku! %s", ex.getMessage()));
            }
        } catch (Exception ex) {
            Logger.getLogger(SessionDataController.class.getName()).log(Level.SEVERE, null, ex);
            if(errorListener != null) {
                errorListener.onErrorOccurred(ex, ex.getMessage());
            }
        }
    }
    
}

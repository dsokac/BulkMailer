package hr.danisoka.bulkmailer.app.controllers;

import hr.danisoka.bulkmailer.app.contracts.NewSessionWinContract;
import hr.danisoka.bulkmailer.app.loggers.MailLoggerHandler;
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

public class NewSessionController implements NewSessionWinContract.Controller {

    private NewSessionWinContract.View view;
    private MailLoggerHandler.LoggerErrorListener errorListener;
    
    public NewSessionController(NewSessionWinContract.View view, MailLoggerHandler.LoggerErrorListener errorListener) {
        this.view = view;
        this.errorListener = errorListener;
    }
    
    @Override
    public void storeStudentsData(File file, String newName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void storeTemplateData(File file, String newName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> fetchHeadersFromStudentsData(File file) {
        List<String> data = null;
        try {
            data = CsvUtils.getHeaderFromFile(file, ";");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NewSessionController.class.getName()).log(Level.SEVERE, null, ex);
            if(errorListener != null) {
                errorListener.onErrorOccurred(ex, String.format("Ne mogu pronaći datoteku: '%s'", file.getAbsolutePath()));
            }
        } catch (IOException ex) {
            Logger.getLogger(NewSessionController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(NewSessionController.class.getName()).log(Level.SEVERE, null, ex);
            if(errorListener != null) {
                errorListener.onErrorOccurred(ex, String.format("Ne mogu pronaći datoteku: '%s'", file.getAbsolutePath()));
            }
        }catch (IOException ex) {
            Logger.getLogger(NewSessionController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(NewSessionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

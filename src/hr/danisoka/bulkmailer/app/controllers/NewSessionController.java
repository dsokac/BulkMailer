package hr.danisoka.bulkmailer.app.controllers;

import hr.danisoka.bulkmailer.app.contracts.NewSessionWinContract;
import hr.danisoka.bulkmailer.app.utils.CsvUtils;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewSessionController implements NewSessionWinContract.Controller {

    private NewSessionWinContract.View view;
    
    public NewSessionController(NewSessionWinContract.View view) {
        this.view = view;
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
        } catch (IOException ex) {
            Logger.getLogger(NewSessionController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (IOException ex) {
            Logger.getLogger(NewSessionController.class.getName()).log(Level.SEVERE, null, ex);
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
    
}

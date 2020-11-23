package hr.danisoka.bulkmailer.app.strategies.impl;

import hr.danisoka.bulkmailer.app.listeners.ProgressListener;
import hr.danisoka.bulkmailer.app.models.Session;
import hr.danisoka.bulkmailer.app.models.session.MailRecipientData;
import hr.danisoka.bulkmailer.app.models.session.RecipientData;
import hr.danisoka.bulkmailer.app.strategies.BuildingMailDataInterface;
import hr.danisoka.bulkmailer.app.utils.CsvUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeamBuildingMailData implements BuildingMailDataInterface{
    
    private Session session;
    private List<String> emails;
    private ProgressListener progressListener;
    
    public TeamBuildingMailData(Session session, List<String> emails) {
        this.session = session;
        this.emails = emails;
    }
    
     public TeamBuildingMailData(Session session, List<String> emails, ProgressListener progressListener) {
        this.session = session;
        this.emails = emails;
        this.progressListener = progressListener;
    }

    @Override
    public List<MailRecipientData> buildMailData() throws FileNotFoundException, IOException {
        List<MailRecipientData> data = new ArrayList<>();
        File csvData = new File(session.getDataFilePath());
        List<String> headers = CsvUtils.getHeaderFromFile(csvData, ";");
        int indexOfGroupColumn = headers.indexOf(session.getGroupColumn());
        int indexOfEmailColumn = headers.indexOf(session.getEmailColumn());
        List<List<String>> rawData = CsvUtils.getDataAllRowFromFile(csvData, ";");
        List<String> wantedGroupValues = new ArrayList<>();
        if(emails != null) {
            if(progressListener != null) {
                progressListener.setProgressAction("Filtriranje podataka", rawData.size(), false);
            }
            int count = 0;
            for(List<String> row : rawData) {
                if(!wantedGroupValues.contains(row.get(indexOfGroupColumn)) && emails.contains(row.get(indexOfEmailColumn))) {
                    wantedGroupValues.add(row.get(indexOfGroupColumn));
                }
                if(progressListener != null) {
                    progressListener.updateProgress(++count);
                }
            }
        }
        
        if(progressListener != null) {
            progressListener.setProgressAction("Kreiranje timova...", rawData.size(), false);
        }
        int count = 0;
        for(List<String> row : rawData) {
            if((emails == null || (emails != null && emails.isEmpty())) || (emails !=  null && !emails.isEmpty() && wantedGroupValues.contains(row.get(indexOfGroupColumn)))) {
                MailRecipientData existing = MailRecipientData.findByGroupingValue(data, row.get(indexOfGroupColumn));
                if(existing == null) {
                    List<RecipientData> recipientData = new ArrayList<>();
                    recipientData.add(RecipientData.convertFrom(headers, row));
                    MailRecipientData mailData = new MailRecipientData(row.get(indexOfGroupColumn), recipientData);
                    data.add(mailData);
                } else {
                    List<RecipientData> recipientData = existing.getRecipientData();
                    recipientData.add(RecipientData.convertFrom(headers, row));
                }
            }
            if(progressListener != null) {
                progressListener.updateProgress(++count);
            }
        }
        return data;
    }

    @Override
    public void setProgressListener(ProgressListener progressListener) {
        this.progressListener = progressListener;
    }
    
}

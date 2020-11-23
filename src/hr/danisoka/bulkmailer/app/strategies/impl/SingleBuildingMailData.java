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

public class SingleBuildingMailData implements BuildingMailDataInterface {
    
    private Session session;
    private List<String> emails;
    private ProgressListener progressListener;
    
    public SingleBuildingMailData(Session session, List<String> emails) {
        this.session = session;
        this.emails = emails;
    }
    
    public SingleBuildingMailData(Session session, List<String> emails, ProgressListener progressListener) {
        this.session = session;
        this.emails = emails;
        this.progressListener = progressListener;
    }

    @Override
    public List<MailRecipientData> buildMailData() throws FileNotFoundException, IOException {
        List<MailRecipientData> data = new ArrayList<>();
        File csvData = new File(session.getDataFilePath());
        List<String> headers = CsvUtils.getHeaderFromFile(csvData, ";");
        List<List<String>> rawData = CsvUtils.getDataAllRowFromFile(csvData, ";");
        int indexEmailColumn = headers.indexOf(session.getEmailColumn());
        if(progressListener != null) {
            progressListener.setProgressAction("Pripremanje e-mailova...", rawData.size());
        }
        int count = 0;
        for(List<String> row : rawData) {
            if((emails == null || (emails != null && emails.isEmpty())) || (emails !=  null && !emails.isEmpty() && rawData.contains(row.get(indexEmailColumn)))) {
                List<RecipientData> recipientData = new ArrayList<>();
                recipientData.add(RecipientData.convertFrom(headers, row));
                MailRecipientData mailData = new MailRecipientData(recipientData);
                data.add(mailData);
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

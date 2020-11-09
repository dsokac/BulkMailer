package hr.danisoka.bulkmailer.app.strategies.impl;

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
    
    public TeamBuildingMailData(Session session, List<String> emails) {
        this.session = session;
        this.emails = emails;
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
        for(List<String> row : rawData) {
            if(!wantedGroupValues.contains(row.get(indexOfGroupColumn)) && emails.contains(row.get(indexOfEmailColumn))) {
                wantedGroupValues.add(row.get(indexOfGroupColumn));
            }
        }
        
        for(List<String> row : rawData) {
            if(wantedGroupValues.contains(row.get(indexOfGroupColumn))) {
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
        }
        return data;
    }
    
}

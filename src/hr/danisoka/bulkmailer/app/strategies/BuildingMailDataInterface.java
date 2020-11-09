package hr.danisoka.bulkmailer.app.strategies;

import hr.danisoka.bulkmailer.app.models.session.MailRecipientData;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface BuildingMailDataInterface {
    public List<MailRecipientData> buildMailData() throws FileNotFoundException, IOException ;
}

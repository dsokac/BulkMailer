package hr.danisoka.bulkmailer.app.contracts;

import hr.danisoka.bulkmailer.app.models.Session;
import hr.danisoka.bulkmailer.app.models.session.BulkEmailData;

public interface ExecuteSessionContract {
    interface View {
        void onBulkMailDataReady(BulkEmailData data, boolean forPreview);
    }
    
    interface Controller {
        void processSending(Session session, String mode, String specifiedEmails);
        void processPreviewing(Session session, String mode, String specifiedEmails);
    }
}

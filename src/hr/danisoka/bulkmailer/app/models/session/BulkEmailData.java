package hr.danisoka.bulkmailer.app.models.session;

import hr.danisoka.bulkmailer.app.mailers.MessageItem;
import hr.danisoka.bulkmailer.app.models.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.internet.AddressException;

public class BulkEmailData {
    private String subject;
    List<BulkEmailItem> emailItems = new ArrayList<>();
    
    public BulkEmailData() {}
    
    public BulkEmailData(String subject, List<BulkEmailItem> emailItems) {
        this.subject = subject;
        this.emailItems = emailItems;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<BulkEmailItem> getEmailItems() {
        return emailItems;
    }

    public void setEmailItems(List<BulkEmailItem> emailItems) {
        this.emailItems = emailItems;
    }
    
    public List<MessageItem> convertToMessageItems(Session session) {
        List<MessageItem> items = new ArrayList<>();
        for(BulkEmailItem emailItem : this.emailItems) {
            int countRecipients = 0;
            MessageItem messageItem = new MessageItem();
            try {
                Address[] recipients = emailItem.getRecipientData().getRecipientsArray(session.getEmailColumn());
                messageItem.setRecipients(recipients);
            } catch (AddressException ex) {
                Logger.getLogger(BulkEmailData.class.getName()).log(Level.SEVERE, null, ex);
            }
            messageItem.setContent(emailItem.getParsedContent());
            items.add(messageItem);
        }
        return items;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.subject);
        hash = 67 * hash + Objects.hashCode(this.emailItems);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BulkEmailData other = (BulkEmailData) obj;
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        if (!Objects.equals(this.emailItems, other.emailItems)) {
            return false;
        }
        return true;
    }
    
    
}

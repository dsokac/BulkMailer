package hr.danisoka.bulkmailer.app.models.session;

import java.util.Objects;

public class BulkEmailItem {
    private MailRecipientData recipientData;
    private String parsedContent;
    
    public BulkEmailItem() {}
    
    public BulkEmailItem(MailRecipientData recipientData, String parsedContent) {
        this.parsedContent = parsedContent;
        this.recipientData = recipientData;
    }

    public MailRecipientData getRecipientData() {
        return recipientData;
    }

    public void setRecipientData(MailRecipientData recipientData) {
        this.recipientData = recipientData;
    }

    public String getParsedContent() {
        return parsedContent;
    }

    public void setParsedContent(String parsedContent) {
        this.parsedContent = parsedContent;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.recipientData);
        hash = 61 * hash + Objects.hashCode(this.parsedContent);
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
        final BulkEmailItem other = (BulkEmailItem) obj;
        if (!Objects.equals(this.parsedContent, other.parsedContent)) {
            return false;
        }
        if (!Objects.equals(this.recipientData, other.recipientData)) {
            return false;
        }
        return true;
    }
    
    
}

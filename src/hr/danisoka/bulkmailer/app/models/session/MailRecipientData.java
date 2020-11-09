package hr.danisoka.bulkmailer.app.models.session;

import java.util.ArrayList;
import java.util.List;
import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class MailRecipientData {
    private String groupValue = null;
    private List<RecipientData> recipientData = new ArrayList<>();

    public MailRecipientData() {
    }
    
    public MailRecipientData(String groupValue, List<RecipientData> recipientData) {
        this.groupValue = groupValue;
        this.recipientData = recipientData;
    }
    
    public MailRecipientData(List<RecipientData> recipientData) {
        this.recipientData = recipientData;
    }

    public String getGroupValue() {
        return groupValue;
    }

    public void setGroupValue(String groupValue) {
        this.groupValue = groupValue;
    }

    public List<RecipientData> getRecipientData() {
        return recipientData;
    }

    public void setRecipientData(List<RecipientData> recipientData) {
        this.recipientData = recipientData;
    }
    public Address[] getRecipientsArray(String emailKey) throws AddressException {
        Address[] addresses = new Address[this.recipientData.size()];
        for(RecipientData item : recipientData) {
            Address a = new InternetAddress(item.<String>getValueAsType(emailKey));
            addresses[addresses.length] = a;
        }
        return addresses;
    }
    
    public static MailRecipientData findByGroupingValue(List<MailRecipientData> data, String groupValue) {
        MailRecipientData foundData = null;
        for(MailRecipientData item : data) {
            if(item.getGroupValue().equals(groupValue)) {
                foundData = item;
                break;
            }
        }
        return foundData;
    }
}

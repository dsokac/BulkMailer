/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.danisoka.bulkmailer.app.mailers;

import javax.mail.Address;

public class MessageItem {
    private String content;
    private Address[] recipients;
    
    public MessageItem() {}
    
    public MessageItem(String content, Address[] recipients) {
        this.content = content;
        this.recipients = recipients;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Address[] getRecipients() {
        return recipients;
    }

    public void setRecipients(Address[] recipients) {
        this.recipients = recipients;
    }
}

package hr.danisoka.bulkmailer.app.mailers;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public abstract class Mailer {

    protected String username = null;
    protected String password = null;
    protected String emailDomain = null;
    protected Boolean loginWithFullEmail = false;
    
    protected Properties properties = null;
    protected Session session = null;
    
    protected String fromAddress = null;
    protected List<Message> messages = new ArrayList<>();
    protected MailerListener listener = null;
    
    public Mailer(String username, String password) {        
        initialize(username, password);
    }
    
    public Mailer(String username, String password, MailerListener listener) {        
        initialize(username, password);
        this.listener = listener;
    }
    
    public void setListener(MailerListener listener) {
        this.listener = listener;
    }
    
    private void initialize(String username, String password) {
        this.username = username;
        this.password = password;
        prepare();
        setProperties();     
        this.fromAddress = this.loginWithFullEmail ? this.username : String.format("%s%s", this.username, this.emailDomain);
        if(!this.loginWithFullEmail) {
            this.username += this.emailDomain;
        }
        createSession();
        try {
            throw new Exception("Test 1");
        } catch (Exception ex) {
            Logger.getLogger(Mailer.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            if(listener != null) {
                listener.onErrorOccured(ex, ex.getMessage());
            }
        }
    }
    
    private void createSession() {
         this.session = Session.getInstance(this.properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }            
        });
    }
    
    private void createMessages(String subject, List<MessageItem> messageItems) {
        this.messages.clear();
        for(MessageItem item : messageItems) {
            Message m = createMessage(subject, item.getRecipients(), item.getContent());
            this.messages.add(m);
        }
    }
    
    private Message createMessage(String subject, Address[] recipients, String content) {
         Message message = null;
        try {
            message = new MimeMessage(this.session);
            message.setFrom(new InternetAddress(this.fromAddress));
            message.setRecipients(
              Message.RecipientType.TO, recipients);
            message.setSubject(subject);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(content, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);
        } catch (AddressException ex) {
            Logger.getLogger(Mailer.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            if(listener != null) {
                listener.onErrorOccured(ex, ex.getMessage());
            }
        } catch (MessagingException ex) {
            Logger.getLogger(Mailer.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            if(listener != null) {
                listener.onErrorOccured(ex, ex.getMessage());
            }
        }
        return message;
    }
    
    private void sendMessage(Transport transport, Message m) {
        try {
            transport.sendMessage(m, m.getAllRecipients());
        } catch (MessagingException ex) {
            Logger.getLogger(Mailer.class.getName()).log(Level.SEVERE, null, ex);
            if(listener != null) {
                listener.onErrorOccured(ex, ex.getMessage());
            }
        }
    }
    
    public void sendMessages() {
        Transport transport = null;
        try {
            transport = this.session.getTransport("smtp");
            transport.connect();
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(Mailer.class.getName()).log(Level.SEVERE, null, ex);
            if(listener != null) {
                listener.onErrorOccured(ex, ex.getMessage());
            }
        } catch (MessagingException ex) {
            Logger.getLogger(Mailer.class.getName()).log(Level.SEVERE, null, ex);
            if(listener != null) {
                listener.onErrorOccured(ex, ex.getMessage());
            }
        } finally {
            if(transport != null) {
                try {
                    transport.close();
                } catch (MessagingException ex) {
                    Logger.getLogger(Mailer.class.getName()).log(Level.SEVERE, null, ex);
                    if(listener != null) {
                        listener.onErrorOccured(ex, ex.getMessage());
                    }
                }
            }
        }
    }
    
    protected abstract void prepare();
    protected abstract void setProperties();

    public interface MailerListener {
        void onErrorOccured(Exception ex, String message);
    }
}

package hr.danisoka.bulkmailer.app.mailers;

import hr.danisoka.bulkmailer.app.listeners.ProgressListener;
import hr.danisoka.bulkmailer.app.models.attempts.AttemptJson;
import hr.danisoka.bulkmailer.app.models.attempts.AttemptRecipient;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
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
    
    protected Properties properties = null;
    protected Session session = null;
    
    protected String fromAddress = null;
    protected List<Message> messages = new ArrayList<>();
    protected MailerListener listener = null;
    
    protected AttemptJson attempt;
    protected ProgressListener progressListener;
    protected boolean stopExecution = false;
    
    public Mailer(String username, String password) {        
        initialize(username, password);
    }
    
    public Mailer(String username, String password, MailerListener listener) {        
        this.listener = listener;
        initialize(username, password);
    }
    
    public void setAttemptData(AttemptJson attempt) {
        this.attempt = attempt;
    }
    
    public void setProgressListener(ProgressListener progressListener) {
        this.progressListener = progressListener;
    }
    
    public void setListener(MailerListener listener) {
        this.listener = listener;
    }
    
    private void initialize(String username, String password) {
        this.username = username;
        this.password = password;
        prepare();
        setProperties();     
        this.fromAddress = this.username;
        createSession();       
    }
    
    private void createSession() {
         this.session = Session.getInstance(this.properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }            
        });
    }
    
    public void createMessages(String subject, List<MessageItem> messageItems) {
        this.messages.clear();
        if(progressListener != null) {
            progressListener.setProgressAction("Kreiranje poruka", messageItems.size(), true);
        }
        int count = 0;
        for(MessageItem item : messageItems) {
            Message m = createMessage(subject, item.getRecipients(), item.getContent());
            this.messages.add(m);
            if(progressListener != null) {
                progressListener.updateProgress(++count);
            }
            if(stopExecution) {
                break;
            }
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
            message.addHeader(content, subject);
            message.setHeader("Content-Type", "text/html; charset=UTF-8");

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setHeader("Content-Type","text/plain; charset=\"utf-8\""); 
            mimeBodyPart.setContent( content, "text/html; charset=utf-8" ); 
            mimeBodyPart.setHeader("Content-Transfer-Encoding", "quoted-printable");
            
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);
        } catch (AddressException ex) {
            Logger.getLogger(Mailer.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            attempt.setStatus("Neuspješno kreiranje poruka.");
            attempt.setStatusMessage(ex.toString());
            stopExecution = true;
            if(listener != null) {
                listener.onErrorOccured(ex, ex.toString());
            }
        } catch (MessagingException ex) {
            attempt.getStatistics().incrementFailedItem();
            for(int i = 0; i < recipients.length; i++) {
                Address recipient = recipients[i];
                if(!attempt.hasRecord(recipient.toString())){
                    AttemptRecipient r = new AttemptRecipient(recipient.toString(), "Kreiranje neuspješno", ex.getMessage());
                    attempt.addRecipient(r);
                }
            }
            Logger.getLogger(Mailer.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            stopExecution = true;
            if(listener != null) {
                listener.onErrorOccured(ex, ex.toString());
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
                listener.onErrorOccured(ex, ex.toString());
            }
        }
    }
    
    public void sendMessages() {
        Transport transport = null;
        if(progressListener != null) {
            progressListener.setProgressAction("Slanje poruka", messages.size(), true);
        }
        int count = 0;
        try {
            transport = this.session.getTransport("smtp");
            transport.connect();         
            for(Message m : messages) {
                sendMessage(transport, m);
                attempt.getStatistics().incrementProcessedItem();
                for(int i = 0; i < m.getAllRecipients().length; i++) {
                    Address recipient = m.getAllRecipients()[i];
                    if(!attempt.hasRecord(recipient.toString())){
                        AttemptRecipient r = new AttemptRecipient(recipient.toString(), "Slanje uspješno", "Poruka poslana.");
                        attempt.addRecipient(r);
                    }
                }
                try {
                    Thread.sleep(getSleepValue() * 1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Mailer.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(progressListener != null) {
                    progressListener.updateProgress(++count);
                }
            }
        } catch (AuthenticationFailedException ex) {
            Logger.getLogger(Mailer.class.getName()).log(Level.SEVERE, null, ex);
            attempt.setStatus("Neuspješno");
            attempt.setStatusMessage(ex.toString());
            if(listener != null) {
                listener.onErrorOccured(ex, ex.toString());
            }
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(Mailer.class.getName()).log(Level.SEVERE, null, ex);
            attempt.setStatus("Neuspješno");
            attempt.setStatusMessage(ex.toString());
            if(listener != null) {
                listener.onErrorOccured(ex, ex.toString());
            }
        }  catch (MessagingException ex) {
            Logger.getLogger(Mailer.class.getName()).log(Level.SEVERE, null, ex);
            attempt.getStatistics().incrementFailedItem();
            try {
                for(int i = 0; i < messages.get(count-1).getAllRecipients().length; i++) {
                    Address recipient = messages.get(count-1).getAllRecipients()[i];
                    if(!attempt.hasRecord(recipient.toString())){
                        AttemptRecipient r = new AttemptRecipient(recipient.toString(), "Slanje neuspješno", ex.getMessage());
                        attempt.addRecipient(r);
                    }
                }
            } catch (MessagingException ex1) {
                Logger.getLogger(Mailer.class.getName()).log(Level.SEVERE, null, ex1);
            }
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
                        listener.onErrorOccured(ex, ex.toString());
                    }
                }
            }
        }
    }
    
    protected abstract void prepare();
    protected abstract void setProperties();
    protected abstract int getSleepValue();

    public interface MailerListener {
        void onErrorOccured(Exception ex, String message);
    }
}

package hr.danisoka.bulkmailer.app.mailers;

import hr.danisoka.bulkmailer.app.listeners.ProgressListener;
import java.io.UnsupportedEncodingException;
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
    
    protected Properties properties = null;
    protected Session session = null;
    
    protected String fromAddress = null;
    protected List<Message> messages = new ArrayList<>();
    protected MailerListener listener = null;
    
    protected ProgressListener progressListener;
    
    public Mailer(String username, String password) {        
        initialize(username, password);
    }
    
    public Mailer(String username, String password, MailerListener listener) {        
        this.listener = listener;
        initialize(username, password);
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
            //mimeBodyPart.setContent(new String(content.getBytes("UTF8"),"ISO-8859-1"), "text/html");
            //mimeBodyPart.setText(content, "utf-8", "text/html");
            mimeBodyPart.setHeader("Content-Type","text/plain; charset=\"utf-8\""); 
            mimeBodyPart.setContent( content, "text/html; charset=utf-8" ); 
            mimeBodyPart.setHeader("Content-Transfer-Encoding", "quoted-printable");
            
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
        } /*catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Mailer.class.getName()).log(Level.SEVERE, null, ex);
            if(listener != null) {
                listener.onErrorOccured(ex, ex.getMessage());
            }
        }*/
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
        if(progressListener != null) {
            progressListener.setProgressAction("Slanje poruka", messages.size(), true);
        }
        try {
            transport = this.session.getTransport("smtp");
            transport.connect();
            int count = 0;
            for(Message m : messages) {
                sendMessage(transport, m);
                try {
                    Thread.sleep(getSleepValue() * 1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Mailer.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(progressListener != null) {
                    progressListener.updateProgress(++count);
                }
            }
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
    protected abstract int getSleepValue();

    public interface MailerListener {
        void onErrorOccured(Exception ex, String message);
    }
}

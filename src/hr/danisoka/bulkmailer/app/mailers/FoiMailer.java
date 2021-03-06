package hr.danisoka.bulkmailer.app.mailers;

import java.util.Properties;

public class FoiMailer extends Mailer{
    
    public FoiMailer(String username, String password){
        super(username, password);
    }
    
    public FoiMailer(String username, String password, MailerListener listener){
        super(username, password, listener);
    }

    @Override
    protected int getSleepValue() {
        return 5;
    }

    @Override
    protected void prepare() {
     
    }   

    @Override
    protected void setProperties() {
        this.properties = new Properties();
        this.properties.put("mail.smtp.auth", true);
        this.properties.put("mail.smtp.starttls.enable", true);        
        this.properties.put("mail.smtp.host", "barok.foi.hr");
        this.properties.put("mail.smtp.port", 25);
    }
    
    
    
}

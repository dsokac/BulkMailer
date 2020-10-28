package hr.danisoka.bulkmailer.app;

import hr.danisoka.bulkmailer.app.loggers.MailLoggerHandler;
import hr.danisoka.bulkmailer.app.mailers.FoiMailer;
import hr.danisoka.bulkmailer.app.mailers.Mailer;
import hr.danisoka.bulkmailer.app.views.windows.MainWindow;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

public class Main {

    public static void main(String[] args) {
        //new MainWindow().setVisible(true);
        MailLoggerHandler.setFileHandler(new MailLoggerHandler.LoggerErrorListener() {
            @Override
            public void onErrorOccurred(Exception ex, String message) {
                System.out.println(".onErrorOccurred() [MailLogger] ---> " + message);                    
            }
        });
        Mailer mailer = new FoiMailer("dsokac", "#9IEisM6yyc9", new Mailer.MailerListener() {
            @Override
            public void onErrorOccured(Exception ex, String message) {
                System.out.println(".onErrorOccurred() [MailerListener] ---> " + message);               
            }
        });
        
    }
    
    
    
}

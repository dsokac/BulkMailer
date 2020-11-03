package hr.danisoka.bulkmailer.app;

import hr.danisoka.bulkmailer.app.db.AppDatabase;
import hr.danisoka.bulkmailer.app.db.DAOs.impl.AttemptDaoImpl;
import hr.danisoka.bulkmailer.app.db.DAOs.impl.HolderMappingDaoImpl;
import hr.danisoka.bulkmailer.app.db.DAOs.impl.SessionDaoImpl;
import hr.danisoka.bulkmailer.app.loggers.MailLoggerHandler;
import hr.danisoka.bulkmailer.app.mailers.FoiMailer;
import hr.danisoka.bulkmailer.app.mailers.Mailer;
import hr.danisoka.bulkmailer.app.models.Attempt;
import hr.danisoka.bulkmailer.app.models.HolderMapping;
import hr.danisoka.bulkmailer.app.models.Session;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        AppDatabase db = AppDatabase.getInstance();
        
        SessionDaoImpl impl = db.sessionDaoImpl;
        HolderMappingDaoImpl hmImpl = db.holderMappingDaoImpl;
        AttemptDaoImpl aImpl = db.attemptDaoImpl;
        
        Session session = new Session();
        session.setDataFilePath("test data file path");
        session.setCreatedAt(new Date());
        session.setEmailColumn("email");
        session.setGroupColumn("group");
        session.setHasGroup(true);
        session.setHolderStart("[[");
        session.setHolderEnd("]]");
        session.setTemplateFilePath("test template file path");
        try {
            impl.create(session);
            
            HolderMapping map1 = new HolderMapping("key1", "key1", session);
            HolderMapping map2 = new HolderMapping("key2", "key2", session);
            HolderMapping map3 = new HolderMapping("key3", "key3", session);
            
            hmImpl.create(map1);
            hmImpl.create(map2);
            hmImpl.create(map3);
            
            session = impl.queryForSameId(session);
            System.out.println("hr.danisoka.bulkmailer.app.Main.main()");
            
            Attempt att1 = new Attempt("report file path", session);
            Attempt att2 = new Attempt("report file path", session);
            Attempt att3 = new Attempt("report file path", session);
            Attempt att4 = new Attempt("report file path", session);
            
            aImpl.create(att1);
            aImpl.create(att2);
            aImpl.create(att3);
            aImpl.create(att4);
            
            session = impl.queryForSameId(session);
            System.out.println("hr.danisoka.bulkmailer.app.Main.main()");
            
            List<Session> sessions = impl.queryForAll();
            System.out.println("hr.danisoka.bulkmailer.app.Main.main()");
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        db.closeConnection();
    }
    
    
    
}

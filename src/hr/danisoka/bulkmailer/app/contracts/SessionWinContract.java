package hr.danisoka.bulkmailer.app.contracts;

import hr.danisoka.bulkmailer.app.models.RawSessionData;
import hr.danisoka.bulkmailer.app.models.Session;
import java.io.File;
import java.util.List;

public interface SessionWinContract {
    interface Controller {
        List<String> fetchHeadersFromStudentsData(File file);
        void analyzeStudentData(File file);
        void analyzeTemplate(File file);
        void createSession(RawSessionData sessionData);
    }
    
    interface View {
        void updateEmailColumnCombobox(String selectedValue);
        void updateGroupColumnCombobox(String selectedValue);
        void updateGroupIndicator(boolean value);
        void updateHolderStart(String value);
        void updateHolderEnd(String value);
        void sessionCreated(Session session);
    }
}

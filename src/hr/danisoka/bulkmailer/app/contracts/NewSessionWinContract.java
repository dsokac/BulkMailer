package hr.danisoka.bulkmailer.app.contracts;

import java.io.File;
import java.util.List;

public interface NewSessionWinContract {
    interface Controller {
        void storeStudentsData(File file, String newName);
        void storeTemplateData(File file, String newName);
        List<String> fetchHeadersFromStudentsData(File file);
        void analyzeStudentData(File file);
        void analyzeTemplate(File file);
    }
    
    interface View {
        void updateEmailColumnCombobox(String selectedValue);
        void updateGroupColumnCombobox(String selectedValue);
        void updateGroupIndicator(boolean value);
        void updateHolderStart(String value);
        void updateHolderEnd(String value);
    }
}

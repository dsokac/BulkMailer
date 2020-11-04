package hr.danisoka.bulkmailer.app.contracts;

public interface CredentialsWinContract {
    interface Controller {
        void storeCredentials(String username, String password);
    }
    
    interface View {
        void showFormErrorMessage(String message);
        void credentialsStored();
    }
}

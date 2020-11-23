package hr.danisoka.bulkmailer.app.controllers;

import hr.danisoka.bulkmailer.app.BulkMailerApplication;
import hr.danisoka.bulkmailer.app.contracts.CredentialsWinContract;

public class CredentialsController implements CredentialsWinContract.Controller{

    private CredentialsWinContract.View view;
    private BulkMailerApplication app = BulkMailerApplication.getInstance();
    
    public CredentialsController(CredentialsWinContract.View view) {
        this.view = view;
    }
    
    @Override
    public void storeCredentials(String username, String password) {
        if(username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
            if(!username.contains("@")) {
                view.showFormErrorMessage("Korisničko ime mora sadržavati domenu (@foi.unizg.hr).");
            } else {
                app.setUsername(username);
                app.setPassword(password);
                view.credentialsStored();
            }
        } else {
            view.showFormErrorMessage("Korisničko ime i lozinka su obavezna polja.");
        }
    }
    
}

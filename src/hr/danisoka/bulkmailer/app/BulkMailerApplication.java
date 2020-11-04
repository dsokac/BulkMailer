package hr.danisoka.bulkmailer.app;

public class BulkMailerApplication {

    private static BulkMailerApplication INSTANCE = null;
    
    private String username;
    private String password;
    
    private BulkMailerApplication() {}
    
    public static BulkMailerApplication getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new BulkMailerApplication();
        }
        return INSTANCE;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean areCredentialsPresent() {
        boolean outcome = this.username != null && this.password != null && !this.username.isEmpty() && !this.password.isEmpty();
        return outcome;
    }
    
}

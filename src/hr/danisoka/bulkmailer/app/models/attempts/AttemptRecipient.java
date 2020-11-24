package hr.danisoka.bulkmailer.app.models.attempts;

import java.util.Objects;

public class AttemptRecipient {
    private String email;
    private String status;
    private String statusMessage;

    public AttemptRecipient(String email, String status, String statusMessage) {
        this.email = email;
        this.status = status;
        this.statusMessage = statusMessage;
    }

    public AttemptRecipient() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.email);
        hash = 41 * hash + Objects.hashCode(this.status);
        hash = 41 * hash + Objects.hashCode(this.statusMessage);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AttemptRecipient other = (AttemptRecipient) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.statusMessage, other.statusMessage)) {
            return false;
        }
        return true;
    }
    
    
}

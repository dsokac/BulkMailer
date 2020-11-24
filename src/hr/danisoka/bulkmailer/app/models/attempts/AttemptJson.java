package hr.danisoka.bulkmailer.app.models.attempts;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AttemptJson {
    
    private String sessionName;
    private Date createdAt;
    private Date completedAt;
    private BigDecimal durationInSeconds;
    private String status;
    private String statusMessage;
    private AttemptStats statistics = new AttemptStats();
    private List<AttemptRecipient> recipients;

    public AttemptJson() {
        createdAt = new Date();
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
        long timeInSecond = (this.completedAt.getTime() - this.createdAt.getTime())/1000;
        this.durationInSeconds = new BigDecimal(timeInSecond);
    }

    public BigDecimal getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(BigDecimal durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
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

    public AttemptStats getStatistics() {
        return statistics;
    }

    public void setStatistics(AttemptStats statistics) {
        this.statistics = statistics;
    }

    public List<AttemptRecipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<AttemptRecipient> recipients) {
        this.recipients = recipients;
    }
    
    public void addRecipient(AttemptRecipient recipient) {
        if(this.recipients == null) {
            this.recipients = new ArrayList<>();
        }
        this.recipients.add(recipient);
    }
    
    public boolean hasRecord(String email) {
        boolean found = false;
        if(recipients != null) {
            for(AttemptRecipient item : recipients) {
                if(item.getEmail().equals(email)) {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.sessionName);
        hash = 79 * hash + Objects.hashCode(this.createdAt);
        hash = 79 * hash + Objects.hashCode(this.completedAt);
        hash = 79 * hash + Objects.hashCode(this.durationInSeconds);
        hash = 79 * hash + Objects.hashCode(this.status);
        hash = 79 * hash + Objects.hashCode(this.statusMessage);
        hash = 79 * hash + Objects.hashCode(this.statistics);
        hash = 79 * hash + Objects.hashCode(this.recipients);
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
        final AttemptJson other = (AttemptJson) obj;
        if (!Objects.equals(this.sessionName, other.sessionName)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.statusMessage, other.statusMessage)) {
            return false;
        }
        if (!Objects.equals(this.createdAt, other.createdAt)) {
            return false;
        }
        if (!Objects.equals(this.completedAt, other.completedAt)) {
            return false;
        }
        if (!Objects.equals(this.durationInSeconds, other.durationInSeconds)) {
            return false;
        }
        if (!Objects.equals(this.statistics, other.statistics)) {
            return false;
        }
        if (!Objects.equals(this.recipients, other.recipients)) {
            return false;
        }
        return true;
    }
    
    
}

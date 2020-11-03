package hr.danisoka.bulkmailer.app.models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import hr.danisoka.bulkmailer.app.db.DAOs.impl.AttemptDaoImpl;
import java.util.Date;
import java.util.Objects;

@DatabaseTable(tableName = "attempts", daoClass = AttemptDaoImpl.class)
public class Attempt {
    
    @DatabaseField(columnName = "attempt_id", generatedId = true)
    private long id;
    
    @DatabaseField(columnName = "created_at", dataType = DataType.DATE_STRING, format = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    
    @DatabaseField(columnName = "report_file_path")
    private String reportFilePath;
    
    @DatabaseField(columnName = "session", foreign = true, foreignAutoRefresh = true)
    private Session session;

    public Attempt() {}
    
    public Attempt(String reportFilePath, Session session) {
        this.reportFilePath = reportFilePath;
        this.session = session;
        this.createdAt = new Date();
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getReportFilePath() {
        return reportFilePath;
    }

    public void setReportFilePath(String reportFilePath) {
        this.reportFilePath = reportFilePath;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 89 * hash + Objects.hashCode(this.createdAt);
        hash = 89 * hash + Objects.hashCode(this.reportFilePath);
        hash = 89 * hash + Objects.hashCode(this.session);
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
        final Attempt other = (Attempt) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.reportFilePath, other.reportFilePath)) {
            return false;
        }
        if (!Objects.equals(this.createdAt, other.createdAt)) {
            return false;
        }
        if (!Objects.equals(this.session, other.session)) {
            return false;
        }
        return true;
    }
    
    
    
}

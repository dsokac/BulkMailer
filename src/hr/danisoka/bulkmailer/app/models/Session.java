package hr.danisoka.bulkmailer.app.models;

import com.j256.ormlite.dao.BaseForeignCollection;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.dao.LazyForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import hr.danisoka.bulkmailer.app.db.DAOs.impl.SessionDaoImpl;
import java.util.Date;
import java.util.Objects;

@DatabaseTable(tableName = "sessions", daoClass = SessionDaoImpl.class)
public class Session {
    
    @DatabaseField(columnName = "session_id", generatedId = true)
    private long id;
    
    @DatabaseField(columnName = "name")
    private String name;
    
    @DatabaseField(columnName = "created_at", dataType = DataType.DATE_STRING, format = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    
    @DatabaseField(columnName = "data_file_path")
    private String dataFilePath;
    
    @DatabaseField(columnName = "template_file_path")
    private String templateFilePath;
    
    @DatabaseField(columnName = "holder_start", width = 5)
    private String holderStart;
    
    @DatabaseField(columnName = "holder_end", width = 5)
    private String holderEnd;
    
    @DatabaseField(columnName = "email_column", width = 50)
    private String emailColumn;
    
    @DatabaseField(columnName = "has_group", columnDefinition = "TINYINT")
    private int hasGroup;
    
    @DatabaseField(columnName = "group_column", width = 50)
    private String groupColumn; 
    
    @ForeignCollectionField
    private ForeignCollection<HolderMapping> holderMappings;
    
    @ForeignCollectionField
    private ForeignCollection<Attempt> attempts;
    
    public Session() {
        setCreatedAt(new Date());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDataFilePath() {
        return dataFilePath;
    }

    public void setDataFilePath(String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }

    public String getTemplateFilePath() {
        return templateFilePath;
    }

    public void setTemplateFilePath(String templateFilePath) {
        this.templateFilePath = templateFilePath;
    }

    public String getHolderStart() {
        return holderStart;
    }

    public void setHolderStart(String holderStart) {
        this.holderStart = holderStart;
    }

    public String getHolderEnd() {
        return holderEnd;
    }

    public void setHolderEnd(String holderEnd) {
        this.holderEnd = holderEnd;
    }

    public String getEmailColumn() {
        return emailColumn;
    }

    public void setEmailColumn(String emailColumn) {
        this.emailColumn = emailColumn;
    }

    public boolean hasGroup() {
        return hasGroup == 1;
    }

    public void setHasGroup(boolean hasGroup) {
        this.hasGroup = hasGroup ? 1 : 0;
    }

    public String getGroupColumn() {
        return groupColumn;
    }

    public void setGroupColumn(String groupColumn) {
        this.groupColumn = groupColumn;
    }

    public ForeignCollection<HolderMapping> getHolderMappings() {
        return holderMappings;
    }

    public void setHolderMappings(ForeignCollection<HolderMapping> holderMappings) {
        this.holderMappings = holderMappings;
    }

    public int getHasGroup() {
        return hasGroup;
    }

    public void setHasGroup(int hasGroup) {
        this.hasGroup = hasGroup;
    }  

    public ForeignCollection<Attempt> getAttempts() {
        return attempts;
    }

    public void setAttempts(ForeignCollection<Attempt> attempts) {
        this.attempts = attempts;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.createdAt);
        hash = 83 * hash + Objects.hashCode(this.dataFilePath);
        hash = 83 * hash + Objects.hashCode(this.templateFilePath);
        hash = 83 * hash + Objects.hashCode(this.holderStart);
        hash = 83 * hash + Objects.hashCode(this.holderEnd);
        hash = 83 * hash + Objects.hashCode(this.emailColumn);
        hash = 83 * hash + this.hasGroup;
        hash = 83 * hash + Objects.hashCode(this.groupColumn);
        hash = 83 * hash + Objects.hashCode(this.holderMappings);
        hash = 83 * hash + Objects.hashCode(this.attempts);
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
        final Session other = (Session) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.hasGroup != other.hasGroup) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.dataFilePath, other.dataFilePath)) {
            return false;
        }
        if (!Objects.equals(this.templateFilePath, other.templateFilePath)) {
            return false;
        }
        if (!Objects.equals(this.holderStart, other.holderStart)) {
            return false;
        }
        if (!Objects.equals(this.holderEnd, other.holderEnd)) {
            return false;
        }
        if (!Objects.equals(this.emailColumn, other.emailColumn)) {
            return false;
        }
        if (!Objects.equals(this.groupColumn, other.groupColumn)) {
            return false;
        }
        if (!Objects.equals(this.createdAt, other.createdAt)) {
            return false;
        }
        if (!Objects.equals(this.holderMappings, other.holderMappings)) {
            return false;
        }
        if (!Objects.equals(this.attempts, other.attempts)) {
            return false;
        }
        return true;
    }

    public static Session convertFrom(RawSessionData sessionData) {
        Session session = new Session();
        session.setName(sessionData.getName());
        session.setDataFilePath(sessionData.getDataFile().getAbsolutePath());
        session.setEmailColumn(sessionData.getEmailColumn());
        session.setHasGroup(sessionData.isGrouped());
        session.setGroupColumn(sessionData.getGroupColumn());
        session.setTemplateFilePath(sessionData.getTemplateFile().getAbsolutePath());
        session.setHolderStart(sessionData.getHolderStart());
        session.setHolderEnd(sessionData.getHolderEnd());
        return session;
    }   
    
}

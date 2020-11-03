package hr.danisoka.bulkmailer.app.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import hr.danisoka.bulkmailer.app.db.DAOs.impl.HolderMappingDaoImpl;
import java.util.Objects;

@DatabaseTable(tableName = "holder_mappings", daoClass = HolderMappingDaoImpl.class)
public class HolderMapping {
    
    @DatabaseField(columnName = "mapping_id", generatedId = true)
    private long id;
    
    @DatabaseField(columnName = "holder_key", width = 50)
    private String holderKey;
    
    @DatabaseField(columnName = "column_key", width = 50)
    private String columnKey;
    
    @DatabaseField(columnName = "session", foreign = true, foreignAutoRefresh = true)
    private Session session;

    public HolderMapping() {}
    
    public HolderMapping(String holderKey, String columnKey, Session session) {
        this.holderKey = holderKey;
        this.columnKey = columnKey;
        this.session = session;       
    }
         
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHolderKey() {
        return holderKey;
    }

    public void setHolderKey(String holderKey) {
        this.holderKey = holderKey;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 79 * hash + Objects.hashCode(this.holderKey);
        hash = 79 * hash + Objects.hashCode(this.columnKey);
        hash = 79 * hash + Objects.hashCode(this.session);
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
        final HolderMapping other = (HolderMapping) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.holderKey, other.holderKey)) {
            return false;
        }
        if (!Objects.equals(this.columnKey, other.columnKey)) {
            return false;
        }
        if (!Objects.equals(this.session, other.session)) {
            return false;
        }
        return true;
    }
 
    
    
}

package hr.danisoka.bulkmailer.app.db.DAOs.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import hr.danisoka.bulkmailer.app.db.DAOs.HolderMappingDao;
import hr.danisoka.bulkmailer.app.models.HolderMapping;
import java.sql.SQLException;

public class HolderMappingDaoImpl extends BaseDaoImpl<HolderMapping, Long> implements HolderMappingDao {
    
    public HolderMappingDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, HolderMapping.class);
    }
    
}

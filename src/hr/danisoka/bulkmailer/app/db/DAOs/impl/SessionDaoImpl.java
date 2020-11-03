package hr.danisoka.bulkmailer.app.db.DAOs.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import hr.danisoka.bulkmailer.app.db.DAOs.SessionDao;
import hr.danisoka.bulkmailer.app.models.Session;
import java.sql.SQLException;

public class SessionDaoImpl extends BaseDaoImpl<Session, Long> implements SessionDao{
    
    public SessionDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Session.class);
    }
    
}

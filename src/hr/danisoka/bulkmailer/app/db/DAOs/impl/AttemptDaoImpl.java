package hr.danisoka.bulkmailer.app.db.DAOs.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import hr.danisoka.bulkmailer.app.db.DAOs.AttemptDao;
import hr.danisoka.bulkmailer.app.models.Attempt;
import java.sql.SQLException;

public class AttemptDaoImpl extends BaseDaoImpl<Attempt, Long> implements AttemptDao {

    public AttemptDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Attempt.class);
    }
    
}

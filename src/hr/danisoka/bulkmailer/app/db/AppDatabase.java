/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.danisoka.bulkmailer.app.db;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import hr.danisoka.bulkmailer.app.AppConstants;
import hr.danisoka.bulkmailer.app.db.DAOs.impl.AttemptDaoImpl;
import hr.danisoka.bulkmailer.app.db.DAOs.impl.HolderMappingDaoImpl;
import hr.danisoka.bulkmailer.app.db.DAOs.impl.SessionDaoImpl;
import hr.danisoka.bulkmailer.app.models.Attempt;
import hr.danisoka.bulkmailer.app.models.HolderMapping;
import hr.danisoka.bulkmailer.app.models.Session;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Danijel
 */
public class AppDatabase {
    private static AppDatabase INSTANCE = null;
    
    public SessionDaoImpl sessionDaoImpl;
    public AttemptDaoImpl attemptDaoImpl;
    public HolderMappingDaoImpl holderMappingDaoImpl;
    
    private ConnectionSource connectionSource = null;
    
    private AppDatabase() {
        JdbcPooledConnectionSource connectionSource  = null;
        try {
            File dbDir = new File(AppConstants.AppSettings.DB_FOLDER);
            dbDir.mkdirs();
            
            File dbFile = new File(AppConstants.AppSettings.DB_FILE);
            if(!dbFile.exists()) {
                dbFile.createNewFile();
            }
            System.out.println(AppConstants.AppSettings.DB_FOLDER);
            System.out.println(AppConstants.AppSettings.DB_FILE);
            connectionSource = new JdbcPooledConnectionSource("jdbc:sqlite:" + AppConstants.AppSettings.DB_FILE);
            connectionSource.setMaxConnectionAgeMillis(5 * 60 * 1000); //5 minutes
            connectionSource.setCheckConnectionsEveryMillis(60 * 1000);
            connectionSource.setTestBeforeGet(true);
            
            this.connectionSource = connectionSource;
            
            sessionDaoImpl = new SessionDaoImpl(connectionSource);
            attemptDaoImpl = new AttemptDaoImpl(connectionSource);
            holderMappingDaoImpl = new HolderMappingDaoImpl(connectionSource);
            
            DaoManager.registerDao(connectionSource, sessionDaoImpl);
            DaoManager.registerDao(connectionSource, attemptDaoImpl);
            DaoManager.registerDao(connectionSource, holderMappingDaoImpl);
            
            TableUtils.createTableIfNotExists(connectionSource, Session.class);
            TableUtils.createTableIfNotExists(connectionSource, Attempt.class);
            TableUtils.createTableIfNotExists(connectionSource, HolderMapping.class);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(AppDatabase.class.getName()).log(Level.SEVERE, null, ex);
            closeConnection();
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(AppDatabase.class.getName()).log(Level.SEVERE, null, ex);
            closeConnection();
        } 
    }
    
    public static AppDatabase getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new AppDatabase();
        }
        return INSTANCE;
    }
    
    public void closeConnection() {
        if(connectionSource != null) {
            try {
                connectionSource.close();
            } catch (IOException ex) {
                Logger.getLogger(AppDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

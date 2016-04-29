/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.locationservice.database;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * second version of database connection class. In this class connection pooling
 * is used to improve the performance of the application
 *
 * @author Bahram Moradi
 */
public class DBConnectionPool {

    private static volatile DBConnectionPool instance = null;
    private BoneCP connectionPool = null;
    public static DBConnectionPool getInstance() {
        if (instance == null) {
            instance = new DBConnectionPool();
        }
        return instance;
    }

    private DBConnectionPool() {
        try {

            DatabaseProperty property = new DatabaseProperty();
            Class.forName(property.getDriver());
            BoneCPConfig config = new BoneCPConfig();
            config.setJdbcUrl(property.getUrl());
            config.setUsername(property.getUser());
            config.setPassword(property.getPassword());
            config.setMinConnectionsPerPartition(5);
            config.setMaxConnectionsPerPartition(50);
            config.setPartitionCount(1);
            connectionPool = new BoneCP(config);
        } catch (Exception e) {
            Logger.getLogger(DBConnectionPool.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    public synchronized Connection getConnection() throws SQLException {

        return connectionPool.getConnection();
    }

    /*Three birds one stone*/
    public static synchronized void close(Connection connection, PreparedStatement statement, ResultSet set) {

        try {
            
			if (set != null) {
                set.close();
            }
            if (statement != null) {
                statement.close();
            }
			if (connection != null) {
                connection.close();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

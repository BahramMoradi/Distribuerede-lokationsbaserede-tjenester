/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.locationservice.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.postgis.*;

/**
 * Fisr version of database connection class without connection pooling.
 * @author Bahram Moardi
 */
public class DatabaseConnector {
    private static DatabaseProperty property=null;
    private static DatabaseConnector instance=null;    
    private DatabaseConnector(){}
    public static synchronized DatabaseConnector getInstance(){
            if (instance==null){
                instance=new DatabaseConnector();
                property=new DatabaseProperty();
                
            }
       
        return instance;
    }
    /**
     * This method is for connectiong to database.
     * @return Connection 
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public synchronized Connection getConnection() throws ClassNotFoundException, SQLException{
      Class.forName(property.getDriver());
      Connection connection = DriverManager.getConnection(property.getUrl(),property.getUser(),property.getPassword());
     ((org.postgresql.PGConnection)connection).addDataType("geometry",Class.forName("org.postgis.PGgeometry"));
        
      return connection;
    }
    
}


package dk.dtu.locationservice.database;

import dk.dtu.locationservice.dao.LocationDaoImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Bahram Moradi
 * This class is for reading database properties.
 * The "config.preoperties" must be in the root of user folder C:\\Users\you_user_account\config.properties
 * This class is onlu used in this package therefor it has not modifier (package private)
 */
class DatabaseProperty {
   
    private String url= null;       //"jdbc:postgresql://127.0.0.1:5432/trackerapp";
    private String host=null;       //"127.0.0.1";
    private String port=null;       //"5432";
    private String database=null;   //"trackerapp";
    private String user=null;       //"admin";
    private String password=null;   //"42227786";
    private String driver=null;     //"org.postgresql.Driver";
    public DatabaseProperty(){
        readProperty();
    }
    private void readProperty(){
       String configPath=System.getProperty("user.home")+File.separator+"config.properties";
       Properties property = new Properties();
       InputStream inputStream = null; 
       try{
        inputStream = new FileInputStream(configPath);
	property.load(inputStream);
        
        host=property.getProperty("HOST");
        port=property.getProperty("PORT");
        database=property.getProperty("DATABASE");
        user=property.getProperty("USER");
        password=property.getProperty("PASSWORD");
        driver=property.getProperty("DRIVER");
        url="jdbc:postgresql://"+host+":"+port+"/"+database;
           
       }catch(Exception e){
           Logger.getLogger(DatabaseProperty.class.getName()).log(Level.SEVERE, null, e);
           System.err.println("Exception :"+e.getMessage());
       }finally{
           if (inputStream!=null){
               try {
                   inputStream.close();
               } catch (IOException ex) {
                   Logger.getLogger(DatabaseProperty.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
    }

    String getUrl() {
        return url;
    }

    String getHost() {
        return host;
    }

    String getPort() {
        return port;
    }

    String getDatabase() {
        return database;
    }

    String getUser() {
        return user;
    }

    String getPassword() {
        return password;
    }

    String getDriver() {
        return driver;
    }

  
    
}

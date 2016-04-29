package dk.dtu.locationservice.dao;

import dk.dtu.locationservice.database.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * All nessesury table names, table column, column index and SQL queries are
 * placed in this class to separate them from java codes in the dao classes. The
 * dao classes extends this class
 *
 * @author Bahram Moradi
 */
public abstract class BaseDao {

    protected final int USERS_UID_COL_INDEX = 1;
    protected final int USERS_PHONE_COL_INDEX = 2;
    protected final int USERS_NAME_COL_INDEX = 3;
    protected final int USERS_DESCRIPTION_COL_INDEX = 4;
    protected final int USERS_MAIL_COL_INDEX = 5;
    protected final int LAST_LOCATION_UID_COL_INDEX = 1;
    protected final int LAST_LOCATION_TIME_COL_INDEX = 2;
    protected final int LAST_LOCATION_LOCATION_COL_INDEX = 3;
    
    protected final String ADMIN_ID_COLUMN = "aid";
    protected final String ADMIN_USERNAME_COLUMN = "username";
    protected final String ADMIN_PASSWORD_COLUMN = "password";
    
    protected final String USER_ID_COLUMN = "uid";
    protected final String USER_NAME_COLUMN = "name";
    protected final String USER_PHONE_COLUMN = "phone";
    protected final String USER_MAIL_COLUMN = "mail";
    protected final String USER_DESCRIPTION_COLUMN = "description";

    protected final String TIME_ID_COLUMN = "tid";
    protected final String TIME_COLUMN = "time";
    protected final String LOCATION_ID_COLUMN = "lid";
    protected final String LOCATION_LONGITUDE_COLUMN = "longitude";
    protected final String LOCATION_LATITUDE_COLUMN = "latitude";

    protected final String DATABASE = "tracker.";
    protected final String TABLE_ADMIN = DATABASE + "admins";
    protected final String TABLE_TIMES = DATABASE + "times";
    protected final String TABLE_USERS = DATABASE + "users";
    protected final String TABLE_LOCATIONS = DATABASE + "locations";
    protected final String TABLE_USER_TIME_DEPENDENCY = DATABASE + "user_time_dependency";
    protected final String TABLE_TIME_LOCATION_DEPENDENCY = DATABASE + "time_location_dependency";

    protected String findUserBYIdQuery = "SELECT * FROM " + TABLE_USERS + " WHERE " + USER_ID_COLUMN + " = ? ;";
    protected String createUserQuery = "INSERT INTO " + TABLE_USERS + "(" + USER_NAME_COLUMN + " , "
            + USER_PHONE_COLUMN + " , "
            + USER_MAIL_COLUMN + " , "
            + USER_DESCRIPTION_COLUMN + " )VALUES (?, ?, ?,?);";

    protected String updateUserQuery = "UPDATE " + TABLE_USERS + " SET " + USER_NAME_COLUMN + "=?, "
            + USER_PHONE_COLUMN + "=?, "
            + USER_MAIL_COLUMN + "=?, "
            + USER_DESCRIPTION_COLUMN + " = ? WHERE " + USER_ID_COLUMN + " =?;";
    protected String deleteByMailQuery = "DELETE FROM " + TABLE_USERS + "  WHERE " + USER_MAIL_COLUMN + " = ?;";
    protected String deleteByUidQuery = "DELETE FROM " + TABLE_USERS + "  WHERE " + USER_ID_COLUMN + " = ?;";
    protected String deleteAllRecords = "DELETE FROM " + TABLE_USERS + " ;";
    protected String findAllUser = "SELECT * FROM " + TABLE_USERS + " ;";

    protected String selectTid = "SELECT " + TIME_ID_COLUMN + " FROM " + TABLE_TIMES + " WHERE " + TIME_COLUMN + " = ?";
    protected String selectLid = "SELECT " + LOCATION_ID_COLUMN + " FROM " + TABLE_LOCATIONS + " WHERE " 
            + LOCATION_LONGITUDE_COLUMN + " = ? AND " + LOCATION_LATITUDE_COLUMN + " = ? ";
    protected String insertTime = "INSERT INTO " + TABLE_TIMES + "(" + TIME_COLUMN + ")VALUES (?) ON CONFLICT DO NOTHING;";
    protected String insertLocation = "INSERT INTO " + TABLE_LOCATIONS + "(" + LOCATION_LONGITUDE_COLUMN + "," 
            + LOCATION_LATITUDE_COLUMN + ") VALUES (?, ?) ON CONFLICT DO NOTHING;";
    protected String insertUserTimeDependency = "INSERT INTO " 
            + TABLE_USER_TIME_DEPENDENCY + " VALUES (?,(" + selectTid + "))  ON CONFLICT DO NOTHING ;";
    protected String insertTimeLocationDependency = "INSERT INTO " 
            + TABLE_TIME_LOCATION_DEPENDENCY + " VALUES ((" + selectTid + "),(" + selectLid + ")) ON CONFLICT DO NOTHING;";
    protected String readAllUserLocation = "SELECT " + TIME_COLUMN + "," 
            + LOCATION_LONGITUDE_COLUMN + "," + LOCATION_LATITUDE_COLUMN 
            + " FROM " + TABLE_USER_TIME_DEPENDENCY + " JOIN " + TABLE_TIMES 
            + " USING (" + TIME_ID_COLUMN + ")"
            + " JOIN " + TABLE_TIME_LOCATION_DEPENDENCY 
            + " USING (" + TIME_ID_COLUMN + ") JOIN " 
            + TABLE_LOCATIONS + " USING (" + LOCATION_ID_COLUMN + ") WHERE " + USER_ID_COLUMN + " = ? ORDER BY " + TIME_COLUMN + " ASC;";

    protected String deleteAllLocationByUid = "DELETE FROM " + TABLE_USER_TIME_DEPENDENCY 
            + " WHERE " + USER_ID_COLUMN + " = ? ;";
    protected String deleteUserLocationInTimeInterval = "DELETE FROM " 
            + TABLE_USER_TIME_DEPENDENCY + " WHERE " 
            + USER_ID_COLUMN + " = ? AND " 
            + TIME_ID_COLUMN
            + " IN ( SELECT " + TIME_ID_COLUMN + " FROM " + TABLE_TIMES + " WHERE " + TIME_COLUMN + " BETWEEN ? AND ? );";

    protected String userLocationInTimeInterval = "SELECT " + TIME_COLUMN + "," 
            + LOCATION_LONGITUDE_COLUMN + "," + LOCATION_LATITUDE_COLUMN 
            + " FROM " + TABLE_USER_TIME_DEPENDENCY 
            + " JOIN " + TABLE_TIMES + " USING (" + TIME_ID_COLUMN + ")"
            + " JOIN " + TABLE_TIME_LOCATION_DEPENDENCY 
            + " USING (" + TIME_ID_COLUMN + ") JOIN " 
            + TABLE_LOCATIONS + " USING (" + LOCATION_ID_COLUMN + ") WHERE " 
            + USER_ID_COLUMN + " = ? AND " + TIME_COLUMN + " BETWEEN ? AND ? ORDER BY " 
            + TIME_COLUMN + " ASC;";
    /*Admin related queries*/
    protected String getAllAdminsQuery="SELECT * FROM "+TABLE_ADMIN+" ;";
    protected String isAdminQuery= "SELECT * FROM " + TABLE_ADMIN + " WHERE " + ADMIN_USERNAME_COLUMN 
            + " = ? AND "+ADMIN_PASSWORD_COLUMN+" = ? ;";
    protected String insertAdminQuery="INSERT INTO " + TABLE_ADMIN +"("+ADMIN_USERNAME_COLUMN+" , "
                                                                       +ADMIN_PASSWORD_COLUMN+" ) VALUES (? , ? );";
    protected String deleteAdminQuery="DELETE FROM "+TABLE_ADMIN+" WHERE "+ADMIN_ID_COLUMN+" = ? ;";
    protected String updateAdminQuery="UPDATE "+TABLE_ADMIN+" SET "
                                                +ADMIN_USERNAME_COLUMN+" = ? , "
                                                +ADMIN_PASSWORD_COLUMN+" = ? WHERE "
                                                +ADMIN_ID_COLUMN+" = ? ;";
    

    protected String CLEAR_TABLE = "DELETE FROM  %s ;";
    //protected String DELETE_ALL_TABLE_TIMES = "DELETE FROM " + TABLE_TIMES + ";";
    //protected String DELETE_ALL_TABLE_LOCATIONS = "DELETE FROM " + TABLE_LOCATIONS + ";";
    //protected String DELETE_ALL_TABLE_ADMIN = "DELETE FROM " + TABLE_ADMIN + ";";

    /**
     * This method is only for test purpose and should only be used within test
     * classes
     */
    public void clearDatabase() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String[] tables = new String[]{TABLE_USERS, TABLE_TIMES, TABLE_LOCATIONS,TABLE_ADMIN};

        try {
            conn = DatabaseConnector.getInstance().getConnection();
            for (String table : tables) {
                ps = conn.prepareStatement(String.format(CLEAR_TABLE, table));
                ps.executeUpdate();
            }

        } catch (Exception e) {
            Logger.getLogger(LocationDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                Logger.getLogger(LocationDaoImpl.class.getName()).log(Level.SEVERE, null, e);
                e.printStackTrace();
            }
        }

    }

}

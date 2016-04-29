package dk.dtu.locationservice.dao;
import dk.dtu.locationservice.database.DBConnectionPool;
import dk.dtu.locationservice.idao.ILocationDao;
import dk.dtu.locationservice.dto.Location;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * The name of the methods are self explantory
 *
 * @author Bahram Moradi
 */
public class LocationDaoImpl extends BaseDao implements ILocationDao {

    @Override
    public void insertUserLocation(long uid, List<Location> locations) {

        Connection connection = null;
        PreparedStatement timePs = null;
        PreparedStatement locationPs = null;
        PreparedStatement userTimePs = null;
        PreparedStatement timeLocationPs = null;

        try {
            /*Connect to database*/
            connection = DBConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            /*Create prepared statements*/
            timePs = connection.prepareStatement(insertTime);
            locationPs = connection.prepareStatement(insertLocation);
            userTimePs = connection.prepareStatement(insertUserTimeDependency);
            timeLocationPs = connection.prepareStatement(insertTimeLocationDependency);
            for (Location location : locations) {
                /*add parameters to prepared statements */
                long time = location.getTime();
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();
                timePs.setLong(1, time);
                timePs.addBatch();

                locationPs.setDouble(1, longitude);
                locationPs.setDouble(2, latitude);
                locationPs.addBatch();

                userTimePs.setLong(1, uid);
                userTimePs.setLong(2, time);
                userTimePs.addBatch();

                timeLocationPs.setLong(1, time);
                timeLocationPs.setDouble(2, longitude);
                timeLocationPs.setDouble(3, latitude);
                timeLocationPs.addBatch();
            }

            timePs.executeBatch();
            locationPs.executeBatch();
            userTimePs.executeBatch();
            timeLocationPs.executeBatch();
            connection.commit();

        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(LocationDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        } finally {
            try {
                if (timePs != null) {
                    timePs.close();
                }
                if (locationPs != null) {
                    locationPs.close();
                }
                if (userTimePs != null) {
                    userTimePs.close();
                }
                if (timeLocationPs != null) {
                    timeLocationPs.close();
                }
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (Exception e) {
                Logger.getLogger(LocationDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            }
        }

    }

    @Override
    public List<Location> getUserLocation(long uid) {
        List<Location> locations = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(readAllUserLocation);
            preparedStatement.setLong(1, uid);
            resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    locations.add(new Location(resultSet.getLong(1), resultSet.getDouble(2), resultSet.getDouble(3)));
                }
            }

        } catch (Exception e) {
            Logger.getLogger(LocationDaoImpl.class.getName()).log(Level.SEVERE, null, e);

        } finally {
           DBConnectionPool.close(connection, preparedStatement, resultSet);
        }
        return locations;
    }

    @Override
    public List<Location> getUserLocationInTimeInterval(long uid, long time1, long time2) {
        List<Location> locations = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(userLocationInTimeInterval);
            preparedStatement.setLong(1, uid);
            preparedStatement.setLong(2, time1);
            preparedStatement.setLong(3, time2);
            resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    locations.add(new Location(resultSet.getLong(1), resultSet.getDouble(2), resultSet.getDouble(3)));
                }
            }

        } catch (Exception e) {
            Logger.getLogger(LocationDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        } finally {
             DBConnectionPool.close(connection, preparedStatement, resultSet);
        }
        return locations;
    }

    public void deleteAll() throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(deleteAllRecords);
            preparedStatement.execute();
        } catch (Exception e) {
            Logger.getLogger(LocationDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception(e);
        } finally {
             DBConnectionPool.close(connection, preparedStatement, null);
        }
    }

    @Override
    public void deleteAllUserLocation(long uid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(deleteAllLocationByUid);
            preparedStatement.setLong(1, uid);
            preparedStatement.execute();
        } catch (Exception e) {
            Logger.getLogger(LocationDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
              DBConnectionPool.close(connection, preparedStatement, null);
        }
    }

    @Override
    public void deleteLocationInInterval(long uid, long from, long to) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection =DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(deleteUserLocationInTimeInterval);
            preparedStatement.setLong(1, uid);
            preparedStatement.setLong(2, from);
            preparedStatement.setLong(3, to);
            preparedStatement.execute();
        } catch (Exception e) {
            Logger.getLogger(LocationDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        } finally {
            DBConnectionPool.close(connection, preparedStatement, null);
        }

    }
}

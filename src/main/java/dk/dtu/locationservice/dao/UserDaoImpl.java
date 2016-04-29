package dk.dtu.locationservice.dao;

import dk.dtu.locationservice.database.DBConnectionPool;
import dk.dtu.locationservice.idao.IUserDao;
import dk.dtu.locationservice.dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
  This is the dao(data access object) class fro users for
 * method documentation look at {@link IUserDao}
 * @author Bahram Moradi
 */
public class UserDaoImpl extends BaseDao implements IUserDao {
    @Override
    public User createUser(User user) throws Exception {
        Connection connection =null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection=DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(createUserQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setLong(2, user.getPhone());
            preparedStatement.setString(3, user.getMail());
            preparedStatement.setString(4, user.getDescription());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet != null && resultSet.next()) {
                user.setUid(resultSet.getLong(1));
            }
        } catch (Exception e) {
            Logger.getLogger(LocationDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception(e);
        } finally {
          DBConnectionPool.close(connection, preparedStatement, resultSet);
        }

        return user;
    }

    @Override
    public int updateUser(User user) throws Exception {

        Connection connection = DBConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        int updateStatus = 0;
        try {
            preparedStatement = connection.prepareStatement(updateUserQuery);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setLong(2, user.getPhone());
            preparedStatement.setString(3, user.getMail());
            preparedStatement.setString(4, user.getDescription());
            preparedStatement.setLong(5, user.getUid());
            updateStatus = preparedStatement.executeUpdate();
            System.out.println("Update status code: " + updateStatus);

        } catch (Exception e) {
            Logger.getLogger(LocationDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception(e);
        } finally {
            DBConnectionPool.close(connection, preparedStatement, null);
        }
        return updateStatus;
    }

    @Override
    public void deleteAllUser() throws Exception {
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
    public int deleteUserById(long uid) throws Exception {
        return deleteUser(uid, null);
    }

    public int deleteUser(long uid, String mail) throws Exception {
        int deleteStatus = 0;
        Connection connection = DBConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            if (mail != null) {
                preparedStatement = connection.prepareStatement(deleteByMailQuery);
                preparedStatement.setString(1, mail);
            } else {
                preparedStatement = connection.prepareStatement(deleteByUidQuery);
                preparedStatement.setLong(1, uid);
            }
            deleteStatus = preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            DBConnectionPool.close(connection, preparedStatement, null);
        }

        return deleteStatus;
    }

    @Override
    public User getUserById(long uid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(findUserBYIdQuery);
            preparedStatement.setLong(1, uid);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User(resultSet.getLong(1), resultSet.getString(3), resultSet.getLong(2), resultSet.getString(5), resultSet.getString(4));
            }
        } catch (Exception e) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            DBConnectionPool.close(connection, preparedStatement, resultSet);
        }
        return user;
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        

        try {
            connection =DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(findAllUser);
            resultSet = preparedStatement.executeQuery();

            
                while (resultSet.next()) {
                    users.add(new User(resultSet.getLong(1), resultSet.getString(3), resultSet.getLong(2), resultSet.getString(5), resultSet.getString(4)));
                }
            
        } catch (Exception e) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        } finally {
            DBConnectionPool.close(connection, preparedStatement, resultSet);
        }

        return users;
    }

}

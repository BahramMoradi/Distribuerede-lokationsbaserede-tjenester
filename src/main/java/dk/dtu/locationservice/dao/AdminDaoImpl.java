/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.locationservice.dao;

import dk.dtu.locationservice.database.DBConnectionPool;
import dk.dtu.locationservice.dto.Admin;
import dk.dtu.locationservice.idao.IAdminDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bahram Moradi
 */
public class AdminDaoImpl extends BaseDao implements IAdminDao {

    @Override
    public boolean isAdmin(Admin admin) {
        Connection connection =null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        boolean exist=false;
        
        try {
            connection =DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(isAdminQuery);
            preparedStatement.setString(1, admin.getUserName());
            preparedStatement.setString(2, admin.getPassword());
            result = preparedStatement.executeQuery();
           
            if (result.next()) {
                exist=true;
            }
        } catch (Exception e) {
            Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
            
        } finally {
            DBConnectionPool.close(connection, preparedStatement, result);
        }
        
        return exist;
    }

    @Override
    public List<Admin> getAllAdmins() {
        List<Admin> admins=new ArrayList<>();
        
        Connection connection =null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
  
        try {
            connection =DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(getAllAdminsQuery);
            result = preparedStatement.executeQuery();
           
             while (result.next()) {
                    admins.add(new Admin(result.getLong(1), result.getString(2), result.getString(3)));
                }
        } catch (Exception e) {
            Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
            
        } finally {
            DBConnectionPool.close(connection, preparedStatement, result);
        }
        
        
        
        return admins;
    }

    @Override
    public int createAdmin(Admin admin) {
        Connection connection =null;
        PreparedStatement preparedStatement = null;
        int created=0;
  
        try {
            connection =DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(insertAdminQuery);
            preparedStatement.setString(1,admin.getUserName());
            preparedStatement.setString(2,admin.getPassword());
            created = preparedStatement.executeUpdate();
           
            
        } catch (Exception e) {
            Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
            
        } finally {
            DBConnectionPool.close(connection, preparedStatement, null);
        }
        return created;
    }

    @Override
    public int deleteAdminById(long aid) {
        Connection connection =null;
        PreparedStatement preparedStatement = null;
        int deleted=0;
  
        try {
            connection =DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(deleteAdminQuery);
            preparedStatement.setLong(1,aid);
            deleted = preparedStatement.executeUpdate();
           
            
        } catch (Exception e) {
            Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
            
        } finally {
            DBConnectionPool.close(connection, preparedStatement, null);
        }
        return deleted;
        
    }

    @Override
    public int updateAdmin(Admin admin) {
        Connection connection =null;
        PreparedStatement preparedStatement = null;
        int updated=0;
  
        try {
            connection =DBConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(updateAdminQuery);
            preparedStatement.setString(1,admin.getUserName());
            preparedStatement.setString(2,admin.getPassword());
            preparedStatement.setLong(3,admin.getAid());
            updated = preparedStatement.executeUpdate();
           
            
        } catch (Exception e) {
            Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        } finally {
            DBConnectionPool.close(connection, preparedStatement, null);
        }
        return updated;
        
    }
    
}

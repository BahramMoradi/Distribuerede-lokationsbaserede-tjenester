/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.map.servlets;

import dk.dtu.locationservice.dao.DaoFactory;
import dk.dtu.locationservice.dto.User;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Bahram Moradi
 */
public class Controller {
    
   public List<User> getAllUsers(){
       List<User> users=new ArrayList<>();
           return DaoFactory.getUserDao().getAllUser();
   }
   public void deleteUser(long uid){
       try {
           DaoFactory.getUserDao().deleteUserById(uid);
       } catch (Exception ex) {
           Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    
}

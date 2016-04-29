/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.locationservice.dao;

import dk.dtu.locationservice.idao.IAdminDao;
import dk.dtu.locationservice.idao.ILocationDao;
import dk.dtu.locationservice.idao.IUserDao;

/**
 *
 * @author Bahram
 */
public class DaoFactory {

    public static IUserDao getUserDao() {
        return new UserDaoImpl();
    }

    public static ILocationDao getLocationDao() {
        return new LocationDaoImpl();
    }

    public static IAdminDao getAdminDao() {
        return new AdminDaoImpl();
    }

}

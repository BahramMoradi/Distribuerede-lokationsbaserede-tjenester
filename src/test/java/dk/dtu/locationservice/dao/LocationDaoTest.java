/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.locationservice.dao;

import dk.dtu.locationservice.idao.ILocationDao;
import dk.dtu.locationservice.dto.Location;
import dk.dtu.locationservice.dto.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


/**
 * This class tests all methods in {@link LocationDaoImpl} class
 * @author Bahram
 * @Time :05-12-2015 22:02
 */
public class LocationDaoTest {

    private static List<User> createdUsers;
    private static UserDaoImpl userDao;
    private static LocationDaoImpl locationDao = null;
    private static List<Location> locations = null;

    @Before
    public void init() throws Exception {

        System.err.println("Runing init");
        createdUsers = new ArrayList<>();
        locations = new ArrayList<>();
        locations.add(new Location(1, 12.44926238, 55.7394383));
        locations.add(new Location(2, 12.45943332, 55.73644241));
        locations.add(new Location(3, 12.52123141, 55.78550742));
        locations.add(new Location(4, 12.52123141, 55.78550742));
        locations.add(new Location(5, 12.52123141, 55.78550742));
        locations.add(new Location(6, 12.52123141, 55.78550742));
        userDao = new UserDaoImpl();
        locationDao = new LocationDaoImpl();
        locationDao.clearDatabase();
        List<User> users = new ArrayList<>();
        users.add(new User(0, "Bahram", 4542227786L, "bahmo25@gmail.com", "wasting my time"));
        users.add(new User(0, "Nanna", 4542732770L, "nana@hotmail.com", "styling specialist"));
        users.add(new User(0, "Brian", 4560401012L, "bn@asseco.dk", " developer at asseco "));
        for (User us : users) {
            User cUser = userDao.createUser(us);
            createdUsers.add(cUser);
        }

    }

    @Test
    public void testInsertUserLocation() throws Exception {
        long uid_one = createdUsers.get(0).getUid();
        long uid_two = createdUsers.get(1).getUid();
        /*insert location for two user*/
        locationDao.insertUserLocation(uid_one, locations);
        locationDao.insertUserLocation(uid_two, locations);
        /* retrive location for first user and test it */
        List<Location> actual = locationDao.getUserLocation(uid_one);
        assertTrue(actual.size() == locations.size());
        actual.forEach(element -> {
            assertTrue(locations.contains(element));
        });
        /* retrive location for first user and test it */
        actual = locationDao.getUserLocation(uid_two);
        assertTrue(actual.size() == locations.size());
        actual.forEach(element -> {
            assertTrue(locations.contains(element));
        });
    }

    @Test
    public void testGetUserLocation() {
        long uid = createdUsers.get(0).getUid();
        /*insert location for two user*/
        locationDao.insertUserLocation(uid, locations);
        /* retrive location and test it  */
        List<Location> actual = locationDao.getUserLocation(uid);
        assertTrue("The catual and expected list must be of equal size", actual.size() == locations.size());
        actual.forEach(element -> {
            assertTrue(locations.contains(element));
        });
    }

    @Test
    public void testGetUserLocationInInterval() {
        ILocationDao dao = new LocationDaoImpl();
        long uid = createdUsers.get(0).getUid();
        locationDao.insertUserLocation(uid, locations);
        List<Location> loc = dao.getUserLocationInTimeInterval(uid, 2, 4);
        assertTrue(loc.size() == 3);
    }

    @Test
    public void testDeleteAllUserLocation() {
        ILocationDao dao = new LocationDaoImpl();
        long uid = createdUsers.get(0).getUid();
        // insert some locations for a user 
        dao.insertUserLocation(uid, locations);
        // retrive locations for the same user and verify that it is not empty
        assertFalse("User location must not be empty", dao.getUserLocation(uid).isEmpty());
        // delete all location for same user
        dao.deleteAllUserLocation(uid);
        //get all location for same user and veryfy that it is empty
        assertTrue("User location must be empty", dao.getUserLocation(uid).isEmpty());
    }

    @Test
    public void deleteLocationInInterval() {
        ILocationDao dao = new LocationDaoImpl();
        long from = locations.get(1).getTime();
        long to = locations.get(5).getTime();
        long uid = createdUsers.get(0).getUid();
        // insert some locations for a user 
        dao.insertUserLocation(uid, locations);
        // retrive locations for the same user and verify that it is not empty
        assertFalse("User location must not be empty", dao.getUserLocation(uid).isEmpty());
        // delete location in interval from and to
        dao.deleteLocationInInterval(uid, from, to);
        // retive the location in same deleted interval and check it
        assertTrue("Must be empty..", dao.getUserLocationInTimeInterval(uid, from, to).isEmpty());

    }

}

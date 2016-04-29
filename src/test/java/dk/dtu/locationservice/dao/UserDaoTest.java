
package dk.dtu.locationservice.dao;
import dk.dtu.locationservice.idao.IUserDao;
import dk.dtu.locationservice.dto.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * This class tests {@link UserDaoImpl} methods
 * @author Bahram Moradi
*/
public class UserDaoTest {

    private static List<User> users;
    private static IUserDao userdao;

    @Before
    public void init() throws Exception {
        System.err.println("Runing init");
        userdao = new UserDaoImpl();
        userdao.deleteAllUser();
        users = new ArrayList<>();
        users.add(new User(0, "Bahram", 4542227786L, "bahmo25@gmail.com", "Developer"));
        users.add(new User(0, "Diman", 4542732770L, "dimanmoradi@hotmail.com", "Nurse"));
        users.add(new User(0, "Aziz", 4560401012L, "aziz.moradi@hotmail.com", "Not yet...."));
    }

    @Test
    public void testDeleteAllUser() throws Exception {
        /*insert all users*/
        for (User user : users) {
            userdao.createUser(user);
        }
        /*check to see if it is inerted*/
        List<User> all = userdao.getAllUser();
        assertTrue("The list must not be empty", !all.isEmpty());
        assertTrue("The list must contain at 3 users", all.size() == 3);
        /*delete all users*/
        userdao.deleteAllUser();
        /*retrive all user again and see if it is empty*/
        assertTrue("The list must be empty", userdao.getAllUser().isEmpty());

    }

    @Test
    public void testCreateUser() throws Exception {
        User user = userdao.createUser(users.get(0));
        assertTrue(user.getUid() != 0);
    }

    @Test
    public void testGetUserById() throws Exception {
        /*create a user*/
        User user = userdao.createUser(users.get(0));
        assertTrue(user.getUid() != 0);
        /*retrive user by id*/
        User actual = userdao.getUserById(user.getUid());
        assertTrue(user.getUid() == actual.getUid());
        assertTrue(user.getName().equals(actual.getName()));
    }

    @Test
    public void testDeleteUserById() throws Exception {
        /*insert a user*/
        User user = userdao.createUser(users.get(0));
        assertTrue(user.getUid() != 0);
        /*delete user*/
        userdao.deleteUserById(user.getUid());
        /*try to find user again*/
        User actual = userdao.getUserById(user.getUid());
        assertTrue(actual == null);
    }

    @Test
    public void testUpdateUser() throws Exception {
        /*insert a user*/
        User user = userdao.createUser(users.get(0));
        /*update user*/
        String name = "HANSEN";
        user.setName(name);
        userdao.updateUser(user);
        /*retrive user*/
        User updated = userdao.getUserById(user.getUid());
        assertTrue(updated.getName().equals(name));

    }

}

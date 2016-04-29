package dk.dtu.locationservice.dao;

import dk.dtu.locationservice.dto.Admin;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Bahram Moradi
 */
public class AdminDaoTest {

    private List<Admin> admins = null;
    private AdminDaoImpl adminDao = null;

    @Before
    public void init() {
        admins = new ArrayList<>();
        admins.add(new Admin(0, "Bahram", "42227786"));
        admins.add(new Admin(0, "Aziz", "65665665"));
        admins.add(new Admin(0, "Nanna", "4222798"));
        adminDao = new AdminDaoImpl();
        adminDao.clearDatabase();
    }

    @Test
    public void testCreateAdmin() {
        /* insert a location*/
        Admin admin = admins.get(0);
        int created = adminDao.createAdmin(admin);
        assertTrue("The id must not be zero.", created != 0);
        /* get all admins*/
        List<Admin> all = adminDao.getAllAdmins();
        assertTrue("The list must not be empty.", !all.isEmpty());
        assertTrue("Size must be one.", all.size() == 1);
        Admin actual = all.get(0);
        assertTrue("Username and password must be equal.", actual.getUserName().equals(admin.getUserName()) && actual.getPassword().equals(admin.getPassword()));
    }

    @Test
    public void testIsAdmin() {
        Admin admin = admins.get(0);
        int created = adminDao.createAdmin(admin);
        assertTrue("The id must not be zero.", created != 0);
        assertTrue("This must be true", adminDao.isAdmin(admin));
    }

     @Test
    public void testGetAllAdmins() {
        /*insert some admins*/
        adminDao.createAdmin(admins.get(0));
        adminDao.createAdmin(admins.get(1));
        adminDao.createAdmin(admins.get(2));
        /* test */
        List<Admin> all = adminDao.getAllAdmins();
        assertTrue("The list must not be empty.", !all.isEmpty());
        assertTrue("Size must be three.", all.size() == 3);

    }

    @Test
    public void testDeleteAdminById() {
        adminDao.createAdmin(admins.get(0));
        adminDao.createAdmin(admins.get(1));
        adminDao.createAdmin(admins.get(2));
        List<Admin> all = adminDao.getAllAdmins();
        assertTrue("The list must not be empty.", !all.isEmpty());
        assertTrue("Size must be one.", all.size() == 3);
        adminDao.deleteAdminById(all.get(0).getAid());
        List<Admin> two = adminDao.getAllAdmins();
        assertTrue("The list must not be empty.", !two.isEmpty());
        assertTrue("Size must be two.", two.size() == 2);

    }
   @Test
    public void testUpdateAdmin() {
        /*insert*/
        adminDao.createAdmin(admins.get(0));
        List<Admin> all = adminDao.getAllAdmins();
        assertTrue("The list must not be empty.", !all.isEmpty());
        assertTrue("Size must be one.", all.size() == 1);
        /*update*/
        Admin created = all.get(0);
        Admin updateAdmin=new Admin(created.getAid(), "new username", "new password");
        adminDao.updateAdmin(updateAdmin);
        List<Admin> actualList = adminDao.getAllAdmins();
        assertTrue("Size of actual list must be one.", actualList.size() == 1);
        Admin actualAdmin = actualList.get(0);
        assertTrue("Must be updated", updateAdmin.getUserName().equals(actualAdmin.getUserName())&&updateAdmin.getPassword().equals(actualAdmin.getPassword()));
    }

}

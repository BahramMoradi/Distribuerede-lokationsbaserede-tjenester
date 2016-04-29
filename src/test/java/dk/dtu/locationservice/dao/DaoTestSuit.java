
package dk.dtu.locationservice.dao;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suit class for {@link LocationDaoTest} and {@link UserDaoTest} 
 * @author Bahram Moradi
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    LocationDaoTest.class,
    UserDaoTest.class,
    AdminDaoTest.class
})
public class DaoTestSuit {
}

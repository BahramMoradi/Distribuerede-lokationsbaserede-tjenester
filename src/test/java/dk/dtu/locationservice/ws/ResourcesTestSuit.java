
package dk.dtu.locationservice.ws;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 
 * Be careful..!!! disable The web service test when
 * building the project. Run the tests after deployment of the project.
 * Test suit for {@link LocationResourceTest} and {@link UserResourceTest}
 * @author Bahram Moradi 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    LocationResourceTest.class,
    UserResourceTest.class
})
public class ResourcesTestSuit {
}

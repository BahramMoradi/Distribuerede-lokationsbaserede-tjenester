
package dk.dtu.locationservice.ws;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dk.dtu.locationservice.dao.LocationDaoImpl;
import dk.dtu.locationservice.dao.UserDaoImpl;
import dk.dtu.locationservice.dto.Location;
import dk.dtu.locationservice.dto.User;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * <p style="color:red">The web service test is disabled in the projects pom.xml
 * fill. Run the test after the deployment of the project
 * </p>
 * <h3>Test LocationResource webservice </h3>
 * <p>
 * In this class all method of the LocationResource will be tested by using
 * JUnit framework. JAVAX Client api is used for invoking the method of
 * {@link LocationResource} The methods do not need any documenation as their
 * names are self-explanatory
 * </p>
 *
 * @author Bahram Moradi
 */
public class LocationResourceTest {

    private List<User> createdUsers;
    private UserDaoImpl userdao;
    private LocationDaoImpl locationDao = null;
    private List<Location> locations = null;
    private String path = "http://localhost:8080/tracker/service/locations";

    /**
     * <h3>Method : init</h3>
     * In this method necessary DAO object and all other resource needed for
     * testing ( users , locations )are created. This method is run once for
     * each method.The test methods are independent of each other.
     *
     * @throws Exception
     */
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

        userdao = new UserDaoImpl();
        locationDao = new LocationDaoImpl();
        locationDao.clearDatabase();
        List<User> users = new ArrayList<>();

        users.add(new User(0, "Bahram", 4542227786L, "bahmo25@gmail.com", "Developer"));
        users.add(new User(0, "Diman", 4542732770L, "dimanmoradi@hotmail.com", "Nurse"));
        users.add(new User(0, "Aziz", 4560401012L, "aziz.moradi@hotmail.com", "Not yet...."));
        for (User us : users) {
            User cUser = userdao.createUser(us);
            createdUsers.add(cUser);
        }
    }

    /**
     * <h3>Test insertUserLocation</h3>
     * <p>
     * This method tests the "insertUserLocation" method from LocationResource
     * webservice class The method in the LocationResource has a path parameter
     * {uid} and a list of locations for more info look at 
     * {@link LocationResource#insertUserLocation(javax.ws.rs.container.AsyncResponse, long, java.util.List) }
     */
    @Test
    public void testInsertUserLocation() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(path + "/" + createdUsers.get(0).getUid());
        Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(locations, MediaType.APPLICATION_JSON));
        assertTrue("The Response was not 200", response.getStatus() == 200);
    }

    @Test
    public void getUserLocation() {
        /*inserting all locations for two users*/
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(path + "/" + createdUsers.get(0).getUid()); //for one user
        Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(new Gson().toJson(locations), MediaType.APPLICATION_JSON));
        assertTrue("The Response was not 200", response.getStatus() == 200);

        target = client.target(path + "/" + createdUsers.get(1).getUid()); // for an other user
        response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(new Gson().toJson(locations), MediaType.APPLICATION_JSON));
        assertTrue("The Response was not 200", response.getStatus() == 200);

        /*retrive location for one user*/
    }

    @Test
    public void getUserLocationInTimeInterval() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(path + "/" + createdUsers.get(0).getUid());
        Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(new Gson().toJson(locations), MediaType.APPLICATION_JSON));
        assertTrue("The Response was not 200", response.getStatus() == 200);

        target = client.target(path + "/" + createdUsers.get(0).getUid() + "/" + 2 + "/" + 4);
        Response res = target.request(MediaType.APPLICATION_JSON).get();
        System.err.println("URI : " + target.getUri().toString());
        System.err.println("status info: " + res.getStatusInfo());
        String body = res.readEntity(String.class);
        System.err.println("The body of call: " + body);

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Location>>() {
        }.getType();
        List<Location> ls = gson.fromJson(body, listType);
        assertTrue(ls.size() == 3);

    }

    @Test
    public void deleteAllUserLocation() {
        long uid = createdUsers.get(0).getUid();
        /* insert some location for a user*/
        locationDao.insertUserLocation(uid, locations);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(path + "/" + uid);
        Response response = target.request().delete();
        assertTrue("Status should be ok (200)", response.getStatus() == 200);
        // test also database
        assertTrue("User location must be empty", locationDao.getUserLocation(uid).isEmpty());

    }

    @Test
    public void deleteUserLocationInTimeInterval() {
        long uid = createdUsers.get(0).getUid();
        long from = locations.get(0).getTime();
        long to = locations.get(4).getTime();
        /* insert some location for a user*/
        locationDao.insertUserLocation(uid, locations);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(path + "/" + uid + "/" + from + "/" + to);
        Response response = target.request().delete();
        assertTrue("Status should be ok (200)", response.getStatus() == 200);
        // test also database
        assertTrue("User location must be empty", locationDao.getUserLocationInTimeInterval(uid, from, to).isEmpty());
    }

}

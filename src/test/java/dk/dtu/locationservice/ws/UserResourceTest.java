/*
 * @author Bahram Moradi
 */
package dk.dtu.locationservice.ws;

import dk.dtu.locationservice.dao.UserDaoImpl;
import dk.dtu.locationservice.dto.User;
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
 * fill. Run the test after the deployment of the project </p>
 * <h1>Test Of User Resource</h1>
 * <p>
 * This class test all web service method related to the user resource. The
 * methods do not need any documenation as their names are self-explanatory
 * </p>
 *
 */
public class UserResourceTest {

    private UserDaoImpl userdao;
    private List<User> users = null;
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/tracker/service";

    /**
     * This method runs before execution of each test method to create som
     * necessary data
     */
    @Before
    public void init() {
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("users");
        userdao = new UserDaoImpl();
        userdao.clearDatabase();
        users = new ArrayList<>();
        users.add(new User(0, "Bahram", 4542227786L, "bahmo25@gmail.com", "Developer"));
        users.add(new User(0, "Diman", 4542732770L, "dimanmoradi@hotmail.com", "Nurse"));
        users.add(new User(0, "Aziz", 4560401012L, "aziz.moradi@hotmail.com", "Not yet...."));

    }

    @Test
    public void createUser() {
        Entity body = Entity.entity(users.get(0), MediaType.APPLICATION_JSON);
        User user = webTarget.request(MediaType.APPLICATION_JSON).post(body, User.class);
        assertTrue("User id should not be 0", user.getUid() != 0);
    }

    @Test
    public void deleteUser() {
        /*first create a user*/
        Entity body = Entity.entity(users.get(0), MediaType.APPLICATION_JSON);
        User user = webTarget.request(MediaType.APPLICATION_JSON).post(body, User.class);
        assertTrue("User id should not be 0", user.getUid() != 0);
        /*delete the user*/
        WebTarget wt = webTarget.path(Long.toString(user.getUid()));
        Response res = wt.request().delete(Response.class);
        assertTrue("status code must be 200", res.getStatus() == 200);

    }

    @Test
    public void updateUser() {
        /*first create a user*/
        User user = users.get(0);
        Entity body = Entity.entity(user, MediaType.APPLICATION_JSON);
        User createdUser = webTarget.request(MediaType.APPLICATION_JSON)
                .post(body, User.class);
        assertTrue("User id should not be 0", createdUser.getUid() != 0);
        /*try to update user*/
        String upString = "Updating...";
        createdUser.setDescription(upString);
        Entity updateBody = Entity.entity(createdUser, MediaType.APPLICATION_JSON);
        Response updateResponse = webTarget.request(MediaType.APPLICATION_JSON)
                .put(updateBody, Response.class);
        assertTrue("Status code should be 200", updateResponse.getStatus() == 200);
        /*Retrive the user and check update*/
        long uid = createdUser.getUid();
        User updatedUser = webTarget.path(Long.toString(uid))
                .request(MediaType.APPLICATION_JSON)
                .get(User.class);
        assertTrue(updatedUser.getDescription().equals(upString));

    }

}

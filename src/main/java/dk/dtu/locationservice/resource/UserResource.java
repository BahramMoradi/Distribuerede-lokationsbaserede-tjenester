package dk.dtu.locationservice.resource;

import dk.dtu.locationservice.dao.DaoFactory;
import dk.dtu.locationservice.dto.User;
import dk.dtu.locationservice.iresource.IUserResource;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.concurrent.Executors;

/**
 * REST Web Service
 *
 * @author Bahram
 */
public class UserResource implements IUserResource {

    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final String TAG = "UserResource: ";

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public UserResource() {
    }

    @Override
    public Response getUser(long uid) {
        User user = DaoFactory.getUserDao().getUserById(uid);
        System.err.println(TAG + user);
        if (user == null) {
            throw new WebApplicationException(Response.status(Status.NOT_FOUND).build());
        }
        return Response.ok().entity(user).type(MediaType.APPLICATION_JSON).build();

    }

    @Override
    public Response updateUser(User user) {
        print(TAG + "/ updateUser : " + user.toString());
        try {
            int updateStatus = DaoFactory.getUserDao().updateUser(user);
            if (updateStatus == 0) {
                throw new WebApplicationException(Response.status(Status.NOT_MODIFIED).build());
            }
        } catch (Exception e) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, e);
            throw new WebApplicationException(Response.status(Status.NOT_MODIFIED).build());
        }
        return Response.ok().build();
    }

    @Override
    public Response deleteUser(long uid) {
         print(TAG + "/ delete user: " + uid);
        int deleteStatus = 0;
        try {
            deleteStatus = DaoFactory.getUserDao().deleteUserById(uid);
        } catch (Exception ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (deleteStatus == 0) {
            throw new WebApplicationException(Response.status(Status.NOT_FOUND).build());
        }

        return Response.ok().build();
    }

    @Override
    public Response createUser(User user) {

        User created = null;
        System.out.println("A Request for creating user received : " + user.toString());
        try {
            created = DaoFactory.getUserDao().createUser(user);
        } catch (Exception ex) {
            if (ex.getMessage().contains("users_mail_key")) {
                /*when a user mail is in use, but tries to create a new user account*/
                throw new WebApplicationException(Response.status(Status.CONFLICT).entity(user).type(MediaType.APPLICATION_JSON).build());
            }
        }
        return Response.ok().entity(created).build();
    }

    public void print(String str) {
        System.out.println(str);
    }

}

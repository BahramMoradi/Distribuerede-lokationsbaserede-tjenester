/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.locationservice.iresource;
import dk.dtu.locationservice.dto.User;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Root resource class for users
 * @author Bahram Moradi
 */
@Path("users")
public interface IUserResource {
    
    /**
     * This method find a user by id and return it to caller.
     * It is used for test
     *
     * @param uid : id of the user
     * @return
     */
    @GET
    @Path("/{uid}")
    @Produces("application/json")
    public Response getUser(@PathParam("uid") long uid);

    /**
     * PUT is used to update a resource. PUT is idempotent but not safe.
     *
     * @param user
     * @return
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(User user);

    /**
     * This method delete a user by id
     *
     * @param uid : id of the user
     * @return
     */
    @DELETE
    @Path("/{uid}")
    public Response deleteUser(@PathParam("uid") long uid);

    /**
     * This method is for creating a user in database
     *
     * @param user : The user to be created
     * @return
     */
    @POST
    @Produces(value = MediaType.APPLICATION_JSON)
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response createUser(User user);

  
    
}

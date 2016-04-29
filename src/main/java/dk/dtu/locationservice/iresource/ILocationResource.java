/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.locationservice.iresource;

import dk.dtu.locationservice.dto.Location;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Bahram Moradi
 */
@Path("locations")
public interface ILocationResource {

    /**
     * Insert a list of location to databse for a user with user id = uid.
     *
     * @param uid : The id of user
     * @param locations : A Response object that conatin a status code OK
     * @return
     */
    @POST
    @Path(value = "/{uid}")
    @Produces(value = MediaType.APPLICATION_JSON)
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response insertUserLocation(@PathParam(value = "uid") final long uid, final List<Location> locations);
     /**
     * this method retrive all location from database and return it to the
     * calling application
     *
     * @param uid : The id of the user
     * @return : A response object that conation a status code OK and a list of
     * location in the body of the response
     */
    @GET
    @Path(value = "/{uid}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getUserLocations(@PathParam(value = "uid") long uid);
    
    /**
     * This method retrives the locations of a user in a time interval
     *
     * @param uid : The id of the user
     * @param from : The start of time interval
     * @param to : The end of time interval
     * @return ; Response object that contain the status of the HTTP call and a
     * list of location in the response body. the list of location is in json
     * format
     */
    @GET
    @Path(value = "/{uid}/{from}/{to}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getUserLocationInTimeInterval(@PathParam("uid") long uid, @PathParam("from") long from, @PathParam("to") long to);
    
    
    /**
     * This method delete all location for a user.
     * @param uid: The id of the user
     * @return : Response object that conatin a response code
     */
    @DELETE
    @Path(value = "/{uid}")
    public Response deleteAllLocation(@PathParam("uid") long uid);
    
      /**
     * This method delete all location in a time interval
     * @param uid : The id of the user
     * @param from : the start time
     * @param to: the end time
     * @return Response obejct with a status code
     */
    @DELETE
    @Path(value = "/{uid}/{from}/{to}")
    public Response deleteInTimeInterval(@PathParam("uid") long uid, @PathParam("from") long from, @PathParam("to") long to);

}

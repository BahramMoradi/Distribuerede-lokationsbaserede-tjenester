
package dk.dtu.locationservice.resource;
import dk.dtu.locationservice.dao.DaoFactory;
import dk.dtu.locationservice.dto.Location;
import dk.dtu.locationservice.iresource.ILocationResource;


import java.util.Date;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * <h3>LocationResource Class</h3>
 * <p></p>
 * @author Bahram Moradi
 */
public class LocationResource implements ILocationResource {

    private final String TAG = "LocationResource / ";
    @Context
    private UriInfo context;


    @Override
    public Response insertUserLocation( final long uid, final List<Location> locations) {
       print("insertUserLocation, UID : " + uid + " Date : " + new Date().toString());
        locations.forEach(System.out::println);
        DaoFactory.getLocationDao().insertUserLocation(uid, locations);
        return Response.ok().build();
    }

    @Override
    public Response getUserLocations( long uid) {
        print("getUserLocations, UID : " + uid);
        List<Location> locations = DaoFactory.getLocationDao().getUserLocation(uid);
        return Response.ok(locations).build();
    }

    @Override
    public Response getUserLocationInTimeInterval(long uid, long from,long to) {
        print("getUserLocationByTime called by parameters : uid: " + uid + " from: " + from + " to: " + to);
        List<Location> locations =DaoFactory.getLocationDao().getUserLocationInTimeInterval(uid, from, to);
        print("getUserLocationByTime: size of returende array:" + locations.size());
        return Response.ok().entity(locations).build();
    }

    
    @Override
    public Response deleteAllLocation(long uid) {
        print(TAG + "Delete all location for user with uid=" + uid);
        DaoFactory.getLocationDao().deleteAllUserLocation(uid);
        return Response.ok().build();
    }

    @Override
    public Response deleteInTimeInterval(long uid,long from,  long to) {
        print(TAG + "Delete all location for user with uid=" + uid + " between times: " + from + " " + to);
        DaoFactory.getLocationDao().deleteLocationInInterval(uid, from, to);
        return Response.ok().build();
    }

    /**
     * Utility method for printing information about requests
     * @param input: String
     */
    public void print(String input) {
        System.out.println("==================================================================");
        System.out.println(TAG + input);
    }

}

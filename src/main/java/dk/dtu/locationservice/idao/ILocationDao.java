package dk.dtu.locationservice.idao;

import dk.dtu.locationservice.dto.Location;
import dk.dtu.locationservice.dto.User;
import java.util.List;

/**
 * Supported method for location table
 *
 * @author Bahram Moradi
 */
public interface ILocationDao {

    /**
     * This methods inserts a list of user location in database
     *
     * @param uid : The id of the {@link User}
     * @param locations : A list of location {@link Location}
     */
    public void insertUserLocation(long uid, List<Location> locations);

    /**
     * Retrive all locations for a user
     *
     * @param uid : Id of the user
     * @return : List of location
     */
    public List<Location> getUserLocation(long uid);

    /**
     * Retrive user location in a time interval from database
     *
     * @param uid : Id of the user
     * @param from : Start time
     * @param to : End time
     * @return : List of location
     */
    public List<Location> getUserLocationInTimeInterval(long uid, long from, long to);

    /**
     * This method delets all location of a user
     *
     * @param uid : The id of the user
     */
    public void deleteAllUserLocation(long uid);

    /**
     * This method deletes user location between two defined time.
     *
     * @param uid : The id of the user
     * @param from : The start time
     * @param to : The end time
     */
    public void deleteLocationInInterval(long uid, long from, long to);

}

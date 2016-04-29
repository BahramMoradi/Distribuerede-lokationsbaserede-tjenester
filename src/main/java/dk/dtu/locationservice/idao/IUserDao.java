package dk.dtu.locationservice.idao;

import dk.dtu.locationservice.dto.User;
import java.util.List;

/**
 * @author Bahram Moradi This is the interface class for users.
 */
public interface IUserDao {

    /**
     * This method delete all users in database. This method is required when
     * testing dao class.
     *
     * @throws Exception :Exception related to user should be handled in
     * resource classes
     */
    void deleteAllUser() throws Exception;

    /**
     * This method find all users in database.
     *
     * @return: a list of user in database
     */
    List<User> getAllUser();

    /**
     * This method creates a user in database
     *
     * @param user : A {@link User} to be created in database
     * @return
     * @throws Exception
     */
    User createUser(User user) throws Exception;

    /**
     * This method finds and returns a user with user id equals to uid from
     * database
     *
     * @param uid : The user id of the user
     * @return : {@link User}
     */
    User getUserById(long uid);

    /**
     * This method delete a user by using user's id
     *
     * @param uid : the user id of the user to be deleted
     * @return 0 if user does not exist to be deleted or 1
     * @throws Exception
     */
    int deleteUserById(long uid) throws Exception;

    /**
     * This method update a user. The id of user is used for finding and
     * updating the user
     *
     * @param user : The user to be updated {@link User}
     * @return 1 if user update succeed otherwise 0
     * @throws Exception
     */
    int updateUser(User user) throws Exception;

}

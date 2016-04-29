/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.locationservice.dto;
import java.io.Serializable;
import java.util.List;
/**
 * For one user
 * @author Bahram
 */
public class UserLocations implements Serializable{
    private User user;
    private List<Location> locations=null;
    public UserLocations(User user,List<Location> locations) {
        this.user = user;
        this.locations=locations;
    }
    
    public UserLocations() {}


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

}

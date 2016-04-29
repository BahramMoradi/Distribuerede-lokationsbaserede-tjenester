/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.locationservice.dto;

import java.util.List;

/**
 *
 * @author Bahram
 */
public class GeoJson {
    
    List<UserLocations> allUsersAndLocations;
      public GeoJson(List<UserLocations> allUsersAndLocations) {
          this.allUsersAndLocations=allUsersAndLocations;
    }
        public GeoJson() {}

    public List<UserLocations> getAllUsersAndLocations() {
        return allUsersAndLocations;
    }

    public void setAllUsersAndLocations(List<UserLocations> allUsersAndLocations) {
        this.allUsersAndLocations = allUsersAndLocations;
    }
    

   

  
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.locationservice.idao;

import dk.dtu.locationservice.dto.Admin;
import java.util.List;

/**
 *
 * @author Bahram Moradi
 */
public interface IAdminDao {
    public boolean isAdmin(Admin admin);
    public List<Admin> getAllAdmins();
    public int createAdmin(Admin admin);
    public int deleteAdminById(long aid);
    public int updateAdmin(Admin admin);
}


package dk.dtu.locationservice.dto;

/**
 *
 * @author Bahram Moradi
 */
public class Admin {

    private long aid;
    private String  userName;
    private String password;

    public Admin() {
    }

    public Admin(long aid, String userName, String  password) {
        this.aid = aid;
        this.userName = userName;
        this.password = password;
    }

    public long getAid() {
        return aid;
    }

    public void setAid(long aid) {
        this.aid = aid;
    }

    public String  getUserName() {
        return userName;
    }

    public void setUserName(String  userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String  password) {
        this.password = password;
    }

}

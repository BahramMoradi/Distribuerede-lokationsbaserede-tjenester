
package dk.dtu.locationservice.dto;

import java.io.Serializable;

/**
 *
 * @author Bahram Moradi
 */
public class User implements Serializable {

    private long uid;
    private long phone;
    private String name;
    private String mail;
    private String description;

    public User() {
    }

    public User(long uid, String name, long phone, String mail, String description) {
        this.uid = uid;
        this.phone = phone;
        this.name = name;
        this.mail = mail;
        this.description = description;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "User{" + "uid=" + uid + ", phone=" + phone + ", name=" + name + ", mail=" + mail + ", description=" + description + '}';
    }

}

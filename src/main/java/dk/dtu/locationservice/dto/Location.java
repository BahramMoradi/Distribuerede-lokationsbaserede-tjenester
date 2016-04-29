package dk.dtu.locationservice.dto;

import java.io.Serializable;

/**
 * @author Bahram Moradi
 */
public class Location implements Serializable {

    private long time;
    private double latitude;
    private double longitude;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (int) (this.time ^ (this.time >>> 32));
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        if (this.time != other.time) {
            return false;
        }
        if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude)) {
            return false;
        }
        if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude)) {
            return false;
        }
        return true;
    }

    public Location() {
    }

    public Location(long time, double longitude, double latitude) {
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Location{" + "time=" + time + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }

}

package com.pop.pricecutz.map;

/**
 * Created by Pop H2 on 10/11/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class GPSPoint {
    double latitude;
    double longitude;

    public GPSPoint(double latitude, double longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
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
}

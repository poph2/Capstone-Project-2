package com.pop.pricecutz;

import com.pop.pricecutz.map.GPSPoint;

/**
 * Created by Pop H2 on 10/18/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class Outlet {

    long id;
    long company_id;
    String name;
    double latitude;
    double longitude;

    public Outlet() {}

    public Outlet(long id, int company_id, String name, double latitude, double longitude) {
        this.id = id;
        this.company_id = company_id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

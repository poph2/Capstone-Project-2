package com.pop.pricecutz.entities;

import com.pop.pricecutz.map.GPSPoint;

/**
 * Created by Pop H2 on 10/18/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class Outlet {

    int company_id;
    GPSPoint gpsPoint;
    Discount discount;

    public Outlet(int company_id, GPSPoint gpsPoint, Discount discount) {
        this.company_id = company_id;
        this.gpsPoint = gpsPoint;
        this.discount = discount;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public GPSPoint getGpsPoint() {
        return gpsPoint;
    }

    public void setGpsPoint(GPSPoint gpsPoint) {
        this.gpsPoint = gpsPoint;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}

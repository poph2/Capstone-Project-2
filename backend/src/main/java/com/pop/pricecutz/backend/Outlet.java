package com.pop.pricecutz.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by Pop H2 on 10/18/2016.
 * Pop Inc
 * Lagos Nigeria
 */

@Entity
public class Outlet {

    @Id
    Long id;

    long coy_id;

    String outlet_name;

    double outlet_latitude, outlet_longitude;

    @Index
    long outlet_created_time;

    @Index
    long outlet_updated_time;

    public Outlet() {
        long timeImMillis = System.currentTimeMillis();

        outlet_created_time = timeImMillis;
        outlet_updated_time = timeImMillis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCoy_id() {
        return coy_id;
    }

    public void setCoy_id(long coy_id) {
        this.coy_id = coy_id;
    }

    public String getOutlet_name() {
        return outlet_name;
    }

    public void setOutlet_name(String outlet_name) {
        this.outlet_name = outlet_name;
    }

    public double getOutlet_latitude() {
        return outlet_latitude;
    }

    public void setOutlet_latitude(double outlet_latitude) {
        this.outlet_latitude = outlet_latitude;
    }

    public double getOutlet_longitude() {
        return outlet_longitude;
    }

    public void setOutlet_longitude(double outlet_longitude) {
        this.outlet_longitude = outlet_longitude;
    }

    public long getOutlet_created_time() {
        return outlet_created_time;
    }

    public void setOutlet_created_time(long outlet_created_time) {
        this.outlet_created_time = outlet_created_time;
    }

    public long getOutlet_updated_time() {
        return outlet_updated_time;
    }

    public void setOutlet_updated_time(long outlet_updated_time) {
        this.outlet_updated_time = outlet_updated_time;
    }
}

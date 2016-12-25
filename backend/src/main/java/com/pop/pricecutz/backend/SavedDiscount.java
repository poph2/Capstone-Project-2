package com.pop.pricecutz.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by Pop H2 on 12/22/2016.
 * Pop Inc
 * Lagos Nigeria
 */

@Entity
public class SavedDiscount {

    @Id
    Long id;

    long user_id;

    long discount_id;

    long sdisc_created_time, sdisc_updated_time;

    public SavedDiscount() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(long discount_id) {
        this.discount_id = discount_id;
    }

    public long getSdisc_created_time() {
        return sdisc_created_time;
    }

    public void setSdisc_created_time(long sdisc_created_time) {
        this.sdisc_created_time = sdisc_created_time;
    }

    public long getSdisc_updated_time() {
        return sdisc_updated_time;
    }

    public void setSdisc_updated_time(long sdisc_updated_time) {
        this.sdisc_updated_time = sdisc_updated_time;
    }
}
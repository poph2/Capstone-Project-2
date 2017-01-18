package com.pop.pricecutz.data.mirrorbeans;

/**
 * Created by Pop H2 on 12/22/2016.
 * Pop Inc
 * Lagos Nigeria
 */

public class SavedDiscount {

    Long id;

    String fbacct_fb_id;

    long discount_id;

    String sdisc_status;

    long sdisc_created_time;

    long sdisc_updated_time;

    public SavedDiscount() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFbacct_fb_id() {
        return fbacct_fb_id;
    }

    public void setFbacct_fb_id(String fbacct_fb_id) {
        this.fbacct_fb_id = fbacct_fb_id;
    }

    public long getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(long discount_id) {
        this.discount_id = discount_id;
    }

    public String getSdisc_status() {
        return sdisc_status;
    }

    public void setSdisc_status(String sdisc_status) {
        this.sdisc_status = sdisc_status;
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

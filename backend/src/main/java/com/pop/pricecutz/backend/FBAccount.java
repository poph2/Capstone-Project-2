package com.pop.pricecutz.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by Pop H2 on 12/22/2016.
 * Pop Inc
 * Lagos Nigeria
 */

@Entity
public class FBAccount {

    @Id
    Long id;

    long user_id, fbacct_fb_id;

    String fbacct_first_name, fbacct_middle_name, fbacct_last_name, fbacct_name, fbacct_link_uri;

    long fbacct_created_time, fbacct_updated_time;

    public FBAccount() {}

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

    public long getFbacct_fb_id() {
        return fbacct_fb_id;
    }

    public void setFbacct_fb_id(long fbacct_fb_id) {
        this.fbacct_fb_id = fbacct_fb_id;
    }

    public String getFbacct_first_name() {
        return fbacct_first_name;
    }

    public void setFbacct_first_name(String fbacct_first_name) {
        this.fbacct_first_name = fbacct_first_name;
    }

    public String getFbacct_middle_name() {
        return fbacct_middle_name;
    }

    public void setFbacct_middle_name(String fbacct_middle_name) {
        this.fbacct_middle_name = fbacct_middle_name;
    }

    public String getFbacct_last_name() {
        return fbacct_last_name;
    }

    public void setFbacct_last_name(String fbacct_last_name) {
        this.fbacct_last_name = fbacct_last_name;
    }

    public String getFbacct_name() {
        return fbacct_name;
    }

    public void setFbacct_name(String fbacct_name) {
        this.fbacct_name = fbacct_name;
    }

    public String getFbacct_link_uri() {
        return fbacct_link_uri;
    }

    public void setFbacct_link_uri(String fbacct_link_uri) {
        this.fbacct_link_uri = fbacct_link_uri;
    }

    public long getFbacct_created_time() {
        return fbacct_created_time;
    }

    public void setFbacct_created_time(long fbacct_created_time) {
        this.fbacct_created_time = fbacct_created_time;
    }

    public long getFbacct_updated_time() {
        return fbacct_updated_time;
    }

    public void setFbacct_updated_time(long fbacct_updated_time) {
        this.fbacct_updated_time = fbacct_updated_time;
    }
}

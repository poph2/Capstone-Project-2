package com.pop.pricecutz.data.mirrorbeans;

/**
 * Created by Pop H2 on 12/22/2016.
 * Pop Inc
 * Lagos Nigeria
 */

public class User {

    Long id;

    String user_email;

    long user_created_time;

    long user_updated_time;

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public long getUser_created_time() {
        return user_created_time;
    }

    public void setUser_created_time(long user_created_time) {
        this.user_created_time = user_created_time;
    }

    public long getUser_updated_time() {
        return user_updated_time;
    }

    public void setUser_updated_time(long user_updated_time) {
        this.user_updated_time = user_updated_time;
    }
}

package com.pop.pricecutz.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by Pop H2 on 12/22/2016.
 * Pop Inc
 * Lagos Nigeria
 */
@Entity
public class User {

    @Id
    Long id;

    String user_email;

    @Index
    long user_created_time;

    @Index
    long user_updated_time;

    public User() {
        long timeImMillis = System.currentTimeMillis();

        user_created_time = timeImMillis;
        user_updated_time = timeImMillis;
    }

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

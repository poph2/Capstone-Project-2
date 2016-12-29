package com.pop.pricecutz.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by Pop H2 on 11/26/2016.
 * Pop Inc
 * Lagos Nigeria
 */

@Entity
public class Category {

    @Id
    Long id;

    @Index
    String cat_name;

    int cat_image_index;

    long cat_image_id;

    @Index
    long cat_created_time;

    @Index
    long cat_updated_time;

    public Category() {
        long timeImMillis = System.currentTimeMillis();

        cat_created_time = timeImMillis;
        cat_updated_time = timeImMillis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public int getCat_image_index() {
        return cat_image_index;
    }

    public void setCat_image_index(int cat_image_index) {
        this.cat_image_index = cat_image_index;
    }

    public long getImage_id() {
        return cat_image_id;
    }

    public void setImage_id(long cat_image_id) {
        this.cat_image_id = cat_image_id;
    }

    public long getCat_created_time() {
        return cat_created_time;
    }

    public void setCat_created_time(long cat_created_time) {
        this.cat_created_time = cat_created_time;
    }

    public long getCat_updated_time() {
        return cat_updated_time;
    }

    public void setCat_updated_time(long cat_updated_time) {
        this.cat_updated_time = cat_updated_time;
    }
}

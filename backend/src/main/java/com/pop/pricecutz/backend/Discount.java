package com.pop.pricecutz.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by Pop H2 on 9/30/2016.
 * Pop Inc
 * Lagos Nigeria
 */

@Entity
public class Discount {

    @Id
    Long id;

    String disc_code, disc_title, disc_description;

    long coy_id;

    int disc_type, disc_image_index;

    long disc_created_time;

    @Index
    long disc_updated_time;

    public Discount() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisc_code() {
        return disc_code;
    }

    public void setDisc_code(String disc_code) {
        this.disc_code = disc_code;
    }

    public String getDisc_title() {
        return disc_title;
    }

    public void setDisc_title(String disc_title) {
        this.disc_title = disc_title;
    }

    public String getDisc_description() {
        return disc_description;
    }

    public void setDisc_description(String disc_description) {
        this.disc_description = disc_description;
    }

    public long getCoy_id() {
        return coy_id;
    }

    public void setCoy_id(long coy_id) {
        this.coy_id = coy_id;
    }

    public int getDisc_type() {
        return disc_type;
    }

    public void setDisc_type(int disc_type) {
        this.disc_type = disc_type;
    }

    public int getDisc_image_index() {
        return disc_image_index;
    }

    public void setDisc_image_index(int disc_image_index) {
        this.disc_image_index = disc_image_index;
    }

    public long getDisc_created_time() {
        return disc_created_time;
    }

    public void setDisc_created_time(long disc_created_time) {
        this.disc_created_time = disc_created_time;
    }

    public long getDisc_updated_time() {
        return disc_updated_time;
    }

    public void setDisc_updated_time(long disc_updated_time) {
        this.disc_updated_time = disc_updated_time;
    }
}

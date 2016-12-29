package com.pop.pricecutz.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by Pop H2 on 9/6/2016.
 * Pop Inc
 * Lagos Nigeria
 */
@Entity
public class Company {

    @Id
    Long id;

    String coy_name, coy_industry, coy_image_url;

    @Index
    long category_id;

    long coy_image_id;

    @Index
    long coy_created_time;

    @Index
    long coy_updated_time;

    public Company() {
        long timeImMillis = System.currentTimeMillis();

        coy_created_time = timeImMillis;
        coy_updated_time = timeImMillis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoy_name() {
        return coy_name;
    }

    public void setCoy_name(String coy_name) {
        this.coy_name = coy_name;
    }

    public String getCoy_industry() {
        return coy_industry;
    }

    public void setCoy_industry(String coy_industry) {
        this.coy_industry = coy_industry;
    }

    public String getCoy_image_url() {
        return coy_image_url;
    }

    public void setCoy_image_url(String coy_image_url) {
        this.coy_image_url = coy_image_url;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public long getImage_id() {
        return coy_image_id;
    }

    public void setImage_id(long coy_image_id) {
        this.coy_image_id = coy_image_id;
    }

    public long getCoy_created_time() {
        return coy_created_time;
    }

    public void setCoy_created_time(long coy_created_time) {
        this.coy_created_time = coy_created_time;
    }

    public long getCoy_updated_time() {
        return coy_updated_time;
    }

    public void setCoy_updated_time(long coy_updated_time) {
        this.coy_updated_time = coy_updated_time;
    }
}

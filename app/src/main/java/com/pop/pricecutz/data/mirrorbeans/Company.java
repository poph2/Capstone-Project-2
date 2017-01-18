package com.pop.pricecutz.data.mirrorbeans;

import android.database.Cursor;

import com.pop.pricecutz.data.entries.CompanyEntry;
import com.pop.pricecutz.data.entries.DiscountEntry;

/**
 * Created by Pop H2 on 9/6/2016.
 * Pop Inc
 * Lagos Nigeria
 */

public class Company {

    long id;

    String coy_name, coy_industry, coy_image_url;

    long category_id;

    long coy_image_id;

    long coy_created_time;

    long coy_updated_time;

    public Company() {}

    public void update(Cursor c, String idColName) {
        this.setId(c.getLong(c.getColumnIndex(idColName)));
        this.setCoy_name(c.getString(c.getColumnIndex(CompanyEntry.COLUMN_NAME)));
        this.setCoy_industry(c.getString(c.getColumnIndex(CompanyEntry.COLUMN_INDUSTRY)));
        this.setCoy_image_url(c.getString(c.getColumnIndex(CompanyEntry.COLUMN_IMAGE_URL)));
        this.setCategory_id(c.getLong(c.getColumnIndex(CompanyEntry.COLUMN_CATEGORY_ID)));
        this.setImage_id(c.getLong(c.getColumnIndex(CompanyEntry.COLUMN_IMAGE_ID)));
        this.setCoy_created_time(c.getLong(c.getColumnIndex(CompanyEntry.COLUMN_CREATED_TIME)));
        this.setCoy_updated_time(c.getLong(c.getColumnIndex(CompanyEntry.COLUMN_UPDATED_TIME)));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

package com.pop.pricecutz;

import com.pop.pricecutz.Data;

/**
 * Created by Pop H2 on 9/6/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class Company {

    long id;

    String name, industry, imageURL;

    public Company() {}

    public Company(long index) {

        this.id         = (long)index;
        this.name       = Data.name[(int)index - 1];
        this.industry   = Data.industry[(int)index  - 1];
        this.imageURL   = Data.image_url[(int)index - 1];

    }

    public Company(long id, String name, String industry, String imageURL) {

        this.id         = id;
        this.name       = name;
        this.industry   = industry;
        this.imageURL   = imageURL;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}

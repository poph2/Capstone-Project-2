package com.pop.pricecutz;

import com.pop.pricecutz.Data;

/**
 * Created by Pop H2 on 9/6/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class Company {

    int id;

    String name, industry, imageURL;

    public Company(int index) {

//        this(Data.name[index], Data.industry[index], Data.image_url[index]);
        this.id         = index;
        this.name       = Data.name[index];
        this.industry   = Data.industry[index];
        this.imageURL   = Data.image_url[index];

    }

    public Company(int id, String name, String industry, String imageURL) {

        this.id         = id;
        this.name       = name;
        this.industry   = industry;
        this.imageURL   = imageURL;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

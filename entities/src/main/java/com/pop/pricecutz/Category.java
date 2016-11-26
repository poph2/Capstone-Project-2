package com.pop.pricecutz;

/**
 * Created by adeniyi.bello on 11/26/2016.
 */

public class Category {

    int id;

    String name, imageName;

    public Category(int id, String imageName, String name) {
        this.id = id;
        this.imageName = imageName;
        this.name = name;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}

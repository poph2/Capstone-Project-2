package com.pop.pricecutz;

import java.util.Random;

/**
 * Created by adeniyi.bello on 11/26/2016.
 */

public class Category {

    long id;

    String name, imageName;

    int imageIndex;

    boolean selected;

    public Category() {}

    public Category(int id, String imageName, String name) {
        this.id = id;
        this.imageName = imageName;
        this.name = name;

        Random r = new Random();
        this.imageIndex = r.nextInt(99);

        selected = false;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getImageIndex() {
        return imageIndex;
    }

    public void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}

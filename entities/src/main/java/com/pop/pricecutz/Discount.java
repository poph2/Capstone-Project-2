package com.pop.pricecutz;

import java.util.Random;

/**
 * Created by Pop H2 on 9/30/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class Discount {

    long id;
    String code;
    String title;
    String description;
    long company_id;
    int type;
    int imageIndex;

    boolean savedByUser;


    public static final int SALE_TYPE = 0;
    public static final int COUPON_TYPE = 1;

    public Discount() {}

    public Discount(long id, String code, String title, String description, long company_id, int type) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.description = description;
        this.company_id = company_id;
        this.type = type;

        Random r = new Random();
        imageIndex = r.nextInt(99);

        savedByUser = true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeStr() {
        switch (type) {
            case COUPON_TYPE:
                return "Coupon";
            case SALE_TYPE:
                return "Sale";
            default:
                return Integer.toString(type);
        }
    }

    public int getImageIndex() {
        return imageIndex;
    }

    public void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
    }

    public boolean isSavedByUser() {
        return savedByUser;
    }

    public void setSavedByUser(boolean savedByUser) {
        this.savedByUser = savedByUser;
    }
}

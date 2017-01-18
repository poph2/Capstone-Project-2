package com.pop.pricecutz.data.mirrorbeans;

/**
 * Created by Pop H2 on 9/30/2016.
 * Pop Inc
 * Lagos Nigeria
 */

public class Discount {


    Long id;

    String disc_code;

    String disc_title;

    String disc_description;

    long coy_id;

    int disc_type, disc_image_index;

    String disc_status;

    int disc_issued_bal;
    int disc_available_bal;
    int disc_reserved_bal;
    int disc_used_bal;

    long disc_image_id;

    long disc_expiry_time;

    long disc_expiry_time_zone;

    long disc_created_time;

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

    public String getDisc_status() {
        return disc_status;
    }

    public void setDisc_status(String disc_status) {
        this.disc_status = disc_status;
    }

    public long getDisc_image_id() {
        return disc_image_id;
    }

    public void setDisc_image_id(long disc_image_id) {
        this.disc_image_id = disc_image_id;
    }

    public long getDisc_expiry_time() {
        return disc_expiry_time;
    }

    public void setDisc_expiry_time(long disc_expiry_time) {
        this.disc_expiry_time = disc_expiry_time;
    }

    public long getDisc_expiry_time_zone() {
        return disc_expiry_time_zone;
    }

    public void setDisc_expiry_time_zone(long disc_expiry_time_zone) {
        this.disc_expiry_time_zone = disc_expiry_time_zone;
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

    public long getImage_id() {
        return disc_image_id;
    }

    public void setImage_id(long disc_image_id) {
        this.disc_image_id = disc_image_id;
    }

    public int getDisc_issued_bal() {
        return disc_issued_bal;
    }

    public void setDisc_issued_bal(int disc_issued_bal) {
        this.disc_issued_bal = disc_issued_bal;
    }

    public int getDisc_available_bal() {
        return disc_available_bal;
    }

    public void setDisc_available_bal(int disc_available_bal) {
        this.disc_available_bal = disc_available_bal;
    }

    public int getDisc_reserved_bal() {
        return disc_reserved_bal;
    }

    public void setDisc_reserved_bal(int disc_reserved_bal) {
        this.disc_reserved_bal = disc_reserved_bal;
    }

    public int getDisc_used_bal() {
        return disc_used_bal;
    }

    public void setDisc_used_bal(int disc_used_bal) {
        this.disc_used_bal = disc_used_bal;
    }
}

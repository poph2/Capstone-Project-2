package com.pop.pricecutz.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.sql.Blob;

/**
 * Created by Pop H2 on 12/29/2016.
 * Pop Inc
 * Lagos Nigeria
 */

@Entity
public class Image {

    @Id
    long id;

    Blob blob;

    public Image() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Blob getBlob() {
        return blob;
    }

    public void setBlob(Blob blob) {
        this.blob = blob;
    }

//    byte[] byteArray = DBcursor.getBlob(columnIndex);
//
//    Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length);
}

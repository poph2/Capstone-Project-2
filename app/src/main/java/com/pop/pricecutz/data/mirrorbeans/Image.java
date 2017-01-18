package com.pop.pricecutz.data.mirrorbeans;

import java.sql.Blob;

/**
 * Created by Pop H2 on 12/29/2016.
 * Pop Inc
 * Lagos Nigeria
 */

public class Image {

    long id;

    Blob blob;

    public Image(Blob blob) {
        this.blob = blob;
    }

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

package com.pop.pricecutz.data.entries;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.provider.BaseColumns;

import com.pop.pricecutz.Category;
import com.pop.pricecutz.data.PriceCutzContract;

/**
 * Created by adeniyi.bello on 11/26/2016.
 */

public class CategoryEntry extends Entry {

    public static final String PATH         = "category";
    public static final String TABLE_NAME   = "category";

    public static final Uri CONTENT_URI     = PriceCutzContract.BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();

    public static final String CONTENT_TYPE         = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;
    public static final String CONTENT_ITEM_TYPE    = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;

    public static final String COLUMN_CAT_ID        = "category_id";
    public static final String COLUMN_NAME          = "name";
    public static final String COLUMN_IMAGE_NAME    = "image_name";

    public static String createTableSQL() {
        String createTableSQL = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID                + " INTEGER PRIMARY KEY," +
                COLUMN_CAT_ID      + " INTEGER UNIQUE NOT NULL, " +
                COLUMN_NAME        + " TEXT NOT NULL, " +
                COLUMN_IMAGE_NAME  + " TEXT NOT NULL, " +
                " );";

        return createTableSQL;
    }

    public static ContentValues getContentValues(Category c) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_CAT_ID,        c.getId());
        contentValues.put(COLUMN_NAME,          c.getName());
        contentValues.put(COLUMN_IMAGE_NAME,    c.getImageName());

        return contentValues;
    }
}

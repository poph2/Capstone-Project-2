package com.pop.pricecutz.data.entries;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import com.pop.pricecutz.Category;
import com.pop.pricecutz.data.PriceCutzContract;

/**
 * Created by adeniyi.bello on 11/26/2016.
 */

public class CategoryEntry implements BaseColumns {

    public static final String PATH         = "category";
    public static final String TABLE_NAME   = "category";

    public static final Uri CONTENT_URI     = PriceCutzContract.BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();

    public static final String CONTENT_TYPE         = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;
    public static final String CONTENT_ITEM_TYPE    = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;

//    public static final String COLUMN_CAT_ID        = "category_id";
    public static final String COLUMN_NAME          = "name";
    public static final String COLUMN_IMAGE_NAME    = "image_name";
    public static final String COLUMN_IMAGE_INDEX   = "image_index";

    public static String createTableSQL() {
        String createTableSQL = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID                + " INTEGER PRIMARY KEY," +
//                COLUMN_CAT_ID      + " INTEGER UNIQUE NOT NULL, " +
                COLUMN_NAME        + " TEXT NOT NULL, " +
                COLUMN_IMAGE_NAME  + " TEXT NOT NULL, " +
                COLUMN_IMAGE_INDEX + " INTEGER NOT NULL " +
                " );";

        return createTableSQL;
    }

    public static ContentValues getContentValues(Category c) {
        ContentValues contentValues = new ContentValues();

//        contentValues.put(COLUMN_CAT_ID,        c.getId());
        contentValues.put(COLUMN_NAME,          c.getName());
        contentValues.put(COLUMN_IMAGE_NAME,    c.getImageName());
        contentValues.put(COLUMN_IMAGE_INDEX,   c.getImageIndex());

        return contentValues;
    }

    public static Uri buildUri(long id) {
        return Entry.buildUri(id);
    }

    public static String dropTableSQL() {
        return Entry.dropTableSQL();
    }

    public static Uri insert(SQLiteDatabase db, ContentValues values, Uri uri) {
        return Entry.insert(db, values, uri);
    }

    public static int bulkInsert(SQLiteDatabase db, ContentValues[] valuesArr) {
        return Entry.bulkInsert(db, valuesArr);
    }
}

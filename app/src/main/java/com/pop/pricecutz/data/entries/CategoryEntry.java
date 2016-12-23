package com.pop.pricecutz.data.entries;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import com.pop.pricecutz.backend.categoryApi.model.Category;
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

    public static final String COLUMN_NAME          = "cat_name";
    public static final String COLUMN_IMAGE_NAME    = "cat_image_name";
    public static final String COLUMN_IMAGE_INDEX   = "cat_image_index";
    public static final String COLUMN_CREATED_TIME  = "cat_created_time";
    public static final String COLUMN_UPDATED_TIME  = "cat_updated_time";

    public static String createTableSQL() {
        String createTableSQL = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID                 + " INTEGER PRIMARY KEY," +
                COLUMN_NAME         + " TEXT NOT NULL, " +
                COLUMN_IMAGE_NAME   + " TEXT NOT NULL, " +
                COLUMN_IMAGE_INDEX  + " INTEGER NOT NULL, " +
                COLUMN_CREATED_TIME + " INTEGER NOT NULL, " +
                COLUMN_UPDATED_TIME + " INTEGER NOT NULL " +
                " );";

        return createTableSQL;
    }

    public static ContentValues getContentValues(Category c) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(_ID,                  c.getId());
        contentValues.put(COLUMN_NAME,          c.getCatName());
        contentValues.put(COLUMN_IMAGE_NAME,    c.getCatImageName());
        contentValues.put(COLUMN_IMAGE_INDEX,   c.getCatImageIndex());
        contentValues.put(COLUMN_CREATED_TIME,  c.getCatCreatedTime());
        contentValues.put(COLUMN_UPDATED_TIME,  c.getCatUpdatedTime());

        return contentValues;
    }

    public static Uri buildUri(long id) {
        return ContentUris.withAppendedId(CONTENT_URI, id);
    }

    public static String dropTableSQL() {
        String dropTableSQL = "DROP TABLE IF EXISTS " + TABLE_NAME;
        return dropTableSQL;
    }

    public static Uri insert(SQLiteDatabase db, ContentValues values, Uri uri) {
        long _id = db.insert(TABLE_NAME, null, values);

        Uri returnUri = null;

        if ( _id > 0 )
            returnUri = buildUri(_id);
        else
            throw new android.database.SQLException("Failed to insert row into " + uri);

        return returnUri;
    }

    public static int bulkInsert(SQLiteDatabase db, ContentValues[] valuesArr) {
        db.beginTransaction();
        int returnCount = 0;
        try {
            for (ContentValues values : valuesArr) {

                long _id = db.insert(TABLE_NAME, null, values);
                if (_id != -1) {
                    returnCount++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return returnCount;
    }
}












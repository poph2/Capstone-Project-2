package com.pop.pricecutz.data.entries;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import com.pop.pricecutz.backend.companyApi.model.Company;
import com.pop.pricecutz.data.PriceCutzContract;

/**
 * Created by Pop H2 on 9/6/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class CompanyEntry implements BaseColumns {

    public static final String PATH         = "company";
    public static final String TABLE_NAME   = "company";

    public static final Uri CONTENT_URI     = PriceCutzContract.BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();

    public static final String CONTENT_TYPE         = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;
    public static final String CONTENT_ITEM_TYPE    = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;

    public static final String COLUMN_APPENGINE_ID  = "coy_app_engine_id";
    public static final String COLUMN_NAME          = "coy_name";
    public static final String COLUMN_INDUSTRY      = "coy_industry";
    public static final String COLUMN_IMAGE_URL     = "coy_image_url";
    public static final String COLUMN_IMAGE_ID      = "coy_image_id";
    public static final String COLUMN_CREATED_TIME  = "coy_created_time";
    public static final String COLUMN_UPDATED_TIME  = "coy_updated_time";

    public static String createTableSQL() {
        String createTableSQL = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID                 + " INTEGER PRIMARY KEY," +
                COLUMN_NAME         + " TEXT, " +
                COLUMN_INDUSTRY     + " TEXT, " +
                COLUMN_IMAGE_URL    + " TEXT, " +
                COLUMN_IMAGE_ID     + " INTEGER, " +
                COLUMN_CREATED_TIME + " INTEGER, " +
                COLUMN_UPDATED_TIME + " INTEGER " +
                " );";

        return createTableSQL;
    }

    public static ContentValues getContentValues(Company c) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(_ID,                  c.getId());
        contentValues.put(COLUMN_NAME,          c.getCoyName());
        contentValues.put(COLUMN_INDUSTRY,      c.getCoyIndustry());
        contentValues.put(COLUMN_IMAGE_URL,     c.getCoyImageUrl());
        contentValues.put(COLUMN_CREATED_TIME,  c.getCoyCreatedTime());
        contentValues.put(COLUMN_IMAGE_ID,      c.getImageId());
        contentValues.put(COLUMN_UPDATED_TIME,  c.getCoyUpdatedTime());

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

    public static int bulkUpdate(SQLiteDatabase db, ContentValues[] valuesArr) {
        db.beginTransaction();
        int returnCount = 0;
        try {
            for (ContentValues values : valuesArr) {

                long _id = db.update(TABLE_NAME, values, _ID + " = " + values.getAsString("ID"), null);
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

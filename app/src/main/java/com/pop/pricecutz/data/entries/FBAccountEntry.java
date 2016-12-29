package com.pop.pricecutz.data.entries;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import com.pop.pricecutz.backend.fBAccountApi.model.FBAccount;
import com.pop.pricecutz.data.PriceCutzContract;

/**
 * Created by Pop H2 on 12/22/2016.
 * Pop Inc
 * Lagos Nigeria
 */

public class FBAccountEntry implements BaseColumns {

    public static final String PATH         = "fb_account";
    public static final String TABLE_NAME   = "fb_account";

    public static final Uri CONTENT_URI     = PriceCutzContract.BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();

    public static final String CONTENT_TYPE         = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;
    public static final String CONTENT_ITEM_TYPE    = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;

    public static final String COLUMN_FB_USER_ID    = "fbacct_fb_id";
    public static final String COLUMN_FIRST_NAME    = "fbacct_first_name";
    public static final String COLUMN_MIDDLE_NAME   = "fbacct_middle_name";
    public static final String COLUMN_LAST_NAME     = "fbacct_last_name";
    public static final String COLUMN_NAME          = "fbacct_name";
    public static final String COLUMN_LINK_URL      = "fbacct_link_uri";
    public static final String COLUMN_EMAIL         = "fbacct_email";
    public static final String COLUMN_GENDER        = "fbacct_gender";
    public static final String COLUMN_CREATED_TIME  = "fbacct_created_time";
    public static final String COLUMN_UPDATED_TIME  = "fbacct_updated_time";

    public static String createTableSQL() {
        String createTableSQL = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID                 + " INTEGER PRIMARY KEY," +
                COLUMN_FB_USER_ID   + " INTEGER, " +
                COLUMN_FIRST_NAME   + " TEXT, " +
                COLUMN_MIDDLE_NAME  + " TEXT, " +
                COLUMN_LAST_NAME    + " TEXT, " +
                COLUMN_NAME         + " TEXT, " +
                COLUMN_LINK_URL     + " TEXT, " +
                COLUMN_EMAIL        + " TEXT, " +
                COLUMN_GENDER       + " TEXT, " +
                COLUMN_CREATED_TIME + " INTEGER, " +
                COLUMN_UPDATED_TIME + " INTEGER " +
                " );";

        return createTableSQL;
    }

    public static ContentValues getContentValues(FBAccount o) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(_ID,                  o.getId());
        contentValues.put(COLUMN_FB_USER_ID,    o.getFbacctFbId());
        contentValues.put(COLUMN_FIRST_NAME,    o.getFbacctFirstName());
        contentValues.put(COLUMN_MIDDLE_NAME,   o.getFbacctMiddleName());
        contentValues.put(COLUMN_LAST_NAME,     o.getFbacctLastName());
        contentValues.put(COLUMN_NAME,          o.getFbacctName());
        contentValues.put(COLUMN_LINK_URL,      o.getFbacctLinkUri());
        contentValues.put(COLUMN_EMAIL,         o.getFbacctEmail());
        contentValues.put(COLUMN_GENDER,        o.getFbacctGender());
        contentValues.put(COLUMN_CREATED_TIME,  o.getFbacctCreatedTime());
        contentValues.put(COLUMN_UPDATED_TIME,  o.getFbacctUpdatedTime());

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

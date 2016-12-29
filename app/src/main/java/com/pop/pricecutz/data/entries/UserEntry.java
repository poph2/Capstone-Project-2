package com.pop.pricecutz.data.entries;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import com.pop.pricecutz.backend.userApi.model.User;
import com.pop.pricecutz.data.PriceCutzContract;

/**
 * Created by Pop H2 on 12/22/2016.
 * Pop Inc
 * Lagos Nigeria
 */

public class UserEntry implements BaseColumns {

    public static final String PATH         = "fb_account";
    public static final String TABLE_NAME   = "fb_account";

    public static final Uri CONTENT_URI     = PriceCutzContract.BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();

    public static final String CONTENT_TYPE         = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;
    public static final String CONTENT_ITEM_TYPE    = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;

    public static final String COLUMN_EMAIL         = "user_email";
    public static final String COLUMN_CREATED_TIME  = "user_created_time";
    public static final String COLUMN_UPDATED_TIME  = "user_updated_time";

    Long id;

    String user_email;

    public static String createTableSQL() {
        String createTableSQL = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID                 + " INTEGER PRIMARY KEY," +
                COLUMN_EMAIL        + " TEXT NOT NULL, " +
                COLUMN_CREATED_TIME + " INTEGER NOT NULL, " +
                COLUMN_UPDATED_TIME + " INTEGER NOT NULL " +
                " );";

        return createTableSQL;
    }

    public static ContentValues getContentValues(User c) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(_ID,                  c.getId());
        contentValues.put(COLUMN_EMAIL,         c.getUserEmail());
        contentValues.put(COLUMN_CREATED_TIME,  c.getUserCreatedTime());
        contentValues.put(COLUMN_UPDATED_TIME,  c.getUserUpdatedTime());

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

    public static int bulkUpdate(SQLiteDatabase db, ContentValues[] valuesArr, String whereClause, String[] whereArgs) {
        db.beginTransaction();
        int returnCount = 0;
        try {
            for (ContentValues values : valuesArr) {

                long _id = db.update(TABLE_NAME, values, whereClause, whereArgs);
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

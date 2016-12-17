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
 * Created by Pop H2 on 12/17/2016.
 * Pop Inc
 * Lagos Nigeria
 */

public class Entry implements BaseColumns {

    public static final String PATH         = "";
    public static final String TABLE_NAME   = "";

    public static final Uri CONTENT_URI     = null;

    public static final String CONTENT_TYPE         = null;
    public static final String CONTENT_ITEM_TYPE    = null;

    //Methods to implement in subclasses
    public static String createTableSQL() {
        return null;
    };

    public static ContentValues getContentValues() {
        return null;
    }

    //Methods not to implement
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

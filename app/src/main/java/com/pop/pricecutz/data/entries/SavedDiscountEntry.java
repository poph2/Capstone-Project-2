package com.pop.pricecutz.data.entries;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import com.pop.pricecutz.backend.categoryApi.model.Category;
import com.pop.pricecutz.backend.savedDiscountApi.model.SavedDiscount;
import com.pop.pricecutz.data.PriceCutzContract;

/**
 * Created by Pop H2 on 12/22/2016.
 * Pop Inc
 * Lagos Nigeria
 */

public class SavedDiscountEntry implements BaseColumns {

    public static final String PATH         = "saved_discount";
    public static final String TABLE_NAME   = "saved_discount";

    public static final Uri CONTENT_URI     = PriceCutzContract.BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();

    public static final String CONTENT_TYPE         = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;
    public static final String CONTENT_ITEM_TYPE    = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;

    public static final String COLUMN_USER_ID       = "user_id";
    public static final String COLUMN_DISCOUNT_ID   = "discount_id";
    public static final String COLUMN_CREATED_TIME  = "sdisc_created_time";
    public static final String COLUMN_UPDATED_TIME  = "sdisc_updated_time";


    public static String createTableSQL() {
        String createTableSQL = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID                 + " INTEGER PRIMARY KEY," +
                COLUMN_USER_ID      + " INTEGER NOT NULL, " +
                COLUMN_DISCOUNT_ID  + " INTEGER NOT NULL, " +
                COLUMN_CREATED_TIME + " INTEGER NOT NULL, " +
                COLUMN_UPDATED_TIME + " INTEGER NOT NULL " +
                " );";

        return createTableSQL;
    }

    public static ContentValues getContentValues(SavedDiscount d) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(_ID,                  d.getId());
        contentValues.put(COLUMN_USER_ID,       d.getUserId());
        contentValues.put(COLUMN_DISCOUNT_ID,   d.getDiscountId());
        contentValues.put(COLUMN_CREATED_TIME,  d.getSdiscCreatedTime());
        contentValues.put(COLUMN_UPDATED_TIME,  d.getSdiscUpdatedTime());

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

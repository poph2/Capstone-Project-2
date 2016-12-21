package com.pop.pricecutz.data.entries;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import com.pop.pricecutz.Company;
import com.pop.pricecutz.Discount;
import com.pop.pricecutz.data.PriceCutzContract;

/**
 * Created by Pop H2 on 9/30/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class DiscountEntry implements BaseColumns {

//    public static final int SALE_TYPE = 0;
//    public static final int COUPON_TYPE = 1;


    public static final String PATH         = "discount";
    public static final String TABLE_NAME   = "discount";

    public static final Uri CONTENT_URI = PriceCutzContract.BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();

    public static final String CONTENT_TYPE         = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;
    public static final String CONTENT_ITEM_TYPE    = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;

//    public static final String COLUMN_DISCOUNT_ID   = "discount_id";
    public static final String COLUMN_CODE          = "code";
    public static final String COLUMN_TITLE         = "title";
    public static final String COLUMN_DESCRIPTION   = "description";
    public static final String COLUMN_COY_ID        = "coy_id";
    public static final String COLUMN_TYPE          = "type";
    public static final String COLUMN_IMAGE_INDEX   = "image_index";
//    public static final String COLUMN_SAVED_BY_USER = "saved_by_user";

//    public static Uri buildUri(long id) {
//        return ContentUris.withAppendedId(CONTENT_URI, id);
//    }

    public static String createTableSQL() {
        String createTableSQL = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID                 + " INTEGER PRIMARY KEY," +
//                COLUMN_DISCOUNT_ID  + " INTEGER UNIQUE NOT NULL, " +
                COLUMN_CODE         + " TEXT NOT NULL, " +
                COLUMN_TITLE        + " TEXT NOT NULL, " +
                COLUMN_DESCRIPTION  + " TEXT NOT NULL, " +
                COLUMN_COY_ID       + " INTEGER NOT NULL, " +
                COLUMN_TYPE         + " TEXT NOT NULL, " +
                COLUMN_IMAGE_INDEX  + " INTEGER NOT NULL " +
//                COLUMN_SAVED_BY_USER    + " TEXT NOT NULL " +
                " );";

        return createTableSQL;
    }

    public static ContentValues getContentValues(Discount d) {
        ContentValues contentValues = new ContentValues();

//        contentValues.put(COLUMN_DISCOUNT_ID,   d.getId());
        contentValues.put(COLUMN_CODE,          d.getCode());
        contentValues.put(COLUMN_TITLE,         d.getTitle());
        contentValues.put(COLUMN_DESCRIPTION,   d.getDescription());
        contentValues.put(COLUMN_COY_ID,        d.getCompany_id());
        contentValues.put(COLUMN_TYPE,          d.getType());
        contentValues.put(COLUMN_IMAGE_INDEX,   d.getImageIndex());
//        contentValues.put(COLUMN_SAVED_BY_USER, d.isSavedByUser());

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
                try {
                    long _id = db.insert(TABLE_NAME, null, values);
                    if (_id != -1) {
                        returnCount++;
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return returnCount;
    }

}

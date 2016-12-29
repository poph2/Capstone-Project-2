package com.pop.pricecutz.data.entries;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import com.pop.pricecutz.backend.discountApi.model.Discount;
import com.pop.pricecutz.data.PriceCutzContract;

/**
 * Created by Pop H2 on 9/30/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class DiscountEntry implements BaseColumns {

//    public static final int SALE_TYPE = 0;
//    public static final int COUPON_TYPE = 1;


    public static final String PATH                     = "discount";
    public static final String PATH_WITH_COMPANY        = "discount_with_company";
    public static final String PATH_SAVED_WITH_COMPANY  = "saved_discount_with_company";
    public static final String TABLE_NAME               = "discount";

    public static final Uri CONTENT_URI                     = PriceCutzContract.BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();
    public static final Uri CONTENT_URI_WITH_COMPANY        = PriceCutzContract.BASE_CONTENT_URI.buildUpon().appendPath(PATH_WITH_COMPANY).build();
    public static final Uri CONTENT_URI_SAVED_WITH_COMPANY  = PriceCutzContract.BASE_CONTENT_URI.buildUpon().appendPath(PATH_SAVED_WITH_COMPANY).build();

    public static final String CONTENT_TYPE         = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;
    public static final String CONTENT_ITEM_TYPE    = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;

    public static final String COLUMN_CODE              = "disc_code";
    public static final String COLUMN_TITLE             = "disc_title";
    public static final String COLUMN_DESCRIPTION       = "disc_description";
    public static final String COLUMN_COY_ID            = "coy_id";
    public static final String COLUMN_TYPE              = "disc_type";
    public static final String COLUMN_IMAGE_INDEX       = "disc_image_index";
    public static final String COLUMN_STATUS            = "disc_status";
    public static final String COLUMN_IMAGE_ID          = "disc_image_id";
    public static final String COLUMN_EXPIRY_TIME       = "disc_expiry_time";
    public static final String COLUMN_EXPIRY_TIMEZONE   = "disc_expiry_time_zone";
    public static final String COLUMN_CREATED_TIME      = "disc_created_time";
    public static final String COLUMN_UPDATED_TIME      = "disc_updated_time";

    public static String createTableSQL() {
        String createTableSQL = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID                     + " INTEGER PRIMARY KEY," +
                COLUMN_CODE             + " TEXT, " +
                COLUMN_TITLE            + " TEXT, " +
                COLUMN_DESCRIPTION      + " TEXT, " +
                COLUMN_COY_ID           + " INTEGER, " +
                COLUMN_TYPE             + " TEXT, " +
                COLUMN_IMAGE_INDEX      + " INTEGER, " +
                COLUMN_STATUS           + " TEXT, " +
                COLUMN_IMAGE_ID         + " INTEGER, " +
                COLUMN_EXPIRY_TIME      + " INTEGER, " +
                COLUMN_EXPIRY_TIMEZONE  + " TEXT, " +
                COLUMN_CREATED_TIME     + " INTEGER, " +
                COLUMN_UPDATED_TIME     + " INTEGER " +
                " );";

        return createTableSQL;
    }

    public static ContentValues getContentValues(Discount d) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(_ID,                      d.getId());
        contentValues.put(COLUMN_CODE,              d.getDiscCode());
        contentValues.put(COLUMN_TITLE,             d.getDiscTitle());
        contentValues.put(COLUMN_DESCRIPTION,       d.getDiscDescription());
        contentValues.put(COLUMN_COY_ID,            d.getCoyId());
        contentValues.put(COLUMN_TYPE,              d.getDiscType());
        contentValues.put(COLUMN_IMAGE_INDEX,       d.getDiscImageIndex());
        contentValues.put(COLUMN_STATUS,            d.getDiscStatus());
        contentValues.put(COLUMN_IMAGE_ID,          d.getDiscImageId());
        contentValues.put(COLUMN_EXPIRY_TIME,       d.getDiscExpiryTime());
        contentValues.put(COLUMN_EXPIRY_TIMEZONE,   d.getDiscExpiryTimeZone());
        contentValues.put(COLUMN_CREATED_TIME,      d.getDiscCreatedTime());
        contentValues.put(COLUMN_UPDATED_TIME,      d.getDiscUpdatedTime());

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

        //ContentValues values = getContentValues(d);

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

package com.pop.pricecutz.data.entries;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import com.pop.pricecutz.backend.outletApi.model.Outlet;
import com.pop.pricecutz.data.PriceCutzContract;

/**
 * Created by Pop H2 on 10/18/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class OutletEntry implements BaseColumns {

    public static final String PATH         = "outlet";
    public static final String TABLE_NAME   = "outlet";

    public static final Uri CONTENT_URI = PriceCutzContract.BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();

    public static final String CONTENT_TYPE         = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;
    public static final String CONTENT_ITEM_TYPE    = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;

    public static final String COLUMN_COY_ID        = "coy_id";
    public static final String COLUMN_NAME          = "outlet_name";
    public static final String COLUMN_LATITUDE      = "outlet_latitude";
    public static final String COLUMN_LONGITUDE     = "outlet_longitude";
    public static final String COLUMN_CREATED_TIME  = "outlet_created_time";
    public static final String COLUMN_UPDATED_TIME  = "outlet_updated_time";

    public static String createTableSQL() {
        String createTableSQL = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID                 + " INTEGER PRIMARY KEY," +
                COLUMN_COY_ID       + " INTEGER NOT NULL, " +
                COLUMN_LATITUDE     + " REAL NOT NULL, " +
                COLUMN_LONGITUDE    + " REAL NOT NULL, " +
                COLUMN_CREATED_TIME + " INTEGER NOT NULL, " +
                COLUMN_UPDATED_TIME + " INTEGER NOT NULL " +
                " );";

        return createTableSQL;
    }

    public static ContentValues getContentValues(Outlet o) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(_ID,                  o.getId());
        contentValues.put(COLUMN_COY_ID,        o.getCoyId());
        contentValues.put(COLUMN_LATITUDE,      o.getOutletLatitude());
        contentValues.put(COLUMN_LONGITUDE,     o.getOutletLongitude());
        contentValues.put(COLUMN_CREATED_TIME,  o.getOutletCreatedTime());
        contentValues.put(COLUMN_UPDATED_TIME,  o.getOutletUpdatedTime());

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

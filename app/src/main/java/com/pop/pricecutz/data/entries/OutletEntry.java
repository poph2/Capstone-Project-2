package com.pop.pricecutz.data.entries;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.provider.BaseColumns;

import com.pop.pricecutz.Discount;
import com.pop.pricecutz.Outlet;
import com.pop.pricecutz.data.PriceCutzContract;
import com.pop.pricecutz.map.GPSPoint;

/**
 * Created by Pop H2 on 10/18/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class OutletEntry extends Entry {

    public static final String PATH         = "outlet";
    public static final String TABLE_NAME   = "outlet";

    public static final Uri CONTENT_URI = PriceCutzContract.BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();

    public static final String CONTENT_TYPE         = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;
    public static final String CONTENT_ITEM_TYPE    = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;

    public static final String COLUMN_OUTLET_ID     = "outlet_id";
    public static final String COLUMN_COY_ID        = "coy_id";
    public static final String COLUMN_LATITUDE      = "latitude";
    public static final String COLUMN_LONGITUDE     = "longitude";

//    public static Uri buildUri(long id) {
//        return ContentUris.withAppendedId(CONTENT_URI, id);
//    }

    public static String createTableSQL() {
        String createTableSQL = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID                 + " INTEGER PRIMARY KEY," +
                COLUMN_OUTLET_ID    + " INTEGER UNIQUE NOT NULL, " +
                COLUMN_COY_ID       + " INTEGER NOT NULL, " +
                COLUMN_LATITUDE     + " TEXT NOT NULL, " +
                COLUMN_LONGITUDE    + " TEXT NOT NULL, " +
                " );";

        return createTableSQL;
    }

    public static ContentValues getContentValues(Outlet o) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_OUTLET_ID, o.getId());
        contentValues.put(COLUMN_COY_ID,    o.getCompany_id());
        contentValues.put(COLUMN_LATITUDE,  o.getLatitude());
        contentValues.put(COLUMN_LONGITUDE, o.getLongitude());

        return contentValues;
    }
}

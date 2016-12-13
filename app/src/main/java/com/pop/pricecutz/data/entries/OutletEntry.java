package com.pop.pricecutz.data.entries;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.pop.pricecutz.data.PriceCutzContract;
import com.pop.pricecutz.map.GPSPoint;

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

    public static final String COLUMN_OUTLET_ID     = "outlet_id";
    public static final String COLUMN_LATITUDE      = "latitude";
    public static final String COLUMN_LONGITUDE     = "longitude";

    public static Uri buildUri(long id) {
        return ContentUris.withAppendedId(CONTENT_URI, id);
    }

}

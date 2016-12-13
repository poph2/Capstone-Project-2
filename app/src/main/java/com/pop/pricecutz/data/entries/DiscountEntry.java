package com.pop.pricecutz.data.entries;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.pop.pricecutz.data.PriceCutzContract;

/**
 * Created by Pop H2 on 9/30/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class DiscountEntry  implements BaseColumns {

//    public static final int SALE_TYPE = 0;
//    public static final int COUPON_TYPE = 1;


    public static final String PATH         = "discount";
    public static final String TABLE_NAME   = "discount";

    public static final Uri CONTENT_URI = PriceCutzContract.BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();

    public static final String CONTENT_TYPE         = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;
    public static final String CONTENT_ITEM_TYPE    = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;

    public static final String COLUMN_DISCOUNT_ID   = "discount_id";
    public static final String COLUMN_CODE          = "code";
    public static final String COLUMN_TITLE         = "title";
    public static final String COLUMN_DESCRIPTION   = "description";
    public static final String COLUMN_COY_ID        = "coy_id";
    public static final String COLUMN_TYPE          = "type";

    public static Uri buildUri(long id) {
        return ContentUris.withAppendedId(CONTENT_URI, id);
    }

}

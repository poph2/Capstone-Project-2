package com.pop.pricecutz.data.entries;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.pop.pricecutz.data.PriceCutzContract;

/**
 * Created by Pop H2 on 9/6/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class CompanyEntry implements BaseColumns {

    public static final String PATH         = "company";
    public static final String TABLE_NAME   = "company";

    public static final Uri CONTENT_URI = PriceCutzContract.BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();

    public static final String CONTENT_TYPE         = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;
    public static final String CONTENT_ITEM_TYPE    = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;

    public static final String COLUMN_COY_ID        = "company_id";
    public static final String COLUMN_NAME          = "name";
    public static final String COLUMN_INDUSTRY      = "industry";
    public static final String COLUMN_IMAGE_URL     = "image_url";

    public static Uri buildUri(long id) {
        return ContentUris.withAppendedId(CONTENT_URI, id);
    }

}
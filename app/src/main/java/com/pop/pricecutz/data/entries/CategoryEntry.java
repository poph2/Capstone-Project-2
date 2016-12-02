package com.pop.pricecutz.data.entries;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.pop.pricecutz.data.PriceCutzContract;

/**
 * Created by adeniyi.bello on 11/26/2016.
 */

public class CategoryEntry implements BaseColumns {

    public static final String PATH = "category";

    public static final Uri CONTENT_URI = PriceCutzContract.BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();

    public static final String CONTENT_TYPE         = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;
    public static final String CONTENT_ITEM_TYPE    = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + PriceCutzContract.CONTENT_AUTHORITY + "/" + PATH;

    public static final String TABLE_NAME = "category";

    public static final String COLUMN_CAT_ID        = "category_id";
    public static final String COLUMN_NAME          = "name";
    public static final String COLUMN_IMAGE_NAME    = "image_name";

    public static Uri buildCategoryUri(long id) {
        return ContentUris.withAppendedId(CONTENT_URI, id);
    }
}

package com.pop.pricecutz.data;

import android.annotation.TargetApi;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.pop.pricecutz.Company;
import com.pop.pricecutz.data.entries.CategoryEntry;
import com.pop.pricecutz.data.entries.CompanyEntry;
import com.pop.pricecutz.data.entries.DiscountEntry;
import com.pop.pricecutz.data.entries.OutletEntry;

/**
 * Created by adeniyi.bello on 12/1/2016.
 */

public class PriceCutzProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private PriceCutzDBHelper mOpenHelper;

    static final int CATEGORY   = 1000;
    static final int COMPANY    = 1100;
    static final int DISCOUNT   = 1200;
    static final int OUTLET     = 1300;

    @Override
    public boolean onCreate() {
        mOpenHelper = new PriceCutzDBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String tableName = getTableName(uri);

        Cursor retCursor = mOpenHelper.getReadableDatabase().query(
                tableName,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    public String getTableName(Uri uri) {
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case CATEGORY: {
                return CategoryEntry.TABLE_NAME;
            }
            case COMPANY: {
                return CompanyEntry.TABLE_NAME;
            }
            case DISCOUNT: {
                return DiscountEntry.TABLE_NAME;
            }
            case OUTLET: {
                return OutletEntry.TABLE_NAME;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case CATEGORY:
                return CategoryEntry.CONTENT_TYPE;
            case COMPANY:
                return CompanyEntry.CONTENT_TYPE;
            case DISCOUNT:
                return DiscountEntry.CONTENT_TYPE;
            case OUTLET:
                return OutletEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case CATEGORY: {
                returnUri = CategoryEntry.insert(db, values, uri);
                break;
            }
            case COMPANY: {
                returnUri = CompanyEntry.insert(db, values, uri);
                break;
            }
            case DISCOUNT: {
                returnUri = DiscountEntry.insert(db, values, uri);
                break;
            }
            case OUTLET: {
                returnUri = OutletEntry.insert(db, values, uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;

        // this makes delete all rows return the number of rows deleted
        if ( null == selection ) selection = "1";

        rowsDeleted = db.delete(getTableName(uri), selection, selectionArgs);

        // Because a null deletes all rows
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdated;

        rowsUpdated = db.update(getTableName(uri), values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] valuesArr) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int returnCount = 0;
        switch (match) {
            case CATEGORY:
                returnCount = CategoryEntry.bulkInsert(db, valuesArr);
                break;
            case COMPANY:
                returnCount = CompanyEntry.bulkInsert(db, valuesArr);
                break;
            case DISCOUNT:
                returnCount = DiscountEntry.bulkInsert(db, valuesArr);
                break;
            case OUTLET:
                returnCount = OutletEntry.bulkInsert(db, valuesArr);
                break;
            default:
                return super.bulkInsert(uri, valuesArr);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnCount;
    }

    static UriMatcher buildUriMatcher() {

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = PriceCutzContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, CategoryEntry.PATH,   CATEGORY);
        matcher.addURI(authority, CompanyEntry.PATH,    COMPANY);
        matcher.addURI(authority, DiscountEntry.PATH,   DISCOUNT);
        matcher.addURI(authority, OutletEntry.PATH,     OUTLET);

        return matcher;
    }

    @Override
    @TargetApi(11)
    public void shutdown() {
        mOpenHelper.close();
        super.shutdown();
    }
}

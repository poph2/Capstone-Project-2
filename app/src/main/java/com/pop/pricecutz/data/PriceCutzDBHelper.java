package com.pop.pricecutz.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pop.pricecutz.data.entries.CompanyEntry;

/**
 * Created by adeniyi.bello on 12/1/2016.
 */

public class PriceCutzDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "price_cutz.db";

    public PriceCutzDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_COY_TABLE = "CREATE TABLE " + CompanyEntry.TABLE_NAME + " (" +
                CompanyEntry._ID                + " INTEGER PRIMARY KEY," +
                CompanyEntry.COLUMN_COY_ID      + " INTEGER UNIQUE NOT NULL, " +
                CompanyEntry.COLUMN_NAME        + " TEXT NOT NULL, " +
                CompanyEntry.COLUMN_INDUSTRY    + " TEXT NOT NULL, " +
                CompanyEntry.COLUMN_IMAGE_URL   + " TEXT NOT NULL " +
                " );";

        sqLiteDatabase.execSQL(SQL_CREATE_COY_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CompanyEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

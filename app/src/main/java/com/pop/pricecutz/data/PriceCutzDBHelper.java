package com.pop.pricecutz.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pop.pricecutz.data.entries.CategoryEntry;
import com.pop.pricecutz.data.entries.CompanyEntry;
import com.pop.pricecutz.data.entries.DiscountEntry;
import com.pop.pricecutz.data.entries.OutletEntry;

/**
 * Created by Pop H2 on 12/1/2016.
 * Pop Inc
 * Lagos Nigeria
 */

public class PriceCutzDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "price_cutz.db";

    public PriceCutzDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CategoryEntry.createTableSQL());
        sqLiteDatabase.execSQL(CompanyEntry.createTableSQL());
        sqLiteDatabase.execSQL(DiscountEntry.createTableSQL());
        sqLiteDatabase.execSQL(OutletEntry.createTableSQL());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(CategoryEntry.dropTableSQL());
        sqLiteDatabase.execSQL(CompanyEntry.dropTableSQL());
        sqLiteDatabase.execSQL(DiscountEntry.dropTableSQL());
        sqLiteDatabase.execSQL(OutletEntry.dropTableSQL());
        onCreate(sqLiteDatabase);
    }
}

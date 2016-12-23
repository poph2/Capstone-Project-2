package com.pop.pricecutz.utils;

import android.content.ContentValues;

import com.pop.pricecutz.backend.categoryApi.model.Category;
import com.pop.pricecutz.backend.companyApi.model.Company;
import com.pop.pricecutz.backend.discountApi.model.Discount;
import com.pop.pricecutz.backend.outletApi.model.Outlet;
import com.pop.pricecutz.data.entries.CategoryEntry;
import com.pop.pricecutz.data.entries.CompanyEntry;
import com.pop.pricecutz.data.entries.DiscountEntry;
import com.pop.pricecutz.data.entries.OutletEntry;

/**
 * Created by Pop H2 on 12/20/2016.
 * Pop Inc
 * Lagos Nigeria
 */

public class BeanEntryConverter {

    public static ContentValues convertToContentValues(Category o) {
        ContentValues c = new ContentValues();

        c.put(CategoryEntry._ID,                o.getId());
        c.put(CategoryEntry.COLUMN_NAME,        o.getCatName());
        c.put(CategoryEntry.COLUMN_IMAGE_NAME,  o.getCatImageName());
        c.put(CategoryEntry.COLUMN_IMAGE_INDEX, o.getCatImageIndex());

        return c;
    }

    public static ContentValues convertToContentValues(Company o) {
        ContentValues c = new ContentValues();

        c.put(CompanyEntry._ID,                o.getId());
        c.put(CompanyEntry.COLUMN_NAME,        o.getCoyName());
        c.put(CompanyEntry.COLUMN_INDUSTRY,    o.getCoyIndustry());
        c.put(CompanyEntry.COLUMN_IMAGE_URL,   o.getCoyImageUrl());

        return c;
    }

    public static ContentValues convertToContentValues(Discount o) {
        ContentValues c = new ContentValues();

        c.put(DiscountEntry._ID,                o.getId());
        c.put(DiscountEntry.COLUMN_CODE,        o.getDiscCode());
        c.put(DiscountEntry.COLUMN_TITLE,       o.getDiscTitle());
        c.put(DiscountEntry.COLUMN_DESCRIPTION, o.getDiscDescription());
        c.put(DiscountEntry.COLUMN_COY_ID,      o.getCoyId());
        c.put(DiscountEntry.COLUMN_TYPE,        o.getDiscType());
        c.put(DiscountEntry.COLUMN_IMAGE_INDEX, o.getDiscImageIndex());
//        c.put(.COLUMN_, o.get());

        return c;
    }

    public static ContentValues convertToContentValues(Outlet o) {
        ContentValues c = new ContentValues();

        c.put(OutletEntry._ID,                o.getId());
        c.put(OutletEntry.COLUMN_COY_ID,        o.getCoyId());
        c.put(OutletEntry.COLUMN_NAME,       o.getOutletName());
        c.put(OutletEntry.COLUMN_LATITUDE, o.getOutletLatitude());
        c.put(OutletEntry.COLUMN_LONGITUDE,      o.getOutletLongitude());
//        c.put(.COLUMN_, o.get());

        return c;
    }

}

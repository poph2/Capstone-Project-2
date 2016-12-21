package com.pop.pricecutz.utils;

import android.content.ContentValues;

import com.pop.pricecutz.Category;
import com.pop.pricecutz.backend.categoryBeanApi.model.CategoryBean;
import com.pop.pricecutz.backend.companyBeanApi.model.CompanyBean;
import com.pop.pricecutz.backend.discountBeanApi.model.DiscountBean;
import com.pop.pricecutz.data.entries.CategoryEntry;
import com.pop.pricecutz.data.entries.CompanyEntry;
import com.pop.pricecutz.data.entries.Data1;
import com.pop.pricecutz.data.entries.DiscountEntry;

/**
 * Created by Pop H2 on 12/20/2016.
 * Pop Inc
 * Lagos Nigeria
 */

public class BeanEntryConverter {

    public static ContentValues convertToContentValues(CategoryBean o) {
        ContentValues c = new ContentValues();

        c.put(CategoryEntry._ID,                o.getId());
        c.put(CategoryEntry.COLUMN_NAME,        o.getName());
        c.put(CategoryEntry.COLUMN_IMAGE_NAME,  o.getImageName());

        return c;
    }

    public static ContentValues convertToContentValues(CompanyBean o) {
        ContentValues c = new ContentValues();

        c.put(CompanyEntry._ID,                o.getId());
        c.put(CompanyEntry.COLUMN_NAME,        o.getName());
        c.put(CompanyEntry.COLUMN_INDUSTRY,     o.getIndustry());
        c.put(CompanyEntry.COLUMN_IMAGE_URL,    o.getImageURL());

        return c;
    }

    public static ContentValues convertToContentValues(DiscountBean o) {
        ContentValues c = new ContentValues();

        c.put(DiscountEntry._ID,                o.getId());
        c.put(DiscountEntry.COLUMN_CODE,        o.getCode());
        c.put(DiscountEntry.COLUMN_TITLE,       o.getTitle());
        c.put(DiscountEntry.COLUMN_DESCRIPTION, o.getDescription());
        c.put(DiscountEntry.COLUMN_COY_ID,      o.getCompanyId());
        c.put(DiscountEntry.COLUMN_TYPE,        o.getType());
        c.put(DiscountEntry.COLUMN_IMAGE_INDEX, o.getImageIndex());
//        c.put(.COLUMN_, o.get());

        return c;
    }

}

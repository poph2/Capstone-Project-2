package com.pop.pricecutz.utils;

import com.pop.pricecutz.Category;
import com.pop.pricecutz.Company;
import com.pop.pricecutz.Discount;
import com.pop.pricecutz.Outlet;
import com.pop.pricecutz.backend.categoryBeanApi.model.CategoryBean;
import com.pop.pricecutz.backend.companyBeanApi.model.CompanyBean;
import com.pop.pricecutz.backend.discountBeanApi.model.DiscountBean;
import com.pop.pricecutz.backend.outletBeanApi.model.OutletBean;

/**
 * Created by Pop H2 on 12/18/2016.
 * Pop Inc
 * Lagos Nigeria
 */

public class BeanEntityConverter {

    public static CategoryBean convertToBean(Category o) {
        CategoryBean bean = new CategoryBean();

        bean.setId(o.getId());
        bean.setName(o.getName());
        bean.setImageName(o.getImageName());
        bean.setImageIndex(o.getImageIndex());

        return bean;
    }

    public static CompanyBean convertToBean(Company o) {
        CompanyBean bean = new CompanyBean();

        bean.setId(o.getId());
        bean.setName(o.getName());
        bean.setIndustry(o.getIndustry());
        bean.setImageURL(o.getImageURL());

        return bean;
    }

    public static DiscountBean convertToBean(Discount o) {

        DiscountBean bean = new DiscountBean();

        bean.setId(o.getId());
        bean.setCode(o.getCode());
        bean.setTitle(o.getTitle());
        bean.setDescription(o.getDescription());
        bean.setCompanyId(o.getCompany_id());
        bean.setType(o.getType());
        bean.setImageIndex(o.getImageIndex());

        return bean;
    }

    public static OutletBean convertToBean(Outlet o) {
        OutletBean bean = new OutletBean();

        bean.setId(o.getId());
        bean.setCompanyId(o.getCompany_id());
        bean.setName(o.getName());
        bean.setLatitude(o.getLatitude());
        bean.setLongitude(o.getLongitude());
//        bean.set(o.get());

        return bean;
    }
}

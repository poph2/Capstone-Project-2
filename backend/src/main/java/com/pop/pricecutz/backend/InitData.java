package com.pop.pricecutz.backend;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Pop H2 on 12/23/2016.
 * Pop Inc
 * Lagos Nigeria
 */

public class InitData {

    static ArrayList<Category> categoryArrayList;
    static ArrayList<Company> companyArraylist;
    static ArrayList<Discount> discountArrayList;
    static ArrayList<Outlet> outletArrayList;


    public static void initData() {
        initCategories();
        initCompanies();
        initDiscounts();
        initOutlets();
    }

    private static void initCategories() {

        categoryArrayList = new ArrayList<>();

        CategoryEndpoint categoryEndpoint = new CategoryEndpoint();

        Random r = new Random();

        for(int i = 0; i < Data.categories.length; i++) {

            long timeInMillis = System.currentTimeMillis();

            Category category = new Category();

            category.setCat_name(Data.categories[i]);
            category.setCat_image_index(r.nextInt(99));
            category.setCat_created_time(timeInMillis);
            category.setCat_updated_time(timeInMillis);

            categoryArrayList.add(categoryEndpoint.insert(category));
        }
    }

    private static void initCompanies() {

        companyArraylist = new ArrayList<>();

        CompanyEndpoint companyEndpoint = new CompanyEndpoint();
        Random r = new Random();

        for(int i = 0; i < Data.name.length; i++) {

            long timeInMillis = System.currentTimeMillis();

            Company company = new Company();

            company.setCoy_name(Data.name[i]);
            company.setCoy_industry(Data.industry[i]);
            company.setCoy_image_url(Data.image_url[i]);
            company.setCoy_created_time(timeInMillis);
            company.setCoy_updated_time(timeInMillis);

            companyArraylist.add(companyEndpoint.insert(company));
        }
    }

    private static void initDiscounts() {

        discountArrayList = new ArrayList<>();

        DiscountEndpoint discountEndpoint = new DiscountEndpoint();
        Random r = new Random();

        for(int i = 0; i < 200; i++) {

            long timeInMillis = System.currentTimeMillis();

            Discount discount = new Discount();

            Company company = companyArraylist.get(r.nextInt(companyArraylist.size() - 1));

            discount.setCoy_id(company.getId());
            discount.setDisc_code("discount_" + i);

            String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
            discount.setDisc_description(description);
            discount.setDisc_image_index(r.nextInt(99));
            discount.setDisc_title("Discount " + i);
            discount.setDisc_type(1);
            discount.setDisc_created_time(timeInMillis);
            discount.setDisc_updated_time(timeInMillis);

            discountEndpoint.insert(discount);
        }
    }

    private static void initOutlets() {

        outletArrayList = new ArrayList<>();

        OutletEndpoint outletEndpoint = new OutletEndpoint();
        Random r = new Random();

        for(int i = 0; i < 200; i++) {

            long timeInMillis = System.currentTimeMillis();

            Outlet outlet = new Outlet();

            Company company = companyArraylist.get(r.nextInt(companyArraylist.size() - 1));

            outlet.setCoy_id(company.getId());
            outlet.setOutlet_name("Outlet " + i);
            outlet.setOutlet_latitude(0.0d);
            outlet.setOutlet_longitude(0.0d);
            outlet.setOutlet_created_time(timeInMillis);
            outlet.setOutlet_updated_time(timeInMillis);

            outletEndpoint.insert(outlet);
        }
    }

}

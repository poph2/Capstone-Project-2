package com.pop.pricecutz.backend;

import com.googlecode.objectify.annotation.Entity;
import com.pop.pricecutz.backend.utils.Data;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Pop H2 on 12/23/2016.
 * Pop Inc
 * Lagos Nigeria
 */

@Entity
public class Init {

    public Init() {}

    ArrayList<Category> categoryArrayList;
    ArrayList<Company> companyArraylist;
    ArrayList<Discount> discountArrayList;
    ArrayList<Outlet> outletArrayList;


    public void initData() {
        initCategories();
        initCompanies();
        initDiscounts();
        initOutlets();
    }

    private void initCategories() {

        categoryArrayList = new ArrayList<>();

        CategoryEndpoint categoryEndpoint = new CategoryEndpoint();

        Random r = new Random();

        for(int i = 0; i < Data.categories.length; i++) {

            Category category = new Category();

            category.setCat_name(Data.categories[i]);
            category.setCat_image_index(r.nextInt(99));

            categoryArrayList.add(categoryEndpoint.insert(category));
        }
    }

    private void initCompanies() {

        companyArraylist = new ArrayList<>();

        CompanyEndpoint companyEndpoint = new CompanyEndpoint();
        Random r = new Random();

        for(int i = 0; i < Data.name.length; i++) {

            Company company = new Company();

            Category category = searchCategory(Data.industry[i]);
            long cat_id = 0;
            if(category != null) {
                cat_id = category.getId();
            }

            company.setCoy_name(Data.name[i]);
            company.setCoy_industry(Data.industry[i]);
            company.setCategory_id(cat_id);
            company.setCoy_image_url(Data.image_url[i]);

            companyArraylist.add(companyEndpoint.insert(company));
        }
    }

    private void initDiscounts() {

        discountArrayList = new ArrayList<>();

        DiscountEndpoint discountEndpoint = new DiscountEndpoint();
        Random r = new Random();

        for(int i = 0; i < 200; i++) {

            Discount discount = new Discount();

            Company company = companyArraylist.get(r.nextInt(companyArraylist.size() - 1));

            discount.setCoy_id(company.getId());
            discount.setDisc_code("discount_" + i);

            String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
            discount.setDisc_description(description);
            discount.setDisc_image_index(r.nextInt(99));
            discount.setDisc_title("Discount " + i);
            discount.setDisc_type(1);

            discountEndpoint.insert(discount);
        }
    }

    private void initOutlets() {

        outletArrayList = new ArrayList<>();

        OutletEndpoint outletEndpoint = new OutletEndpoint();
        Random r = new Random();

        for(int i = 0; i < 200; i++) {

            Outlet outlet = new Outlet();

            Company company = companyArraylist.get(r.nextInt(companyArraylist.size() - 1));

            outlet.setCoy_id(company.getId());
            outlet.setOutlet_name("Outlet " + i);

            double randLat = r.nextDouble();
            double latitude = randLat < 0.5 ? -(randLat*0.2): (randLat*0.2);
            outlet.setOutlet_latitude(latitude);    //0.2deg == 22.2km

            double randLong = r.nextDouble();
            double longitude = randLong < 0.5 ? -(randLong*0.2): (randLong*0.2);
            outlet.setOutlet_longitude(longitude);   //0.2deg == 22.2km

            outletEndpoint.insert(outlet);
        }
    }

    private Category searchCategory(String name) {
        for(Category category: categoryArrayList) {
            if(category.getCat_name().equalsIgnoreCase(name)) {
                return category;
            }
        }
        return null;
    }

}

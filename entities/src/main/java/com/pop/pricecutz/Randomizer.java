package com.pop.pricecutz;

import com.pop.pricecutz.Company;
import com.pop.pricecutz.map.GPSPoint;

import java.util.ArrayList;
import java.util.Random;

public class Randomizer {


    public static ArrayList<Company> getCompanies(int population) {

        ArrayList<Company> companyArrayList = new ArrayList<>();

        Random random = new Random();

        while(companyArrayList.size() < population) {
            Company company = new Company(random.nextInt(Data.name.length));
            companyArrayList.add(company);
        }


        return companyArrayList;

    }

    public static ArrayList<Discount> getDiscounts(int population) {

        ArrayList<Discount> discountArrayList = new ArrayList<>();

        Random random = new Random();

        int i = 0;

        while(discountArrayList.size() < population) {
            int company_id = random.nextInt(Data.name.length);

            Discount discount = getDiscount(company_id);
            discountArrayList.add(discount);

        }


        return discountArrayList;

    }

    public static ArrayList<Outlet> getOutlets(int population, GPSPoint centralGPSPoint, double latRange, double longRange) {

        ArrayList<Outlet> outletArrayList = new ArrayList<>();

        while(outletArrayList.size() < population) {
            Outlet outlet = getOutlet(centralGPSPoint, latRange, longRange);

            outletArrayList.add(outlet);
        }

        return outletArrayList;

    }

    public static GPSPoint getGPSPoint(GPSPoint centralGPSPoint, double latRange, double longRange) {
        Random random = new Random();

        double randLat = (random.nextDouble() * 2.0) - 1.0;
        double randLong = (random.nextDouble() * 2.0) - 1.0;

        double latitude = (randLat * latRange) + centralGPSPoint.getLatitude();
        double longitude = (randLong * longRange) + centralGPSPoint.getLongitude();

        GPSPoint gpsPoint = new GPSPoint(latitude, longitude);

        return gpsPoint;
    }

    public static Discount getDiscount(int company_id) {

        Random random = new Random();

        int id = random.nextInt(Integer.MAX_VALUE);
        String code = "discount_" + id;
        String title = "Discount " + id;
//        String description = "This is a demo description for Discount " + id + ".";

        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        int type = 1 + random.nextInt(1);

        Discount discount = new Discount(id, code, title, description, company_id, type);

        return discount;
    }

    public static Outlet getOutlet(GPSPoint centralGPSPoint, double latRange, double longRange) {

        Random random = new Random();

        int company_id = random.nextInt(Data.name.length);
        GPSPoint gpsPoint = getGPSPoint(centralGPSPoint, latRange, longRange);
        Discount discount = getDiscount(company_id);

        Outlet outlet = new Outlet(random.nextInt(), company_id, "Outlet", gpsPoint.getLatitude(), gpsPoint.getLongitude());

        return outlet;
    }

}

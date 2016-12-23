package com.pop.pricecutz.utils;

/**
 * Created by Pop H2 on 10/11/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class MapUtils {

//    static double R = 6.378137e6;
//
//    public static void main(String args[]) {
//
////        GPSPoint p1 = new GPSPoint(6.436062, 3.465925);
////        GPSPoint p2 = new GPSPoint(6.446308, 3.574080);
////        12.04km
//
//        GPSPoint p1 = new GPSPoint(50.06638888888889, 5.714722222222222);
//        GPSPoint p2 = new GPSPoint(58.64388888888889, 3.0700000000000003);
//
//        GPSPoint p3 = calculateMidPoint(p1, p2);
//
//
//        System.out.println("Distance - " + calculateDistance(p1, p2));
//        System.out.println("Bearing - " + calculateBearing(p1, p2));
//        System.out.println("MidPoint - " + p3.getLatitude() + ", " + p3.getLongitude());
//
//    }
//
//    public static double calculateDistance(GPSPoint p1, GPSPoint p2) {
//        double lat1 = Math.toRadians(p1.getLatitude());
//        double lat2 = Math.toRadians(p2.getLatitude());
//        double long1 = Math.toRadians(p1.getLongitude());
//        double long2 = Math.toRadians(p2.getLongitude());
//
//        double deltaLong = long2 - long1;
//        double deltaLat = lat2 - lat1;
//
//        double a = Math.pow(Math.sin(deltaLat/2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(deltaLong/2),2);
//        double c = 2 * Math.atan2( Math.sqrt(a), Math.sqrt(1-a) );
//        double d = R * c;
//
//
////        double y = Math.sin(deltaLong) * Math.cos(lat2);
////        double x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1)  * Math.cos(lat2) * Math.cos(deltaLong);
////        double bearing = Math.atan2(y, x);
////
////        return (Math.toDegrees(bearing) + 360) % 360;
//
//        return d;
//    }
//
//    public static double calculateBearing(GPSPoint p1, GPSPoint p2) {
//        double lat1 = Math.toRadians(p1.getLatitude());
//        double lat2 = Math.toRadians(p2.getLatitude());
//        double long1 = Math.toRadians(p1.getLongitude());
//        double long2 = Math.toRadians(p2.getLongitude());
//
//
//        double y = Math.sin(long2 - long1) * Math.cos(lat2);
//        double x = Math.cos(lat1) * Math.sin(lat2) -
//                Math.sin(lat1) * Math.cos(lat2) * Math.cos(long2-long1);
//        double brng = Math.toDegrees(Math.atan2(y, x));
//
//        return brng;
//    }
//
//    public static GPSPoint calculateMidPoint(GPSPoint p1, GPSPoint p2) {
//        double lat1 = Math.toRadians(p1.getLatitude());
//        double lat2 = Math.toRadians(p2.getLatitude());
//        double long1 = Math.toRadians(p1.getLongitude());
//        double long2 = Math.toRadians(p2.getLongitude());
//
//        double Bx = Math.cos(lat2) * Math.cos(long2-long1);
//        double By = Math.cos(lat2) * Math.sin(long2-long1);
//        double lat3 = Math.atan2(Math.sin(lat1) + Math.sin(lat2),
//                Math.sqrt( (Math.cos(lat1)+Bx)*(Math.cos(lat1)+Bx) + By*By ) );
//        double long3 = long1 + Math.atan2(By, Math.cos(lat1) + Bx);
//
//        GPSPoint p3 = new GPSPoint(lat3, long3);
//        return p3;
//    }

}

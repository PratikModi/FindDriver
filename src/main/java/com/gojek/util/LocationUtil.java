package com.gojeck.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Pratik on 21-01-2017.
 */
public class LocationUtil {
    private static LocationUtil ourInstance = new LocationUtil();

    public static LocationUtil getInstance() {
        return ourInstance;
    }

    protected static final String LATITUDE_PATTERN="^(\\+|-)?(?:90(?:(?:\\.0{1,7})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,7})?))$";
    protected static final String LONGITUDE_PATTERN="^(\\+|-)?(?:180(?:(?:\\.0{1,7})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,7})?))$";

    private LocationUtil() {
    }

    public String findArea(long latitude, long longitude){
        /** here Google's Geo Code service can be used to find the locality
         * using latitude and longitude to store the driver information per
         * location. This is to narrow down the search of where when customer will
         * search for driver. Same way we can find locality of customer and search the
         * drivers accordingly
         *
         * for now returning static value as "Bangalore".
         */
        return "Bangalore";
    }

    public double findDistance(long custLatitude,long custLongitude, long driverLatitude, long driverLongitude){

        double theta = custLongitude - driverLongitude;
        double dist = Math.sin(deg2rad(custLatitude)) * Math.sin(deg2rad(driverLatitude)) + Math.cos(deg2rad(custLatitude)) * Math.cos(deg2rad(driverLatitude)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344*1000;
        return dist;
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts decimal degrees to radians						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts radians to decimal degrees						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }


    public boolean isValidLatitude(Long latitude) {
        Pattern latitudePattern = Pattern.compile(LATITUDE_PATTERN);
        Matcher latitudeMatcher = latitudePattern.matcher(latitude.toString());
        return latitudeMatcher.find();
    }


    public boolean isValidLongitude(Long longitude) {
        Pattern latitudePattern = Pattern.compile(LONGITUDE_PATTERN);
        Matcher latitudeMatcher = latitudePattern.matcher(longitude.toString());
        return latitudeMatcher.find();
    }

}

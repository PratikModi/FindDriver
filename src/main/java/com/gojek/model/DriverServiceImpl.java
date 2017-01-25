package com.gojek.model;

import com.gojek.api.Location;
import com.gojek.api.Driver;
import com.gojek.service.DriverService;
import com.gojek.util.LocationUtil;
import com.google.inject.Inject;

import java.util.*;

/**
 * Created by Pratik on 21-01-2017.
 */
public class DriverServiceImpl implements DriverService {

    /**
     * Comment: -
     * Below Map is to hold current driver locations.
     * Ideally this should be inserted into some NO SQL DB Like MongoDB, it can
     * also be inserted into "Elastic Search" to make in scalable and distributed.     *
     * <p>
     * Just for the ease I am storing driver locations in memory which is not
     * preferable and scalable as well.
     */

    private final Map<String, NavigableSet<Driver>> driverLocations;

    @Inject
    public DriverServiceImpl(Map<String, NavigableSet<Driver>> driverLocations) {
        this.driverLocations = driverLocations;

    }

    @Override
    public NavigableSet<Driver> getDriversLocation(Location location) {
        final NavigableSet<Driver> driverList;
        String key = String.valueOf(location.getLatitude()) + "-" + String.valueOf(location.getLongitude());
        driverList = driverLocations.get(key);
        if(driverList!=null && driverList.size()>0) {
            Iterator<Driver> iterator = driverList.iterator();
            while(iterator.hasNext()){
                Driver driver = iterator.next();
                final double distance = LocationUtil.getInstance().findDistance(location.getLatitude(),location.getLongitude(),driver.getLatitude(),driver.getLongitude());
                if(distance>location.getRadius()){
                    iterator.remove();
                }else {
                    driver.setDistance(distance);
                }
            }
            if(driverList.size()>location.getLimit()){
                return driverList.subSet(driverList.first(),true,driverList.toArray(new Driver[driverList.size()])[location.getLimit()-1],true);
            }
        }
        return driverList;
    }

    @Override
    public void saveDriversLocation(Driver driver) {
        NavigableSet<Driver> driverList;
        String key = String.valueOf(driver.getLatitude()) + "-" + String.valueOf(driver.getLongitude());
        if(driverLocations.containsKey(key)){
            driverList = driverLocations.get(key);
            driverList.add(driver);
        }else{
            driverList = new TreeSet<Driver>();
            driverList.add(driver);
        }

        driverLocations.put(String.valueOf(driver.getLatitude()) + "-" + String.valueOf(driver.getLongitude()),driverList);
    }
}

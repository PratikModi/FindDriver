package com.gojeck.service;

import com.gojeck.api.Driver;
import com.gojeck.api.Location;

import java.util.NavigableSet;

/**
 * Created by Pratik on 21-01-2017.
 */
public interface DriverService {

    NavigableSet<Driver> getDriversLocation(Location location);

    void saveDriverLocation(Driver driver);

}

package com.gojek.service;

import com.gojek.api.Driver;
import com.gojek.api.Location;

import java.util.NavigableSet;

/**
 * Created by Pratik on 21-01-2017.
 */
public interface DriverService {

    NavigableSet<Driver> getDriversLocation(Location location);

    void saveDriversLocation(Driver driver);

}

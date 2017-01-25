package com.gojek.model;

import com.gojek.api.Driver;
import com.gojek.api.Location;
import com.gojek.service.DriverService;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;

/**
 * Created by Pratik on 25-01-2017.
 */
public class DriverServiceImplTest {

    private final Map<String, NavigableSet<Driver>> driverLocations = new HashMap<>();

    private final DriverService service = new DriverServiceImpl(driverLocations);

    @Test
    public void saveDriversLocationTest(){

        Driver d1 = new Driver(1,10l,20l);
        Driver d2 = new Driver(2,10l,20l);
        Driver d3 = new Driver(3,20l,30l);
        Driver d4 = new Driver(4,20l,30l);
        Driver d5 = new Driver(5,30l,40l);
        Driver d6 = new Driver(6,30l,40l);
        Driver d7 = new Driver(7,40l,50l);
        Driver d8 = new Driver(8,40l,50l);
        Driver d9 = new Driver(9,50l,60l);
        Driver d10 = new Driver(10,50l,60l);

        service.saveDriversLocation(d1);
        Assert.assertEquals(1,driverLocations.size());
        service.saveDriversLocation(d2);
        Assert.assertEquals(1,driverLocations.size());
        service.saveDriversLocation(d3);
        Assert.assertEquals(2,driverLocations.size());
        service.saveDriversLocation(d4);
        Assert.assertEquals(2,driverLocations.size());
        service.saveDriversLocation(d5);
        Assert.assertEquals(3,driverLocations.size());
        service.saveDriversLocation(d6);
        Assert.assertEquals(3,driverLocations.size());
        service.saveDriversLocation(d7);
        Assert.assertEquals(4,driverLocations.size());
        service.saveDriversLocation(d8);
        Assert.assertEquals(4,driverLocations.size());
        service.saveDriversLocation(d9);
        Assert.assertEquals(5,driverLocations.size());
        service.saveDriversLocation(d10);
        Assert.assertEquals(5,driverLocations.size());

    }

    @Test
    public void getDriversLocationTest(){

        this.saveDriversLocationTest();

        Location location = new Location(10l,20l,1,10);
        Set<Driver> driverList = service.getDriversLocation(location);
        Assert.assertEquals(2,driverList.size());

        location = new Location(10l,20l,1,1);
        driverList = service.getDriversLocation(location);
        Assert.assertEquals(1,driverList.size());

        location = new Location(10l,20l,0,1);
        driverList = service.getDriversLocation(location);
        Assert.assertEquals(0,driverList.size());

    }

}

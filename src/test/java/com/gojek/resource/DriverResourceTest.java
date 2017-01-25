package com.gojek.resource;

import com.gojek.api.Driver;
import com.gojek.api.Location;
import com.gojek.model.DriverServiceImpl;
import com.gojek.service.DriverService;
import com.gojek.validation.DriverValidator;
import com.gojek.validation.Validator;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;

/**
 * Created by Pratik on 25-01-2017.
 */
public class DriverResourceTest {


    private final Map<String, NavigableSet<Driver>> driverLocations = new HashMap<>();

    private final DriverService service = new DriverServiceImpl(driverLocations);

    private final Validator<Driver> validator = new DriverValidator();

    private final DriverResource resource = new DriverResource(service,validator);

    @Test
    public void saveDriversLocationTest(){

        long latitude = 20l;
        long longitude = 10l;
        int radius = 0;
        int limit = 0;

        Location location = new Location(latitude,longitude, radius,limit);
        Response response = resource.saveDriver(1,location);
        Assert.assertEquals(response.getStatus(),200);
        Assert.assertEquals(1,driverLocations.size());
    }

    @Test(expected = RuntimeException.class)
    public void saveDriversLocationInvalidLatitudeTest(){
        long latitude = 91l;
        long longitude = 10l;
        int radius = 0;
        int limit = 0;

        Location location = new Location(latitude,longitude, radius,limit);
        Response response = resource.saveDriver(2,location);
        Assert.assertEquals(response.getStatus(),400);
        Assert.assertEquals(1,driverLocations.size());
    }

    @Test(expected = RuntimeException.class)
    public void saveDriversLocationInvalidLongitudeTest(){
        long latitude = 90l;
        long longitude = 190l;
        int radius = 0;
        int limit = 0;

        Location location = new Location(latitude,longitude, radius,limit);
        Response response = resource.saveDriver(3,location);
        Assert.assertEquals(response.getStatus(),400);
        Assert.assertEquals(1,driverLocations.size());
    }

    @Test
    public void saveDriversLocationInvalidIdTest(){

        long latitude = 20l;
        long longitude = 10l;
        int radius = 0;
        int limit = 0;

        Location location = new Location(latitude,longitude, radius,limit);
        Response response = resource.saveDriver(0,location);
        Assert.assertEquals(response.getStatus(),404);
    }

    @Test
    public void getDriversLocationTest(){
        long latitude = 20l;
        long longitude = 10l;
        int radius = 0;
        int limit = 0;
        this.saveDriversLocationTest();
        Response response = resource.findDriver(latitude,longitude,radius,limit);
        Assert.assertEquals(response.getStatus(),200);
        Assert.assertEquals(1,((Set<Driver>)response.getEntity()).size());
    }

    @Test
    public void getDriversLocationNoDriverFoundTest(){
        long latitude = 20l;
        long longitude = 11l;
        int radius = 0;
        int limit = 0;
        this.saveDriversLocationTest();
        Response response = resource.findDriver(latitude,longitude,radius,limit);
        Assert.assertEquals(response.getStatus(),404);
    }

    @Test(expected = RuntimeException.class)
    public void getDriversLocationInvalidLatitudeTest(){
        long latitude = 91l;
        long longitude = 11l;
        int radius = 0;
        int limit = 0;
        Response response = resource.findDriver(latitude,longitude,radius,limit);
        Assert.assertEquals(response.getStatus(),400);
    }

    @Test(expected = RuntimeException.class)
    public void getDriversLocationInvalidLongitudeTest(){
        long latitude = 90l;
        long longitude = 181l;
        int radius = 0;
        int limit = 0;
        Response response = resource.findDriver(latitude,longitude,radius,limit);
        Assert.assertEquals(response.getStatus(),400);
    }


}

package com.gojeck;

import com.gojeck.api.Driver;
import com.gojeck.model.DriverServiceImpl;
import com.gojeck.resource.DriverResource;
import com.gojeck.service.DriverService;
import com.gojeck.validation.DriverValidator;
import com.gojeck.validation.Validator;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.dropwizard.setup.Environment;

import java.util.Map;
import java.util.NavigableSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Pratik on 21-01-2017.
 */
public class FindDriveModule extends ConfigurationServiceModule {

    final Environment environment;

    /**
     * Comment: -
     *          Below Map is to hold current driver locations.
     *          Ideally this should be inserted into some NO SQL DB Like MongoDB, it can
     *          also be inserted into "Elastic Search" to make in scalable and distributed.     *
     *
     *          Just for the ease I am storing driver locations in memory which is not
     *          preferable and scalable as well.
     */

    private static Map<String,NavigableSet<Driver>> driverLocations = new ConcurrentHashMap<>();

    public FindDriveModule(final FindDriverConfiguration configuration,final Environment environment){
        setFindDriverConfiguration(configuration);
        this.environment = environment;
    }

    @Override
    protected void configure() {
        bind(DriverResource.class);
        bind(DriverServiceImpl.class);
        //bind(DriverService.class);
        //bind(Validator.class);
        expose(DriverResource.class);
    }

    @Provides
    public Map<String,NavigableSet<Driver>> getDriverLocations(){
        return driverLocations;
    }

    @Provides
    @Singleton
    public Validator<Driver> getValidator(){
        return new DriverValidator();
    }

    @Provides
    public DriverService getDriverService(){
        return new DriverServiceImpl(driverLocations);
    }
}

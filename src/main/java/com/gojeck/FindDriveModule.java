package com.gojeck;

import com.gojeck.api.Driver;
import com.gojeck.resource.DriverResource;
import com.google.inject.Provides;
import io.dropwizard.setup.Environment;

import java.util.HashMap;
import java.util.Map;

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
     */

    private static Map<String,Map<Integer,Driver>> driverLocations = new HashMap<>();

    public FindDriveModule(final FindDriverConfiguration configuration,final Environment environment){
        setFindDriverConfiguration(configuration);
        this.environment = environment;
    }

    @Override
    protected void configure() {
        expose(DriverResource.class);
    }

    @Provides
    public Map<String,Map<Integer,Driver>> getDriverLocations(){
        return driverLocations;
    }
}

package com.gojeck.resource;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import com.gojeck.api.Driver;
import com.google.inject.Inject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Pratik on 21-01-2017.
 */

@Path("/drivers")
@Api(value = "Where Is My Driver?")
@Produces(MediaType.APPLICATION_JSON)
public class DriverResource {

    private static final Logger log = LoggerFactory.getLogger(DriverResource.class);

    private final Map<String, Map<Integer, Driver>> driverLocations;
    @Inject
    public DriverResource(final Map<String, Map<Integer, Driver>> driverLocations) {
        this.driverLocations = driverLocations;
    }

    @GET
    @Timed
    @Metered
    @ExceptionMetered
    @ApiOperation(tags = {"customer"}, value = "Finds all the drivers near by")
    public List<Driver> findDriver(
            @ApiParam(required = true, value = "latitude")
            @NotEmpty
                    long latitude,
            @ApiParam(required = true, value = "latitude")
            @NotEmpty
                    long longitude,
            @ApiParam(value = "radius")
                    long radius,
            @ApiParam(value = "limit")
                    long limit
    ) {
        log.info("finding driver for latitude:"+latitude+"&& longitude:"+longitude);
        List<Driver> diversNearBy = new ArrayList<>();
        return diversNearBy;
    }


}

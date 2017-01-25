package com.gojek.resource;

import com.codahale.metrics.annotation.Timed;
import com.gojek.api.Driver;
import com.gojek.api.Location;
import com.gojek.response.ApiResponse;
import com.gojek.service.DriverService;
import com.gojek.validation.Validator;
import com.google.inject.Inject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

/**
 * Created by Pratik on 21-01-2017.
 */
@Path("/drivers")
@Api(value = "Where Is My Driver?")
@Produces(MediaType.APPLICATION_JSON)
public class DriverResource {

    private static final Logger log = LoggerFactory.getLogger(DriverResource.class);
    public static final Integer DEFAULT_RADIUS = 500;
    public static final Integer DEFAULT_LIMIT = 10;

    private final DriverService driverService;
    private final Validator<Driver> validator;
    @Inject
    public DriverResource(final DriverService driverService,final Validator<Driver> validator) {
        this.driverService = driverService;
        this.validator = validator;
    }

    @GET
    @Timed
    @ApiOperation(tags = {"customer"}, value = "Finds all the drivers near by")
    public Response findDriver(
           @ApiParam(required = true, value = "latitude")
           @QueryParam("latitude") final long latitude,
           @ApiParam(required = true, value = "longitude")
           @QueryParam("longitude") final long longitude,
           @ApiParam(value = "radius")
           @QueryParam("radius") int radius,
           @ApiParam(value = "limit")
           @QueryParam("limit") int limit

    ) {
        log.info("finding driver for latitude:"+latitude+"&& longitude:"+longitude);
        radius = radius==0?DEFAULT_RADIUS:radius;
        limit = limit==0?DEFAULT_LIMIT:limit;
        if(!validator.isValidLatitude(latitude) || !validator.isValidLongitude(longitude)){
            throw new RuntimeException(validator.getValidationMessage());
        }
        Set<Driver> driversNearBy=driverService.getDriversLocation(new Location(latitude,longitude,radius,limit));
        if(driversNearBy==null || driversNearBy.size()==0){
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).entity(new ApiResponse(Response.Status.NOT_FOUND.getStatusCode(),"Driver Not Found")).type(MediaType.APPLICATION_JSON_TYPE).build();
        }

        return Response.ok().entity(driversNearBy).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    @Path("/{id}/location")
    @PUT
    @Timed
    @ApiOperation(tags = {"driver"}, value = "Save the driver location")
    public Response saveDriver(
            @PathParam("id")
            Integer id,
            @ApiParam(required = true, value = "Location Details")
            Location location
    ) {
        log.info("saving driver location with latitude:"+location.getLatitude()+"&& longitude:"+location.getLongitude());
        if(id<1 || id>50000){
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).entity(new ApiResponse(Response.Status.NOT_FOUND.getStatusCode(),"Driver Not Found.")).type(MediaType.APPLICATION_JSON_TYPE).build();
        }
        Driver driver = new Driver(id,location.getLatitude(),location.getLongitude());
        if(!validator.validate(driver)){
            log.info("Validation Message:-"+validator.getValidationMessage());
            throw new RuntimeException(validator.getValidationMessage());
        }
        driverService.saveDriversLocation(driver);

        return Response.status(Response.Status.OK.getStatusCode()).entity(new ApiResponse(Response.Status.OK.getStatusCode(),"Driver Data Saved Successfully.")).type(MediaType.APPLICATION_JSON_TYPE).build();
    }


}

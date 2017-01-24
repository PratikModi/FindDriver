package com.gojeck.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Pratik on 23-01-2017.
 */
public class Location {

    @JsonProperty
    //@ApiParam(required = true, value = "latitude")
    private Long latitude;
    @JsonProperty
    //@ApiParam(required = true, value = "longitude")
    private Long longitude;
    @JsonProperty
    //@ApiParam(value = "radius")
    private Integer radius;
    @JsonProperty
    //@ApiParam(value = "limit")
    private Integer limit;

    public Location(Long latitude, Long longitude, Integer radius, Integer limit) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.limit = limit;
    }

    public Long getLatitude() {
        return latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public Integer getRadius() {
        return radius;
    }

    public Integer getLimit() {
        return limit;
    }

    @Override
    public String toString() {
        return "Location{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", radius=" + radius +
                ", limit=" + limit +
                '}';
    }
}

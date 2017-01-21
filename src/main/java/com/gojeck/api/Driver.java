package com.gojeck.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Pratik on 21-01-2017.
 */
public class Driver {

    @JsonProperty
    private int id;
    @JsonProperty
    private long latitude;
    @JsonProperty
    private long longitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }
}

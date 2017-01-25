package com.gojek.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Pratik on 21-01-2017.
 */
public class Driver implements Comparable<Driver>{

    @JsonProperty
    private Integer id;
    @JsonProperty
    private Long latitude;
    @JsonProperty
    private Long longitude;
    @JsonProperty
    private Double distance;

    public Driver(){

    }

    public Driver(Integer id, Long latitude, Long longitude) {
        this(id,latitude,longitude,new Double(0));
    }

    public Driver(Integer id, Long latitude, Long longitude, Double distance) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", distance=" + distance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Driver driver = (Driver) o;

        return id.equals(driver.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public int compareTo(Driver driver) {
        int i = this.getDistance().compareTo(driver.getDistance());
        if(i!=0) return i;
        return this.getId().compareTo(driver.getId());
    }
}

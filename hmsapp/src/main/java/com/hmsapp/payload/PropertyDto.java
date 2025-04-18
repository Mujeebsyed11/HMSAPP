package com.hmsapp.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PropertyDto {

    private long id;

    @NotNull(message = "Property name is Mandatory")
    @Size(min=5, max = 100, message = "Property name can be between 5 to 100 characters")
    private String name;

    @NotNull(message = "Please specify number of Bedrooms")
    private int noOfBedrooms;

    @NotNull(message = "Please specify number of Bathrooms")
    private int noOfBathrooms;

    @NotNull(message = "Please specify number of Guests")
    private int noOfGuest;

    @NotNull(message = "Country name is Mandatory")
    private String country;

    @NotNull(message = "City name is Mandatory")
    private String city;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoOfBedrooms() {
        return noOfBedrooms;
    }

    public void setNoOfBedrooms(int noOfBedrooms) {
        this.noOfBedrooms = noOfBedrooms;
    }

    public int getNoOfBathrooms() {
        return noOfBathrooms;
    }

    public void setNoOfBathrooms(int noOfBathrooms) {
        this.noOfBathrooms = noOfBathrooms;
    }

    public int getNoOfGuest() {
        return noOfGuest;
    }

    public void setNoOfGuest(int noOfGuest) {
        this.noOfGuest = noOfGuest;
    }

    public  String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

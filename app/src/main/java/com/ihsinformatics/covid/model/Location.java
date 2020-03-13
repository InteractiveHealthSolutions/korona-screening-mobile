package com.ihsinformatics.covid.model;

public class Location {
    private String name;
    private String contactNo;
    private String address;

    public Location(String name, String contactNo, String address) {
        this.name = name;
        this.contactNo = contactNo;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getAddress() {
        return address;
    }
}

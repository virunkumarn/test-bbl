package com.example.demo.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserModel {
    public int id;

    @NotBlank(message = "Name cannot be blank")
    public String name;
    @NotBlank(message = "Username cannot be blank")
    public String username;
    @NotBlank(message = "Email cannot be blank")
    public String email;
    @NotNull
    public Address address;
    @NotBlank(message = "Phone cannot be blank")
    public String phone;
    @NotBlank(message = "Website cannot be blank")
    public String website;
    @NotNull
    public Company company;
}

@Data
class Address {
    public String street;
    public String suite;
    public String city;
    public String zipcode;
    public Geo geo;
}

@Data
class Company {

    public String name;

    public String catchPhrase;

    public String bs;
}

@Data
class Geo {
    public String lat;
    public String lng;
}


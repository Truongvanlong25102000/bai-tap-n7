package com.example.btn7.model;

import java.io.Serializable;

public class Contact implements Serializable {
    private long id;
    private String name;
    private String phone;
    private String street;
    private String email;
    private String city;

    public Contact(long id, String name, String phone, String street, String email, String city) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.street = street;
        this.email = email;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getStreet() {
        return street;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }
}

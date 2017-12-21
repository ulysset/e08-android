package com.example.ul.libike;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Bike {
    public String description;
    public String id;
    public String imageURL;
    public String name;
    public Long price;
    public Double latitude;
    public Double longitude;

    public Bike() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Bike(String description, String id, String imageURL, String name, Long price, Double latitude, Double longitude) {
        this.description = description;
        this.id = id;
        this.imageURL = imageURL;
        this.name = name;
        this.price = price;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}

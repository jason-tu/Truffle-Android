package com.example.models;

public class Vendor {

    private User user;
    private Item[] items;

    public Vendor(User user, Item[] items) {
        this.user = user;
        this.items = items;

    }

}

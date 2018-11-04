package com.example.models;

import com.sun.tools.javac.jvm.Items;

public class Vendor {

    private User user;
    private Items[] items;

    public Vendor(User user, Items[] items) {
        this.user = user;
        this.items = items;

    }
}

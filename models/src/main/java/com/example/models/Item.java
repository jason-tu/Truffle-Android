package com.example.models;

import java.util.UUID;

public class Item {

    private String name;
    private UUID id;
    private float price;

    public Item(UUID id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


}

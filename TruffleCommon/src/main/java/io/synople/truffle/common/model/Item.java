package io.synople.truffle.common.model;

import java.util.UUID;

public class Item {

    private String name;
    private String id;
    private float price;

    public Item(String id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

package io.synople.truffle.common.model;

import java.util.List;

public class Vendor {

    private String id;
    private String name;
    private List<Item> items;

    public Vendor(String id, String name, List<Item> items) {
        this.id = id;
        this.name = name;
        this.items = items;
    }
}

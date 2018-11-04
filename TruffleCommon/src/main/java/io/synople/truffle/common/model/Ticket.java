package io.synople.truffle.common.model;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Ticket {

    private String id;
    private List<Item> items;
    private String customerId;
    private String vendorId;
    private String time;
    private double amount;

    public Ticket(String id, List<Item> items, String customerId, String vendorId, String time, double amount) {
        this.id = id;
        this.items = items;

        this.customerId = customerId;
        this.vendorId = vendorId;
        this.time = time;
        this.amount = amount;
    }

    public Ticket() {
        id = UUID.randomUUID().toString();
        items = new ArrayList<>();
        customerId = "";
        vendorId = "";
        time = "";
        amount = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

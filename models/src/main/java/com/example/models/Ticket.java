package com.example.models;


import java.sql.Time;
import java.util.UUID;

public class Ticket {

    private UUID id;
    private Item[] items;
    private User customer;
    private User vendor;
    private Time time;
    private double amount;

    public Ticket(UUID id, Item[] items, User customer, User vendor, Time time, double amount) {
        this.id = id;
        this.items = items;
        this.customer = customer;
        this.vendor = vendor;
        this.time = time;
        this.amount = amount;

    }

}

package io.synople.truffle.common.model;


import java.sql.Time;
import java.util.List;
import java.util.UUID;

public class Ticket {

    private String id;
    private List<String> items;
    private String customerId;
    private String vendorId;
    private String time;
    private double amount;

    public Ticket(String id, List<String> items, String customerId, String vendorId, String time, double amount) {
        this.id = id;
        this.items = items;
        this.customerId = customerId;
        this.vendorId = vendorId;
        this.time = time;
        this.amount = amount;

    }

}

package io.synople.truffle.common.model;

import java.util.List;
import java.util.UUID;

public class User {

    private String id;
    private String name;
    private List<Ticket> transactions;

    public User(String id, String name) {
        this.id = id;
        this.name = name;

    }

}

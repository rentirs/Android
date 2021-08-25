package com.example.secondapp;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
    private String name;
    private String lastName;
    private String phone;
    private final UUID uuid;

    public User(UUID uuid){
        this.uuid = uuid;
    }

    public User(){
        this.uuid = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UUID getUuid() {
        return uuid;
    }
}

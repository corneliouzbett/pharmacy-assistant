package com.example.jecihjoy.pharmacyasistant.Model;

/**
 * Created by Jecihjoy on 4/13/2018.
 */

public class Medicine {
    String name, type, desc, expirydate;
    int amount, id;


    public Medicine( int id, int amount, String name, String type, String desc, String expirydate) {
        this.name = name;
        this.type = type;
        this.desc = desc;
        this.expirydate = expirydate;
        this.amount = amount;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public int getAmount() {
        return amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

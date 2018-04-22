package com.example.jecihjoy.pharmacyasistant.Model;

/**
 * Created by Jecihjoy on 4/16/2018.
 */

public class Replacements {
    private int id;
    private String oldMed, newMed, reason, typeA, typeB,alternatives, date;

    public Replacements(int id, String newMed, String reason, String typeA, String date) {
        this.id = id;
        this.newMed = newMed;
        this.reason = reason;
        this.typeA = typeA;
        this.date = date;
    }

    public Replacements(int id, String oldMed, String newMed, String reason) {
        this.id = id;
        this.oldMed = oldMed;
        this.newMed = newMed;
        this.reason = reason;
    }

    public Replacements(int id, String oldMed, String typeB, String typeA, String reason, String newMed, String alternatives) {
        this.id = id;
        this.oldMed = oldMed;
        this.newMed = newMed;
        this.reason = reason;
        this.typeA = typeA;
        this.typeB = typeB;
        this.alternatives = alternatives;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setTypeA(String typeA) {
        this.typeA = typeA;
    }

    public void setTypeB(String typeB) {
        this.typeB = typeB;
    }

    public void setAlternatives(String alternatives) {
        this.alternatives = alternatives;
    }

    public String getTypeA() {
        return typeA;
    }

    public String getTypeB() {
        return typeB;
    }

    public String getAlternatives() {
        return alternatives;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOldMed(String oldMed) {
        this.oldMed = oldMed;
    }

    public void setNewMed(String newMed) {
        this.newMed = newMed;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public String getOldMed() {
        return oldMed;
    }

    public String getNewMed() {
        return newMed;
    }

    public String getReason() {
        return reason;
    }
}

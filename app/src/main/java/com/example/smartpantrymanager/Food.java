package com.example.smartpantrymanager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Food {
    private int id;
    private String name;
    private int quantity;
    private String unit;
    private String expiryDate;

    public Food(int id, String name, int quantity, String unit, String expiryDate) {

        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.expiryDate = expiryDate;

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

    public void setName(String name) {

        this.name = name;
    }

    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }

    public String getUnit() {

        return unit;
    }

    public void setUnit(String unit) {

        this.unit = unit;
    }

    public String getExpiryDate() {

        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {

        this.expiryDate = expiryDate;
    }

    public String getExpiryStatus() {
        if (getExpiryDate() == null) {
            return "Unknown";
        }

        LocalDate parsedExpiryDate = LocalDate.parse(getExpiryDate(), DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate today = LocalDate.now();
        if (today.isBefore(parsedExpiryDate)) {
            return "Not Expired";
        } else {
            return "Expired";
        }
    }
}

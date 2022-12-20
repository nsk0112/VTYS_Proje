package com.example.vtys;

import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class Vehicle {
    private SimpleStringProperty id = null;
    private SimpleStringProperty date = null;
    private SimpleStringProperty type = null;
    private SimpleStringProperty purpose = null;
    private SimpleStringProperty plate = null;

    public Vehicle(){

    }

    public Vehicle(String id, String date, String type, String purpose, String plate) {
        this.id = new SimpleStringProperty(id);
        this.date = new SimpleStringProperty(date);
        this.type = new SimpleStringProperty(type);
        this.purpose = new SimpleStringProperty(purpose);
        this.plate = new SimpleStringProperty(plate);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id = new SimpleStringProperty(id);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date = new SimpleStringProperty(date);}

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type = new SimpleStringProperty(type);
    }

    public String getPurpose() {
        return purpose.get();
    }

    public SimpleStringProperty purposeProperty() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = new SimpleStringProperty(purpose);
    }

    public String getPlate() {
        return plate.get();
    }

    public SimpleStringProperty plateProperty() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = new SimpleStringProperty(plate);
    }
}

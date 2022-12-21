package com.example.vtys;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Farmer {
    public String getField() {
        return field.get();
    }

    public String getTool() {
        return tool.get();
    }

    public SimpleStringProperty toolProperty() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool.set(tool);
    }

    public SimpleStringProperty fieldProperty() {
        return field;
    }

    public void setField(String field) {
        this.field.set(field);
    }

    public String getFarmerID() {
        return farmerID.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public SimpleStringProperty farmerIDProperty() {
        return farmerID;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setFarmerID(String farmerID) {
        this.farmerID.set(farmerID);
    }

    private SimpleStringProperty farmerID = null;
    private SimpleStringProperty firstName = null;
    private SimpleStringProperty lastName = null;
    private SimpleStringProperty field = null;
    private SimpleStringProperty tool = null;
    private SimpleStringProperty vehicle = null;
    //private SimpleBooleanProperty work = null;

    public Farmer(String farmerID, String firstName, String lastName, String field, String vehicle, String tool) {

        this.farmerID = new SimpleStringProperty(farmerID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.field = new SimpleStringProperty(field);
        this.tool = new SimpleStringProperty(tool);
        this.vehicle = new SimpleStringProperty(vehicle);
        //this.work = new SimpleBooleanProperty(work);


    }
    public Farmer(){}


    public String getVehicle() {
        return vehicle.get();
    }

    public SimpleStringProperty vehicleProperty() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle.set(vehicle);
    }
}

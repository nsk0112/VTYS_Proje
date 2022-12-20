package com.example.vtys;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Farmer {

    private SimpleStringProperty farmerID = null;
    private SimpleStringProperty firstName = null;
    private SimpleStringProperty lastName = null;
    private SimpleStringProperty field = null;
    private SimpleStringProperty tool = null;
    private SimpleStringProperty vehicle = null;
    private SimpleBooleanProperty work = null;

    public Farmer(String farmerID, String firstName, String lastName, String field, String tool, String vehicle) {

        this.farmerID = new SimpleStringProperty(farmerID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.field = new SimpleStringProperty(field);
        this.tool = new SimpleStringProperty(tool);
        this.vehicle = new SimpleStringProperty(vehicle);
        //this.work = new SimpleBooleanProperty(work);


    }
    public Farmer(){}


}

package com.example.vtys;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Animal {
    private SimpleStringProperty animalID = null;
    private SimpleStringProperty species = null;
    private SimpleStringProperty type = null;
    private SimpleStringProperty quantity = null;
    private SimpleStringProperty gender = null;

    public String getQuantity() {
        return quantity.get();
    }

    public SimpleStringProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity.set(quantity);
    }

    public String getType() {
        return type.get();
    }

    public String getSpecies() {
        return species.get();
    }

    public SimpleStringProperty speciesProperty() {
        return species;
    }

    public void setSpecies(String species) {
        this.species.set(species);
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public Animal(){

    }

    public String getGender() {
        return gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getAnimalID() {
        return animalID.get();
    }

    public SimpleStringProperty animalIDProperty() {
        return animalID;
    }

    public void setAnimalID(String animalID) {
        this.animalID.set(animalID);
    }

    public Animal(String id, String species, String type, String quantity) {
        this.animalID = new SimpleStringProperty(id);
        this.species = new SimpleStringProperty(species);
        this.type = new SimpleStringProperty(type);
        this.quantity = new SimpleStringProperty(quantity);

    }

}

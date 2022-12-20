package com.example.vtys;

import javafx.beans.property.SimpleStringProperty;

public class Tool {
    private SimpleStringProperty id = null;
    private SimpleStringProperty name = null;
    private SimpleStringProperty quantity = null;
    private SimpleStringProperty explanation = null;

    public Tool() {
    }

    public Tool(String id, String name, String quantity, String explanation) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.quantity = new SimpleStringProperty(quantity);
        this.explanation = new SimpleStringProperty(explanation);
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

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getQuantity() {
        return quantity.get();
    }

    public SimpleStringProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = new SimpleStringProperty(quantity);
    }

    public String getExplanation() {
        return explanation.get();
    }

    public SimpleStringProperty explanationProperty() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = new SimpleStringProperty(explanation);
    }
}

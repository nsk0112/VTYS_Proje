package com.example.vtys;

import javafx.beans.property.SimpleStringProperty;

public class Product {
    private SimpleStringProperty productID = null;
    private SimpleStringProperty category = null;
    private SimpleStringProperty name = null;
    private SimpleStringProperty price = null;
    private SimpleStringProperty quantity = null;

    public String getCategory() {
        return category.get();
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

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category = new SimpleStringProperty(category);
    }

    public String getPrice() {
        return price.get();
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price = new SimpleStringProperty(price);
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

    public String getProductID() {
        return productID.get();
    }

    public SimpleStringProperty productIDProperty() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = new SimpleStringProperty(productID);
    }

    public Product(String productID, String category, String name, String price, String quantity) {

        this.productID = new SimpleStringProperty(productID);
        this.category = new SimpleStringProperty(category);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price);
        this.quantity = new SimpleStringProperty(quantity);
    }
    public Product(){}
}

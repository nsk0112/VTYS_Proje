package com.example.vtys;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Field {
    private SimpleStringProperty fieldID = null;
    private SimpleStringProperty productID = null;
    private SimpleStringProperty area = null;
    private SimpleStringProperty farmerID = null;
    private SimpleStringProperty total = null;

    public String getFieldID() {
        return fieldID.get();
    }

    public String getFarmerID() {
        return farmerID.get();
    }

    public String getTotal() {
        return total.get();
    }

    public SimpleStringProperty totalProperty() {
        return total;
    }

    public void setTotal(String total) {
        this.total = new SimpleStringProperty(total);
    }

    public SimpleStringProperty farmerIDProperty() {
        return farmerID;
    }

    public void setFarmerID(String farmerID) {
        this.farmerID = new SimpleStringProperty(farmerID);
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

    public String getArea() {
        return area.get();
    }

    public SimpleStringProperty areaProperty() {
        return area;
    }

    public void setArea(String area) {
        this.area = new SimpleStringProperty(area);
    }

    public SimpleStringProperty fieldIDProperty() {
        return fieldID;
    }

    public void setFieldID(String fieldID) {
        this.fieldID = new SimpleStringProperty(fieldID);
    }

    public Field(String id, String productID, String area, String farmerID, String total) {
        this.fieldID = new SimpleStringProperty(id);
        this.productID = new SimpleStringProperty(productID);
        this.area = new SimpleStringProperty(area);
        this.farmerID = new SimpleStringProperty(farmerID);
        this.total = new SimpleStringProperty(total);
    }
    public Field(){

    }


}

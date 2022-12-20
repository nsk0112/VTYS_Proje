package com.example.vtys;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class MainPage {
    public AnchorPane ap;
    public Label welcome;
    public Button animals;
    public Button products;
    public Button sales;
    public Button fields;
    public Button tools;
    public Button vehicles;


    Statement s;
    ResultSet rs;
    int userID;
    String user;
    String name;

    public MainPage() throws SQLException {
        String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;encrypt=true;database=Farm_db;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
        Connection con = DriverManager.getConnection(connectionUrl);

        s = con.createStatement();
    }

    public void createPage(String username) throws IOException, SQLException {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("C:\\Users\\lenovo\\IdeaProjects\\vtys\\src\\main\\java\\com\\example\\vtys\\paw.png"));
        stage.setTitle("FarmNN!");
        Parent root = (Parent) FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("MainPage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        rs = s.executeQuery("SELECT UserName, UserPassword, FarmerID FROM Users");

        while (rs.next()) {
            if (Objects.equals(username, rs.getString("Username"))) {
                userID = rs.getInt("FarmerID");
                user = rs.getString("Username");
            }
        }

        rs = s.executeQuery("SELECT FarmerFirstName FROM Farmer");

        while (rs.next()) {
            if (Objects.equals(userID, rs.getString("FarmerFirstName"))) {
                name = rs.getString("FarmerFirstName");
            }
        }
        stage.show();

    }

    public void animalsB() throws IOException, SQLException {
        ap.getScene().getWindow().hide();
        AnimalsPage animalsPage = new AnimalsPage();
        animalsPage.createPage();
    }

    public void productsB() throws IOException, SQLException {
        ap.getScene().getWindow().hide();
        ProductsPage productsPage = new ProductsPage();
        productsPage.createPage();
    }

    public void salesB() throws IOException, SQLException {
        ap.getScene().getWindow().hide();
        SalesPage salesPage = new SalesPage();
        salesPage.createPage();
    }

    public void fieldsB() throws IOException, SQLException {
        ap.getScene().getWindow().hide();
        FieldsPage fieldsPage = new FieldsPage();
        fieldsPage.createPage();
    }

    public void toolsB() throws IOException, SQLException {
        ap.getScene().getWindow().hide();
        ToolsPage toolsPage = new ToolsPage();
        toolsPage.createPage();
    }

    public void vehiclesB() throws IOException, SQLException {
        ap.getScene().getWindow().hide();
        VehiclesPage vehiclesPage = new VehiclesPage();
        vehiclesPage.createPage();
    }
}

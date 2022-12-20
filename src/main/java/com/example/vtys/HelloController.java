package com.example.vtys;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.*;

public class HelloController {
    @FXML
    public AnchorPane ap;
    public Button Enter;
    public TextField t1;
    public TextField t2;
    public Label label;

    @FXML
    protected void entryButtonClick() throws SQLException, IOException {
        Deneme deneme = new Deneme();
        deneme.setLogIn();

        String username = t1.getText();
        String password = t2.getText();

        if (deneme.logIn(username, password)) {
            label.setText("Login Successful");
            ap.getScene().getWindow().hide();
            MainPage mp = new MainPage();
            mp.createPage(username);
        } else {
            label.setText("Login Failed");

        }
    }

    @FXML
    public void onEnter(ActionEvent ae) throws SQLException, IOException {
        entryButtonClick();
    }

}

package com.example.vtys;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class SalesPage implements Initializable {
    public AnchorPane ap;
    public Button back_btn;
    public Label pageLabel;

    @FXML
    private Button btnadd;

    @FXML
    private Button btndel;

    @FXML
    private TextField fname;

    @FXML
    private TextField id;

    @FXML
    private TextField lname;

    @FXML
    private Button refbtn;

    @FXML
    private TextField ToolID;

    @FXML
    private TextField vehicleID;

    @FXML
    private TextField fieldID;


    @FXML
    public Label delLabel;

    public TableView<Farmer> table;
    public TableColumn<Farmer, String> col1;
    public TableColumn<Farmer, String> col2;
    public TableColumn<Farmer, String> col3;
    public TableColumn<Farmer, String> col4;
    public TableColumn<Farmer, String> col5;
    public TableColumn<Farmer, String> col6;
    //public TableColumn<Farmer, ObservableValue<Boolean>> col7;

    Statement s;
    ResultSet rs;

    public SalesPage() throws SQLException {
        String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;encrypt=true;database=Farm_db;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
        Connection con = DriverManager.getConnection(connectionUrl);

        s = con.createStatement();
    }

    public void createPage() throws IOException {
        Stage stage = new Stage();
        Parent root = (Parent) FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("SalesPage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void back() throws IOException {
        ap.getScene().getWindow().hide();

        Stage stage = new Stage();
        Parent root = (Parent) FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("MainPage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void refreshPage() throws IOException {
        ap.getScene().getWindow().hide();

        Stage stage = new Stage();
        Parent root = (Parent) FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("SalesPage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }


    public void initialize(URL url, ResourceBundle rb){
        col1.setCellValueFactory(new PropertyValueFactory<Farmer, String>("farmerID"));
        col2.setCellValueFactory(new PropertyValueFactory<Farmer, String>("firstName"));
        col3.setCellValueFactory(new PropertyValueFactory<Farmer, String>("lastName"));
        col4.setCellValueFactory(new PropertyValueFactory<Farmer, String>("field"));
        col5.setCellValueFactory(new PropertyValueFactory<Farmer, String>("tool"));
        col6.setCellValueFactory(new PropertyValueFactory<Farmer, String>("vehicle"));
        //col7.setCellValueFactory(new PropertyValueFactory<Farmer, ObservableValue<Boolean>>("quantity"));

        try {
            table.setItems(getFarmers());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        table.setEditable(true);
        col1.setCellFactory(TextFieldTableCell.forTableColumn());
        col2.setCellFactory(TextFieldTableCell.forTableColumn());
        col3.setCellFactory(TextFieldTableCell.forTableColumn());
        col4.setCellFactory(TextFieldTableCell.forTableColumn());
        col5.setCellFactory(TextFieldTableCell.forTableColumn());
        col6.setCellFactory(TextFieldTableCell.forTableColumn());


    }



    public ObservableList<Farmer> getFarmers() throws SQLException {
//        String query = "SELECT Farmer.FarmerID, FarmerFirstName,FarmerLastName,ToolName, VehicleType,Farm.FieldID FROM Farmer\n" +
//                "Full outer join Farm ON Farmer.FarmerID=Farm.FarmerID\n" +
//                "left join Tools ON Farm.ToolID=Tools.ToolID\n" +
//                "left join Vehicle ON Farm.VehicleID=Vehicle.VehicleID\n" +
//                "left join Field ON Farm.FieldID=Field.FieldID";

        String query = "SELECT * FROM Farmer\n" +
                "left join Farm ON Farmer.FarmerID=Farm.FarmerID";
        rs = s.executeQuery(query);

        ObservableList<Farmer> farmers = FXCollections.observableArrayList();

        while (rs.next()){


            String id = rs.getString(1);
            String fname = rs.getString(2);
            String lname = rs.getString(3);
            String tool = rs.getString(4);
            String veh = rs.getString(5);
            String field = rs.getString(6);
            //Boolean work = (rs.getBoolean(7));


            farmers.add(new Farmer(id, fname, lname, tool,veh,field));
        }

        return farmers;
    }

    public void delButton() throws IOException {
        try{
            int x = Integer.parseInt(id.getText());
            String query = "DELETE FROM Farmer WHERE FarmerID="+x;
            s.executeUpdate(query);

        } catch (Exception e) {
            delLabel.setText("Please enter a valid value!!");
        }

        refreshPage();
    }

    public void addButton() {
        try {

            String one = id.getText();
            String two = fname.getText();
            String three = lname.getText();
            String four = ToolID.getText();
            String five = vehicleID.getText();
            String six = fieldID.getText();

            String query = "INSERT INTO Farmer (FarmerID, FarmerFirstName, FarmerLastName)" +
                    "VALUES (\'" + one + "\', \'" + two + "\', \'" + three + "\')";


            System.out.println(query);
            s.executeUpdate(query);

            id.setText("");
            fname.setText("");
            lname.setText("");
            ToolID.setText("");
            vehicleID.setText("");
            fieldID.setText("");


            query = "INSERT INTO Farm (FarmerID, ToolID, VehicleID,FieldID)" +
                    "VALUES (\'" + one + "\',  \'" + four + "\', \'" + five + "\',\'" + six + "\')";

            System.out.println(query);
            s.executeUpdate(query);


            refreshPage();
        } catch (Exception e) {
            delLabel.setText("An error occured");
            System.out.println(e.getStackTrace().toString());

        }
    }
}
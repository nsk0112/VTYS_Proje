package com.example.vtys;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class VehiclesPage implements Initializable {
    public AnchorPane ap;
    public Button refbtn;
    public Button back_btn;
    public Label pageLabel;
    public TableView<Vehicle> table;
    public TableColumn<Vehicle, String> col1;
    public TableColumn<Vehicle, String> col2;
    public TableColumn<Vehicle, String> col3;
    public TableColumn<Vehicle, String> col4;
    public TableColumn<Vehicle, String> col5;
    public TextField search;
    public TextField doc;
    public TextField type;
    public TextField purp;
    public TextField numberp;
    public Button add;
    public Label delLabel;
    public TextField idField;
    public Button delete;
    public ObservableList<Vehicle> vehicles;

    Statement s;
    ResultSet rs;

    public VehiclesPage() throws SQLException {
        String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;encrypt=true;database=Farm_db;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
        Connection con = DriverManager.getConnection(connectionUrl);

        s = con.createStatement();
    }

    public void createPage() throws IOException, SQLException {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("C:\\Users\\lenovo\\IdeaProjects\\vtys\\src\\main\\java\\com\\example\\vtys\\paw.png"));
        stage.setTitle("FarmNN!");
        Parent root = (Parent) FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("VehiclesPage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();

    }

    public void delButton() throws IOException {
        try{
            int x = Integer.parseInt(idField.getText());
            String query = "DELETE FROM Vehicle WHERE VehicleID="+x;
            s.executeUpdate(query);

        } catch (Exception e) {
            delLabel.setText("Please enter a valid value");
        }

        refreshPage();
    }

    public void addButton(){
        try{
            String one = doc.getText();
            String two = type.getText();
            String three = purp.getText();
            String four = numberp.getText();

            String query = "INSERT INTO Vehicle (DateOfCheck, VehicleType, Purpose, NumberPlate)" +
                    "VALUES (\'"+one+"\', \'"+two+"\', \'"+three+"\', \'"+four+"\')";
            System.out.println(query);
            s.executeUpdate(query);

            doc.setText("");
            type.setText("");
            purp.setText("");
            numberp.setText("");

            refreshPage();
        }
        catch (Exception e){
            delLabel.setText("An error occured");
            System.out.println(e.getStackTrace().toString());
        }
    }

    public void editCell(TableColumn.CellEditEvent edittedCell) throws SQLException {
        Vehicle vehicleSelected = table.getSelectionModel().getSelectedItem();
        int index = table.getEditingCell().getColumn();
//        System.out.println(index);

        if (index==1) {
            String oldVal = edittedCell.getOldValue().toString();
            try{
                vehicleSelected.setDate(edittedCell.getNewValue().toString());
                String select = vehicleSelected.getDate();
                String selid = vehicleSelected.getId();
                String query = "UPDATE Vehicle SET DateOfCheck=\'"+select+"\' WHERE DateOfCheck=\'" +oldVal+"\' AND VehicleID="+selid;
                s.executeUpdate(query);
            }
            catch (Exception e){
                delLabel.setText("Please enter a valid value");
//                String old = edittedCell.getOldValue().toString();
                vehicleSelected.setDate(oldVal);
            }
        }
        else if(index==2) {
            String oldVal = edittedCell.getOldValue().toString();
            vehicleSelected.setType(edittedCell.getNewValue().toString());
            String select = vehicleSelected.getType();
            String selid = vehicleSelected.getId();
            String query = "UPDATE Vehicle SET VehicleType=\'"+select+"\' WHERE VehicleType=\'" +oldVal+"\' AND VehicleID="+selid;
            s.executeUpdate(query);
        }
        else if(index==3) {
            String oldVal = edittedCell.getOldValue().toString();
            vehicleSelected.setPurpose(edittedCell.getNewValue().toString());
            String select = vehicleSelected.getPurpose();
            String selid = vehicleSelected.getId();
            String query = "UPDATE Vehicle SET Purpose=\'"+select+"\' WHERE Purpose=\'" +oldVal+"\' AND VehicleID="+selid;
            s.executeUpdate(query);
        }
        else if(index==4) {
            String oldVal = edittedCell.getOldValue().toString();
            vehicleSelected.setPlate(edittedCell.getNewValue().toString());
            String select = vehicleSelected.getPlate();
            String selid = vehicleSelected.getId();
            String query = "UPDATE Vehicle SET NumberPlate=\'"+select+"\' WHERE NumberPlate=\'" +oldVal+"\' AND VehicleID="+selid;
            s.executeUpdate(query);
        }
    }

    public void search() throws SQLException {
        String filter = search.getText();

        ObservableList<Vehicle> v = getVehicles();
        FilteredList<Vehicle> filtered = new FilteredList<>(v);
        table.setItems(filtered);

        filtered.setPredicate(new Predicate<Vehicle>() {
            @Override
            public boolean test(Vehicle vehicle) {
                if(vehicle.getId().contains(filter))return true;
                else if(vehicle.getDate().contains(filter)) return true;
                else if(vehicle.getType().toLowerCase().contains(filter)) return true;
                else if(vehicle.getPurpose().toLowerCase().contains(filter)) return true;
                else if(vehicle.getPlate().toLowerCase().contains(filter)) return true;
                return false;
            }
        });

    }

    public void refreshPage() throws IOException {
        ap.getScene().getWindow().hide();

        Stage stage = new Stage();
        stage.getIcons().add(new Image("C:\\Users\\lenovo\\IdeaProjects\\vtys\\src\\main\\java\\com\\example\\vtys\\paw.png"));
        stage.setTitle("FarmNN!");
        Parent root = (Parent) FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("VehiclesPage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void back() throws IOException {
        ap.getScene().getWindow().hide();

        Stage stage = new Stage();
        Parent root = (Parent) FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("MainPage.fxml")));
        stage.getIcons().add(new Image("C:\\Users\\lenovo\\IdeaProjects\\vtys\\src\\main\\java\\com\\example\\vtys\\paw.png"));
        stage.setTitle("FarmNN!");
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        col1.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("id"));
        col2.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("date"));
        col3.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("type"));
        col4.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("purpose"));
        col5.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("plate"));

        try {
            table.setItems(getVehicles());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        table.setEditable(true);
        col1.setCellFactory(TextFieldTableCell.forTableColumn());
        col2.setCellFactory(TextFieldTableCell.forTableColumn());
        col3.setCellFactory(TextFieldTableCell.forTableColumn());
        col4.setCellFactory(TextFieldTableCell.forTableColumn());
        col5.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    public ObservableList<Vehicle> getVehicles() throws SQLException {
        String query = "SELECT * FROM Vehicle";
        rs = s.executeQuery(query);

        vehicles = FXCollections.observableArrayList();

        while (rs.next()){
            String id = rs.getString(1);
            String date = rs.getDate(2).toString();
            String type = rs.getString(3);
            String purpose = rs.getString(4);
            String plate = rs.getString(5);

            vehicles.add(new Vehicle(id, date, type, purpose, plate));
        }

        return vehicles;
    }
}

package com.example.vtys;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class FieldsPage implements Initializable {
    public AnchorPane ap;
    public Button refbtn;
    public Button back_btn;
    public Label pageLabel;

    Statement s;
    ResultSet rs;


    public TableView<Field> table;
    public TableColumn<Field, String> col1;
    public TableColumn<Field, String> col2;
    public TableColumn<Field, String> col3;
    public TableColumn<Field, String> col4;
    public TableColumn<Field, String> col5;

    @FXML
    private Button delete;

    public TextField farmerID;

    public TextField fieldID;

    public TextField productID;

    public TextField idField;

    public Label delLabel;



    public FieldsPage() throws SQLException {
        String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;encrypt=true;database=Farm_db;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
        Connection con = DriverManager.getConnection(connectionUrl);

        s = con.createStatement();
    }

    public void createPage() throws IOException {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("C:\\Users\\lenovo\\IdeaProjects\\vtys\\src\\main\\java\\com\\example\\vtys\\paw.png"));
        stage.setTitle("FarmNN!");
        Parent root = (Parent) FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("FieldsPage.fxml")));
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

    public void initialize(URL url, ResourceBundle rb){
        col1.setCellValueFactory(new PropertyValueFactory<Field, String>("fieldID"));
        col2.setCellValueFactory(new PropertyValueFactory<Field, String>("area"));
        col3.setCellValueFactory(new PropertyValueFactory<Field, String>("productID"));
        col4.setCellValueFactory(new PropertyValueFactory<Field, String>("farmerID"));
        col5.setCellValueFactory(new PropertyValueFactory<Field, String>("total"));

        try {
            table.setItems(getFields());
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

    public ObservableList<Field> getFields() throws SQLException {
        String query = "\n" +
                "SELECT FieldID, Area, ProductName, CONCAT(FarmerFirstName, ' ', FarmerLastName) AS 'full name',  TotalEarnings\n" +
                "FROM Field\n" +
                "LEFT JOIN Farmer ON Field.FarmerID=Farmer.FarmerID LEFT JOIN Product ON Field.ProductID=Product.ProductID\n" +
                "ORDER BY Field.FieldID";
        rs = s.executeQuery(query);

        ObservableList<Field> fields = FXCollections.observableArrayList();

        while (rs.next()){
            String fieldID = rs.getString(1);
            String area = rs.getString(3);
            String prod = rs.getString(2);
            String farmer = rs.getString(4);
            String earn = rs.getString(5);

            fields.add(new Field(fieldID, area, prod, farmer, earn));
        }

        return fields;
    }

    public void delButton() throws IOException {

        try{
            int x = Integer.parseInt(idField.getText());
            String query = "DELETE FROM Field WHERE FieldID="+x;
            s.executeUpdate(query);

        } catch (Exception e) {
            delLabel.setText("Please enter a valid value");
        }

        refreshPage();
    }

    public void addButton(){
        try{
            String one = fieldID.getText();
            String two = farmerID.getText();
            String three = productID.getText();

//            "UPDATE Vehicle SET VehicleType=\'"+select+"\' WHERE VehicleType=\'" +oldVal+"\' AND VehicleID="+selid;


//            String query = "ALTER TABLE Field ALTER COLUMN FarmerID " + two;
//            System.out.println(query);
//            System.out.println(two);
//            s.executeUpdate(query);
//
//            String query1 = "ALTER TABLE Field ALTER COLUMN ProductID " + three;
//            System.out.println(query1);
//            s.executeUpdate(query1);

            String query = "UPDATE Field SET ProductID="+three+" WHERE FieldID="+one;
            String query1 = "UPDATE Field SET FarmerID="+two+" WHERE FieldID="+one;

            s.executeUpdate(query);
            s.executeUpdate(query1);


            fieldID.setText("");
            farmerID.setText("");
            productID.setText("");


            refreshPage();
        }
        catch (Exception e){
            delLabel.setText("An error occured");
            System.out.println(e.getStackTrace().toString());
        }
    }

    public void refreshPage() throws IOException {
        ap.getScene().getWindow().hide();

        Stage stage = new Stage();
        stage.getIcons().add(new Image("C:\\Users\\lenovo\\IdeaProjects\\vtys\\src\\main\\java\\com\\example\\vtys\\paw.png"));
        stage.setTitle("FarmNN!");
        Parent root = (Parent) FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("FieldsPage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void editCell(TableColumn.CellEditEvent edittedCell) throws SQLException {
        Field fieldSelected = table.getSelectionModel().getSelectedItem();
        int index = table.getEditingCell().getColumn();
//        System.out.println(index);

        if (index==1) {
            String oldVal = edittedCell.getOldValue().toString();
            try{
                fieldSelected.setArea(edittedCell.getNewValue().toString());
                String select = fieldSelected.getArea();
                String selid = fieldSelected.getFieldID();
                String query = "UPDATE Vehicle SET DateOfCheck=\'"+select+"\' WHERE DateOfCheck=\'" +oldVal+"\' AND VehicleID="+selid;
                s.executeUpdate(query);
            }
            catch (Exception e){
                delLabel.setText("Please enter a valid value");
//                String old = edittedCell.getOldValue().toString();
                fieldSelected.setFieldID(oldVal);
            }
        }
        else if(index==2) {
            String oldVal = edittedCell.getOldValue().toString();
            fieldSelected.setProductID(edittedCell.getNewValue().toString());
            String select = fieldSelected.getProductID();
            String selid = fieldSelected.getFieldID();
            String query = "UPDATE Field SET ProductID=\'"+select+"\' WHERE ProductID=\'" +oldVal+"\' AND FieldID="+selid;
            s.executeUpdate(query);
        }
        else if(index==3) {
            String oldVal = edittedCell.getOldValue().toString();
            fieldSelected.setFarmerID(edittedCell.getNewValue().toString());
            String select = fieldSelected.getFarmerID();
            String selid = fieldSelected.getFieldID();
            String query = "UPDATE Field SET FarmerID=\'"+select+"\' WHERE FarmerID=\'" +oldVal+"\' AND FieldID="+selid;
            s.executeUpdate(query);
        }
        else if(index==4) {
            String oldVal = edittedCell.getOldValue().toString();
            fieldSelected.setTotal(edittedCell.getNewValue().toString());
            String select = fieldSelected.getTotal();
            String selid = fieldSelected.getFieldID();
            String query = "UPDATE Field SET TotalEarnings=\'"+select+"\' WHERE TotalEarnings=\'" +oldVal+"\' AND FieldID="+selid;
            s.executeUpdate(query);
        }
    }
}

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
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class AnimalsPage implements Initializable {
    public AnchorPane ap;
    public Button refbtn;
    public Button back_btn;
    public Label pageLabel;

    public TableView<Animal> table;
    public TableColumn<Animal, String> col1;
    public TableColumn<Animal, String> col2;
    public TableColumn<Animal, String> col3;
    public TableColumn<Animal, String> col5;
    public TextField animalID;
    public TextField type;
    public TextField age;
    public TextField quantity;

    public Button add;
    public Label delLabel;
    public TextField idField;
    public Button delete;

    public TextField search;

    Statement s;
    ResultSet rs;

    public AnimalsPage() throws SQLException {
        String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;encrypt=true;database=Farm_db;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
        Connection con = DriverManager.getConnection(connectionUrl);

        s = con.createStatement();
    }

    public void createPage() throws IOException, SQLException {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("C:\\Users\\lenovo\\IdeaProjects\\vtys\\src\\main\\java\\com\\example\\vtys\\paw.png"));
        stage.setTitle("FarmNN!");
        Parent root = (Parent) FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("AnimalsPage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();

    }

    public void delButton() throws IOException {

        try{
            int x = Integer.parseInt(idField.getText());
            String query = "DELETE FROM Animal WHERE AnimalID="+x;
            s.executeUpdate(query);

        } catch (Exception e) {
            delLabel.setText("Please enter a valid value");
        }

        refreshPage();
    }

    public void addButton(){
        try{
            String one = animalID.getText();
            String two = type.getText();
            String three = age.getText();
            String four = quantity.getText();

            String query = "INSERT INTO Animal (AnimalID, AnimalType, Species, Quantity)" +
                    "VALUES (\'"+one+"\', \'"+two+"\', \'"+three+"\', \'"+four+"\')";

            System.out.println(query);
            s.executeUpdate(query);

            animalID.setText("");
            type.setText("");
            age.setText("");
            quantity.setText("");

            refreshPage();
        }
        catch (Exception e){
            delLabel.setText("An error occured");
            System.out.println(e.getStackTrace().toString());
        }
    }

    public void editCell(TableColumn.CellEditEvent edittedCell) throws SQLException {

        Animal animalselected = table.getSelectionModel().getSelectedItem();
        int index = table.getEditingCell().getColumn();
        System.out.println(index);

        if (index==1) {
//            String oldVal = edittedCell.getOldValue().toString();
//            animalselected.setAnimalID(edittedCell.getNewValue().toString());
//            String select = animalselected.getAnimalID();
//            String selid = animalselected.getAnimalID();
//            String query = "UPDATE Animal SET AnimalID=\'"+select+"\' WHERE AnimalID=\'" +oldVal+"\' AND AnimalID="+selid;
//            s.executeUpdate(query);
        }
        else if(index==1) {
            String oldVal = edittedCell.getOldValue().toString();
            animalselected.setType(edittedCell.getNewValue().toString());
            String select = animalselected.getSpecies();
            String selid = animalselected.getAnimalID();
            String query = "UPDATE Animal SET Species=\'"+select+"\' WHERE Species=\'" +oldVal+"\' AND AnimalID="+selid;
            s.executeUpdate(query);
        }
        else if(index==2) {
            String oldVal = edittedCell.getOldValue().toString();
            animalselected.setType(edittedCell.getNewValue().toString());
            String select = animalselected.getType();
            String selid = animalselected.getAnimalID();
            String query = "UPDATE Animal SET AnimalType=\'"+select+"\' WHERE AnimalType=\'" +oldVal+"\' AND AnimalID="+selid;
            s.executeUpdate(query);
        }
        else if(index==3) {
            String oldVal = edittedCell.getOldValue().toString();
            animalselected.setQuantity(edittedCell.getNewValue().toString());
            String select = animalselected.getQuantity();
            String selid = animalselected.getAnimalID();
            String query = "UPDATE Animal SET Quantity=\'"+select+"\' WHERE Quantity=\'" +oldVal+"\' AND AnimalID="+selid;
            s.executeUpdate(query);
        }
    }

    public void refreshPage() throws IOException {
        ap.getScene().getWindow().hide();

        Stage stage = new Stage();
        stage.getIcons().add(new Image("C:\\Users\\lenovo\\IdeaProjects\\vtys\\src\\main\\java\\com\\example\\vtys\\paw.png"));
        stage.setTitle("FarmNN!");
        Parent root = (Parent) FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("AnimalsPage.fxml")));
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

    @Override
    public void initialize(URL url, ResourceBundle rb){
        col1.setCellValueFactory(new PropertyValueFactory<Animal, String>("animalID"));
        col2.setCellValueFactory(new PropertyValueFactory<Animal, String>("species"));
        col3.setCellValueFactory(new PropertyValueFactory<Animal, String>("type"));
        col5.setCellValueFactory(new PropertyValueFactory<Animal, String>("quantity"));

        try {
            table.setItems(getAnimals());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        table.setEditable(true);
        col1.setCellFactory(TextFieldTableCell.forTableColumn());
        col2.setCellFactory(TextFieldTableCell.forTableColumn());
        col3.setCellFactory(TextFieldTableCell.forTableColumn());
        col5.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    public ObservableList<Animal> getAnimals() throws SQLException {
        String query = "select AnimalID, Species,  AnimalType, Quantity from Animal";
        rs = s.executeQuery(query);

        ObservableList<Animal> animals = FXCollections.observableArrayList();

        while (rs.next()){
            String id = rs.getString(1);
            String species = rs.getString(2);
            String type = rs.getString(3);
            String quan = rs.getString(4);

            animals.add(new Animal(id, species, type, quan));
        }

        return animals;
    }

    public void search() throws SQLException {
        String filter = search.getText();

        ObservableList<Animal> a = getAnimals();
        FilteredList<Animal> filtered = new FilteredList<>(a);
        table.setItems(filtered);

        filtered.setPredicate(new Predicate<Animal>() {
            @Override
            public boolean test(Animal animal) {
                if(animal.getAnimalID().toLowerCase().contains(filter)) return true;
                else if(animal.getSpecies().toLowerCase().contains(filter)) return true;
                else if(animal.getType().toLowerCase().contains(filter)) return true;
                else if(animal.getQuantity().toLowerCase().contains(filter)) return true;

                return false;
            }
        });
    }
}

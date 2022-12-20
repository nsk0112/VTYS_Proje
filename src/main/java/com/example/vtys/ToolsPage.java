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

public class ToolsPage implements Initializable {
    public AnchorPane ap;
    public Button refbtn;
    public Button back_btn;
    public Label pageLabel;
    public TableView<Tool> table;
    public TableColumn<Tool, String> col1;
    public TableColumn<Tool, String> col2;
    public TableColumn<Tool, String> col3;
    public TableColumn<Tool, String> col4;
    public TextField tname;
    public TextField tquantity;
    public TextField texplanation;
    public Button add;
    public Label delLabel;
    public TextField idField;
    public Button delete;
    public TextField search;

    Statement s;
    ResultSet rs;

    public ToolsPage() throws SQLException {
        String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;encrypt=true;database=Farm_db;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
        Connection con = DriverManager.getConnection(connectionUrl);

        s = con.createStatement();
    }

    public void createPage() throws IOException {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("C:\\Users\\lenovo\\IdeaProjects\\vtys\\src\\main\\java\\com\\example\\vtys\\paw.png"));
        stage.setTitle("FarmNN!");
        Parent root = (Parent) FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("ToolsPage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void refreshPage() throws IOException {
        ap.getScene().getWindow().hide();

        Stage stage = new Stage();
        stage.getIcons().add(new Image("C:\\Users\\lenovo\\IdeaProjects\\vtys\\src\\main\\java\\com\\example\\vtys\\paw.png"));
        stage.setTitle("FarmNN!");
        Parent root = (Parent) FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("ToolsPage.fxml")));
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

    public void editCell(TableColumn.CellEditEvent ediittedCell) throws SQLException{
        Tool toolSelected = table.getSelectionModel().getSelectedItem();
        int index = table.getEditingCell().getColumn();
        System.out.println(index);

        if(index==0){

        }

        else if(index==1){
            String oldVal = ediittedCell.getOldValue().toString();
            toolSelected.setName(ediittedCell.getNewValue().toString());
            String select = toolSelected.getName();
            String selid = toolSelected.getId();
            String query = "UPDATE Tools SET ToolName=\'"+select+"\' WHERE ToolName=\'"+oldVal+"\' AND ToolID="+selid;
            s.executeUpdate(query);
        }

        else if(index==2){
            String oldVal = ediittedCell.getOldValue().toString();
            toolSelected.setQuantity(ediittedCell.getNewValue().toString());
            String select = toolSelected.getQuantity();
            String selid = toolSelected.getId();
            String query = "UPDATE Tools SET Quantity=\'"+select+"\' WHERE Quantity=\'"+oldVal+"\' AND ToolID="+selid;
            s.executeUpdate(query);
        }

        else if(index==3){
            String oldVal = ediittedCell.getOldValue().toString();
            toolSelected.setExplanation(ediittedCell.getNewValue().toString());
            String select = toolSelected.getExplanation();
            String selid = toolSelected.getId();
            String query = "UPDATE Tools SET Explanation=\'"+select+"\' WHERE Explanation=\'"+oldVal+"\' AND ToolID="+selid;
            s.executeUpdate(query);
        }
    }

    public void delButton() throws IOException {
        try{
            int x = Integer.parseInt(idField.getText());
            String query = "DELETE FROM Tools WHERE ToolID="+x;
            s.executeUpdate(query);

        } catch (Exception e) {
            delLabel.setText("Please enter a valid value");
        }

        refreshPage();
    }

    public void addButton(){
        try{
            String one = tname.getText();
            String two = tquantity.getText();
            String three = texplanation.getText();

            String query = "INSERT INTO Tools (Toolname, Quantity, Explanation)" +
                    "VALUES (\'"+one+"\', \'"+two+"\', \'"+three+"\')";
            System.out.println(query);
            s.executeUpdate(query);

            tname.setText("");
            tquantity.setText("");
            texplanation.setText("");

            refreshPage();
        }
        catch (Exception e){
            delLabel.setText("An error occured");
            System.out.println(e.getStackTrace().toString());
        }
    }

    public void search() throws SQLException {
        String filter = search.getText();

        ObservableList<Tool> t = getTools();
        FilteredList<Tool> filtered = new FilteredList<>(t);
        table.setItems(filtered);

        filtered.setPredicate(new Predicate<Tool>() {
            @Override
            public boolean test(Tool tool) {
                if(tool.getId().toLowerCase().contains(filter)) return true;
                else if(tool.getName().toLowerCase().contains(filter)) return true;
                else if(tool.getExplanation().toLowerCase().contains(filter)) return true;
                else if(tool.getQuantity().toLowerCase().contains(filter)) return true;
                return false;
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        col1.setCellValueFactory(new PropertyValueFactory<Tool, String>("id"));
        col2.setCellValueFactory(new PropertyValueFactory<Tool, String>("name"));
        col3.setCellValueFactory(new PropertyValueFactory<Tool, String>("quantity"));
        col4.setCellValueFactory(new PropertyValueFactory<Tool, String>("explanation"));

        try {
            table.setItems(getTools());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        table.setEditable(true);

        col1.setCellFactory(TextFieldTableCell.forTableColumn());
        col2.setCellFactory(TextFieldTableCell.forTableColumn());
        col3.setCellFactory(TextFieldTableCell.forTableColumn());
        col4.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public ObservableList<Tool> getTools() throws SQLException{
        String query = "SELECT * FROM Tools";
        rs = s.executeQuery(query);

        ObservableList<Tool> tools = FXCollections.observableArrayList();

        while (rs.next()){
            String id = rs.getString(1);
            String name = rs.getString(2);
            String quantity = rs.getString(3);
            String explanation = rs.getString(4);

            tools.add(new Tool(id, name, quantity, explanation));
        }

        return tools;
    }
}

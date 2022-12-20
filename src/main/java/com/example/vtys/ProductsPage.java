package com.example.vtys;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import java.util.function.Predicate;

public class ProductsPage implements Initializable {
    public AnchorPane ap;
    public Button refbtn;
    public Button back_btn;
    public Label pageLabel;
    public TextField search;

    public TableView<Product> table;
    public TableColumn<Product, String> col1;
    public TableColumn<Product, String> col2;
    public TableColumn<Product, String> col3;
    public TableColumn<Product, String> col4;
    public TableColumn<Product, String> col5;

    @FXML
    private TextField avg;
    @FXML
    private TextField highest_earning;
    @FXML
    private TextField max_price1;
    @FXML
    private TextField max_price2;

    Statement s;
    ResultSet rs;

    public ProductsPage() throws SQLException {
        String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;encrypt=true;database=Farm_db;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
        Connection con = DriverManager.getConnection(connectionUrl);

        s = con.createStatement();
    }

    public void createPage() throws IOException, SQLException {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("C:\\Users\\lenovo\\IdeaProjects\\vtys\\src\\main\\java\\com\\example\\vtys\\paw.png"));
        stage.setTitle("FarmNN!");
        Parent root = (Parent) FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("ProductsPage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();

    }

    public void editCell(TableColumn.CellEditEvent edittedCell) throws SQLException {

        Product selected = table.getSelectionModel().getSelectedItem();
        int index = table.getEditingCell().getColumn();
        System.out.println(index);

        if (index==0) {
//            String oldVal = edittedCell.getOldValue().toString();
//            selected.setProductID(edittedCell.getNewValue().toString());
//            String select = selected.getProductID();
//            String selid = selected.getProductID();
//            String query = "UPDATE Product SET AnimalID=\'"+select+"\' WHERE AnimalID=\'" +oldVal+"\' AND AnimalID="+selid;
//            s.executeUpdate(query);
        }
        else if(index==1) {
            String oldVal = edittedCell.getOldValue().toString();
            selected.setCategory(edittedCell.getNewValue().toString());
            String select = selected.getCategory();
            String selid = selected.getProductID();
            String query = "UPDATE Product SET CategoryID=\'"+select+"\' WHERE CategoryID=\'" +oldVal+"\' AND ProductID="+selid;
            s.executeUpdate(query);
        }
        else if(index==2) {
            String oldVal = edittedCell.getOldValue().toString();
            selected.setName(edittedCell.getNewValue().toString());
            String select = selected.getName();
            String selid = selected.getProductID();
            String query = "UPDATE Product SET ProductName= \'"+select+"\' WHERE ProductName=\'" +oldVal+"\' AND ProductID="+selid;
            s.executeUpdate(query);
        }
        else if(index==3) {
            String oldVal = edittedCell.getOldValue().toString();
            selected.setPrice(edittedCell.getNewValue().toString());
            String select = selected.getPrice();
            String selid = selected.getProductID();
            String query = "UPDATE Product SET UnitPrice="+select+" WHERE UnitPrice=" +oldVal+" AND ProductID="+selid;
            s.executeUpdate(query);
        }

        else if(index==4) {
            String oldVal = edittedCell.getOldValue().toString();
            selected.setQuantity(edittedCell.getNewValue().toString());
            String select = selected.getQuantity();
            String selid = selected.getProductID();
            String query = "UPDATE Product SET Quantity=\'"+select+"\' WHERE Quantity=\'" +oldVal+"\' AND ProductID="+selid;
            s.executeUpdate(query);
        }
        calculate();
    }

    public void refreshPage() throws IOException {
        ap.getScene().getWindow().hide();

        Stage stage = new Stage();
        stage.getIcons().add(new Image("C:\\Users\\lenovo\\IdeaProjects\\vtys\\src\\main\\java\\com\\example\\vtys\\paw.png"));
        stage.setTitle("FarmNN!");
        Parent root = (Parent) FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("ProductsPage.fxml")));
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
    public void initialize(URL url, ResourceBundle rb) {
        col1.setCellValueFactory(new PropertyValueFactory<Product, String>("productID"));
        col2.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        col3.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        col4.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
        col5.setCellValueFactory(new PropertyValueFactory<Product, String>("quantity"));

        try {
            table.setItems(getProducts());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        table.setEditable(true);
        col1.setCellFactory(TextFieldTableCell.forTableColumn());
        col2.setCellFactory(TextFieldTableCell.forTableColumn());
        col3.setCellFactory(TextFieldTableCell.forTableColumn());
        col4.setCellFactory(TextFieldTableCell.forTableColumn());
        col5.setCellFactory(TextFieldTableCell.forTableColumn());

        calculate();

    }

    public void calculate(){
        try {
            String query = "SELECT  CAST(max(UnitPrice*Quantity) AS DECIMAL(15,2)) as total FROM Product LEFT JOIN Category ON Product.CategoryID=Category.CategoryID ";
            rs = s.executeQuery(query);
            while (rs.next()) {
                highest_earning.setText(rs.getString("total"));
            }

            query = "SELECT CAST( MAX(UnitPrice) AS DECIMAL(15,2)) AS LargestPrice FROM Product where CategoryID=200";
            rs = s.executeQuery(query);
            while (rs.next()) {
                max_price1.setText(rs.getString(1));
            }

            query = "SELECT CAST(MAX(UnitPrice) AS DECIMAL(15,2)) AS LargestPrice FROM Product where CategoryID=100";
            rs = s.executeQuery(query);
            while (rs.next()) {
                max_price2.setText(rs.getString(1));
            }

            query = "SELECT  CAST(avg(TotalEarnings/Area) AS DECIMAL(15,2)) as prosjek FROM Field";
            rs = s.executeQuery(query);
            while (rs.next()) {
                avg.setText(rs.getString(1));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Product> getProducts() throws SQLException {
        String query = "\n" +
                "SELECT ProductID,ProductName,CategoryName,CAST(UnitPrice AS DECIMAL(15,2)),Quantity FROM Product\n" +
                "LEFT JOIN Category ON Product.CategoryID=Category.CategoryID\n" +
                "ORDER BY Product.ProductID";
        rs = s.executeQuery(query);

        ObservableList<Product> products = FXCollections.observableArrayList();

        while (rs.next()) {
            String id = rs.getString(1);
            String cat = rs.getString(3);
            String name = rs.getString(2);
            String price = rs.getString(4);
            String quan = rs.getString(5);


            products.add(new Product(id, cat, name, price, quan));
        }

        return products;
    }

    public void search() throws SQLException {
        String filter = search.getText();

        ObservableList<Product> t = getProducts();
        FilteredList<Product> filtered = new FilteredList<>(t);
        table.setItems(filtered);

        filtered.setPredicate(new Predicate<Product>() {
            @Override
            public boolean test(Product product) {
                if(product.getProductID().toLowerCase().contains(filter)) return true;
                else if(product.getCategory().toLowerCase().contains(filter)) return true;
                else if(product.getName().toLowerCase().contains(filter)) return true;
                else if(product.getPrice().toLowerCase().contains(filter)) return true;
                else if(product.getQuantity().toLowerCase().contains(filter)) return true;
                return false;
            }
        });
    }


}



package com.example.vtys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Deneme {

    //     **************    CALISIYOR    ************************
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;encrypt=true;database=Northwind;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
//        Connection con = DriverManager.getConnection(connectionUrl);
//
//        Statement s = con.createStatement();
//        ResultSet rs = s.executeQuery("SELECT * FROM Musteriler ORDER BY MusteriAdi ASC");
//        while(rs.next()){
//            System.out.println("Kişi: "+rs.getString("MusteriAdi"));
//        }
//
//        System.out.println("tamam");
//    }

    Statement s;
    ResultSet rs;

    public Deneme() throws SQLException {
        String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;encrypt=true;database=Farm_db;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
        Connection con = DriverManager.getConnection(connectionUrl);

        s = con.createStatement();
    }

//    public void write() throws SQLException {
//        String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;encrypt=true;database=Northwind;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
//        Connection con = DriverManager.getConnection(connectionUrl);
//
//        Statement s = con.createStatement();
//        ResultSet rs = s.executeQuery("SELECT * FROM Musteriler ORDER BY MusteriAdi ASC");
//        while (rs.next()) {
//            System.out.println(rs.getString("MusteriAdi"));
//        }
//
//        System.out.println("tamam");
//    }

    public boolean logIn(String username, String password) throws SQLException {
        boolean val = false;
        while (rs.next()) {
            if (Objects.equals(username, rs.getString("UserName"))) {
                if (Objects.equals(password, rs.getString("UserPassword"))) {
                    val = true;
                    break;
                }
            }
        }
        return val;
    }


    public void setLogIn() throws SQLException {
        rs = s.executeQuery("SELECT UserName, UserPassword FROM Users");
    }
}
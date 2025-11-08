package com.tecsup.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {

    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String usuario = "root";
    private static String password = "";
    private static String url = "jdbc:mysql://localhost:3306/sistema_academico";

    static{
        try{
            Class.forName(driver);
        }catch(ClassNotFoundException e){
            System.out.println("Error: " + e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, usuario, password);
    }
}

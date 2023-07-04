package com.example.kurs_v1;

import java.sql.*;

public class DBconnection {
    //static Statement st;
    private static DBconnection instance = null;
    private Connection connection;
    public DBconnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver" );
        connection = DriverManager.getConnection(
                "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2020_kurs" ,
                "std_2020_kurs" , "00000000" );
        //st = connection.createStatement();
    }
    public static DBconnection getInstance() throws SQLException, ClassNotFoundException {
        if(instance == null){
            instance = new DBconnection();
        }
        return instance;
    }
    public Connection getConnection(){
        return connection;
    }
}

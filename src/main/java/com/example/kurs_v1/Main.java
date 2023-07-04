package com.example.kurs_v1;
import java.sql.*;



public class Main {
    public static void main(String[] args){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver" );
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2020_kurs" ,
                    "std_2020_kurs" , "00000000" );
            Statement statement = connection.createStatement();
            DBconnection db = new DBconnection();
            String query = "SELECT * FROM furniture_lines" ; // запрос :(((
            ResultSet result = statement.executeQuery(query); // получение результата запроса в таблицу данных Резалт сет
            Statement st = DBconnection.getInstance().getConnection().createStatement();
            ResultSet res = st.executeQuery(query);
            while(result.next()){
                int id = result.getInt("id" );
                String name = result.getString("name" );
                //String short_name = result.getString("short_name" );
                System.out.print("Actual furniture lines: " );
                System.out.print("id = " + id);
                System.out.println(", name = \" " + name + " \" " );
                //System.out.println(" , short name = \" " + short_name + " \" . " );
            }
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
package com.example.kurs_v1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Shop_role_model {


    public Shop_role_model() {

    }

    public static String getFaxNum (User user) throws SQLException, ClassNotFoundException {
        ResultSet res = getResultStores(user);
        String fax = "";
        while (res.next()){
            fax = res.getString("fax_number");
        }
        return fax;
    }

    public static String getAddress (User user) throws SQLException, ClassNotFoundException {
        ResultSet res = getResultStores(user);
        String address = "";
        while (res.next()){
            address = res.getString("address");
        }
        return address;
    }

    public static int getStoreid (User user) throws SQLException, ClassNotFoundException {
        ResultSet res = getResultStores(user);
        int storeid =0;
        while (res.next()){
            storeid = res.getInt("id");
        }
        return storeid;
    }

    protected static ResultSet getResultStores (User user) throws SQLException, ClassNotFoundException {
        ResultSet res = null;
        String select = "SELECT id, address, fax_number, user_id FROM stores WHERE user_id=?";
        PreparedStatement preparedStatement = DBconnection.getInstance().getConnection().prepareStatement(select);
        preparedStatement.setInt(1, User.getUserid());

        res = preparedStatement.executeQuery();
        return res;
    }

    public static ResultSet getResultFurniture () throws SQLException, ClassNotFoundException {
        int id=0;
        String type="";
        String article="";
        double price=0;
        String line="";


        String selectFurniture = "SELECT furniture_items.id, furniture_items.type, furniture_items.article, furniture_items.price, furniture_lines.name FROM furniture_items JOIN furniture_lines ON furniture_line_id=furniture_lines.id";
        PreparedStatement prst = DBconnection.getInstance().getConnection().prepareStatement(selectFurniture);
        ResultSet resultSet = prst.executeQuery();
        /*while(resultSet.next()){
            id = resultSet.getInt(1);
            type = resultSet.getString(2);
            article = resultSet.getString(3);
            price = resultSet.getDouble(4);
            line = resultSet.getString(5);
        }
        Furniture furniture = new Furniture(id, type, article, price, line);*/
        return resultSet;
    }

    public static ResultSet getResultOrders(int store_id) throws SQLException, ClassNotFoundException {

        int id=0;
        String order_number="";
        Date order_date=null;
        String furniture_name="";
        int quantity=0;
        String selectOrder = "SELECT orders.id, orders.order_number, orders.order_date, furniture_items.type, order_items.quantity  FROM orders\n" +
                "JOIN order_items ON orders.id=order_items.order_id JOIN furniture_items ON order_items.furniture_item_id=furniture_items.id\n" +
                "WHERE orders.store_id=?";
        PreparedStatement prst = DBconnection.getInstance().getConnection().prepareStatement(selectOrder);
        prst.setInt(1, store_id);
        ResultSet resultSet = prst.executeQuery();
        return resultSet;
    }
}

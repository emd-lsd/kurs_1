package com.example.kurs_v1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Admin_role_model {
    public static void updatePassword(int password, int userid) throws SQLException, ClassNotFoundException {
        String update = "UPDATE users SET passw=? WHERE id=?";
        PreparedStatement prst = DBconnection.getInstance().getConnection().prepareStatement(update);
        prst.setInt(1, password);
        prst.setInt(2, userid);
        prst.executeUpdate();
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

        return resultSet;
    }

    public static ResultSet getResultComponents() throws SQLException, ClassNotFoundException {
        String select = "SELECT furniture_item_components.id, furniture_items.type, components.type, components.code, components.price, furniture_item_components.quantity FROM furniture_item_components JOIN furniture_items ON furniture_item_components.furniture_item_id=furniture_items.id JOIN components ON furniture_item_components.component_id=components.id";
        PreparedStatement prst = DBconnection.getInstance().getConnection().prepareStatement(select);
        ResultSet res = prst.executeQuery();
        return res;
    }

    public static ResultSet getResultOrders() throws SQLException, ClassNotFoundException {

        int id=0;
        String order_number="";
        Date order_date=null;
        String furniture_name="";
        int quantity=0;
        String selectOrder = "SELECT orders.id, orders.order_number, orders.order_date, furniture_items.type, order_items.quantity  FROM orders\n" +
                "JOIN order_items ON orders.id=order_items.order_id JOIN furniture_items ON order_items.furniture_item_id=furniture_items.id";
        PreparedStatement prst = DBconnection.getInstance().getConnection().prepareStatement(selectOrder);
        //prst.setInt(1, store_id);
        ResultSet resultSet = prst.executeQuery();
        return resultSet;
    }

    public static ResultSet getResultStores() throws SQLException, ClassNotFoundException {
        String select = "SELECT id, address, fax_number, user_id FROM stores";
        PreparedStatement prst = DBconnection.getInstance().getConnection().prepareStatement(select);
        ResultSet res = prst.executeQuery();
        return res;
    }

    public  static ResultSet getResultUsers() throws SQLException, ClassNotFoundException {
        String select = "SELECT id, login, passw, role_id FROM users WHERE role_id=1 OR role_id=2 OR role_id=3";
        PreparedStatement prst = DBconnection.getInstance().getConnection().prepareStatement(select);
        ResultSet res = prst.executeQuery();
        return  res;

    }

    public static void updatePassUser(String id, String newpas) throws SQLException, ClassNotFoundException {
        String update = "UPDATE users SET passw=? WHERE id=?";
        PreparedStatement prst = DBconnection.getInstance().getConnection().prepareStatement(update);
        prst.setString(1, newpas);
        prst.setString(2, id);
        prst.executeUpdate();
    }

    public static void addnewWorker(String login, String passw) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO users(login, passw, role_id) VALUES (?,?,2)";
        PreparedStatement prst = DBconnection.getInstance().getConnection().prepareStatement(insert);
        prst.setString(1, login);
        prst.setString(2, passw);
        prst.executeUpdate();

    }

    public static void deleteUser(int id) throws SQLException, ClassNotFoundException {
        deleteStore(id);
        String delete = "DELETE FROM users WHERE users.id=?";
        PreparedStatement prst = DBconnection.getInstance().getConnection().prepareStatement(delete);
        prst.setInt(1, id);
        prst.executeUpdate();
    }

    public static void deleteStore(int userid) throws SQLException, ClassNotFoundException {
        String delete = "DELETE FROM stores WHERE stores.user_id=?";
        PreparedStatement prst = DBconnection.getInstance().getConnection().prepareStatement(delete);
        prst.setInt(1, userid);
        prst.executeUpdate();
    }
}

package com.example.kurs_v1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Worker_role_model {
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

    public static ResultSet getResultComponents() throws SQLException, ClassNotFoundException {
        String select = "SELECT furniture_item_components.id, furniture_items.type, components.type, components.code, components.price, furniture_item_components.quantity FROM furniture_item_components JOIN furniture_items ON furniture_item_components.furniture_item_id=furniture_items.id JOIN components ON furniture_item_components.component_id=components.id";
        PreparedStatement prst = DBconnection.getInstance().getConnection().prepareStatement(select);
        ResultSet res = prst.executeQuery();
        return res;
    }

    public static void addnewfLine(String fline) throws SQLException, ClassNotFoundException {
        String addline = "INSERT INTO furniture_lines(name) VALUES (?)";
        PreparedStatement prst = DBconnection.getInstance().getConnection().prepareStatement(addline);
        prst.setString(1,fline);
        prst.executeUpdate();

    }

    public static void addnewComponent(String type, String code, String price) throws SQLException, ClassNotFoundException {
        String addcomp = "INSERT INTO components(type, code, price) VALUES (?,?,?)";
        PreparedStatement prst = DBconnection.getInstance().getConnection().prepareStatement(addcomp);
        prst.setString(1,type);
        prst.setString(2, code);
        prst.setString(3, price);
        prst.executeUpdate();
    }

    public static void setItemComp(String furid, String compid, String quantity) throws SQLException, ClassNotFoundException {
        String setst = "INSERT INTO furniture_item_components(furniture_item_id, component_id, quantity) VALUES (?,?,?)";
        PreparedStatement prst = DBconnection.getInstance().getConnection().prepareStatement(setst);
        prst.setString(1,furid);
        prst.setString(2,compid);
        prst.setString(3, quantity);
        prst.executeUpdate();

    }

    public static void addnewFurniture(String typef, String article, String adprice, String linef) throws SQLException, ClassNotFoundException {

        String addfur = "INSERT INTO furniture_items(type, article, price, furniture_line_id) VALUES (?,?,?, (SELECT furniture_lines.id FROM furniture_lines WHERE furniture_lines.name=?))";
        PreparedStatement prst = DBconnection.getInstance().getConnection().prepareStatement(addfur);
        prst.setString(1, typef);
        prst.setString(2, article);
        prst.setString(3, adprice);
        prst.setString(4, linef);
        prst.executeUpdate();
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
}

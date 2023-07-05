package com.example.kurs_v1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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

    public static String CalculatePrice(String article, String quantity) throws SQLException, ClassNotFoundException {
        ResultSet res = null;
        int i = 0;
        String checkprice = "SELECT furniture_items.price*? FROM furniture_items WHERE furniture_items.article=?";
        PreparedStatement prst = DBconnection.getInstance().getConnection().prepareStatement(checkprice);
        prst.setString(1,quantity);
        prst.setString(2,article);
        res = prst.executeQuery();
        String price="";
        while (res.next()){
            price=res.getString(1);
            i++;
        }
        return price;
    }

    public static int addnewOrder(String article, String quantity) throws SQLException, ClassNotFoundException {
        LocalDate currentDate = LocalDate.now();
        int order_num = (int)(Math.random() * 100) + 1;
        int storeid = getStoreid(null);
        addfirstlyOrder(order_num, String.valueOf(currentDate), storeid);
        int orderid = getOrderId(order_num, storeid);
        String addorder = "INSERT INTO order_items(order_id, furniture_item_id, quantity) VALUES (?,(SELECT id FROM furniture_items WHERE furniture_items.article=?),?)";
        PreparedStatement prst = DBconnection.getInstance().getConnection().prepareStatement(addorder);
        prst.setInt(1, orderid);
        prst.setString(2, article);
        prst.setString(3, quantity);
        prst.executeUpdate();
        return order_num;
    }

    public  static void addfirstlyOrder(int order_number, String order_date, int storeid) throws SQLException, ClassNotFoundException {
        String firstlyorder = "INSERT INTO orders(order_number, order_date, store_id) VALUES (?,?,?)";
        PreparedStatement prst = DBconnection.getInstance().getConnection().prepareStatement(firstlyorder);
        prst.setInt(1, order_number);
        prst.setString(2,order_date);
        prst.setInt(3, storeid);
        prst.executeUpdate();
    }

    public  static int getOrderId(int order_number, int storeid) throws SQLException, ClassNotFoundException {
        String select = "SELECT id FROM orders WHERE orders.order_number=? AND orders.store_id=?";
        PreparedStatement prst = DBconnection.getInstance().getConnection().prepareStatement(select);
        prst.setInt(1, order_number);
        prst.setInt(2, storeid);
        ResultSet rst = prst.executeQuery();
        int orderid=0;
        while (rst.next()){
            orderid = rst.getInt(1);
        }
        return orderid;
    }

    public static void updateAddress(String address) throws SQLException, ClassNotFoundException {
        int storeid = getStoreid(null);
        String update = "UPDATE stores SET address=? WHERE id =?";
        PreparedStatement prst = DBconnection.getInstance().getConnection().prepareStatement(update);
        prst.setString(1, address);
        prst.setInt(2, storeid);
        prst.executeUpdate();
    }

    public static void updateFax(String fax) throws SQLException, ClassNotFoundException {
        int storeid = getStoreid(null);
        String update = "UPDATE stores SET fax_number=? WHERE id =?";
        PreparedStatement prst = DBconnection.getInstance().getConnection().prepareStatement(update);
        prst.setString(1, fax);
        prst.setInt(2, storeid);
        prst.executeUpdate();
    }

    public static void updatePassword(int password, int userid) throws SQLException, ClassNotFoundException {
        String update = "UPDATE users SET passw=? WHERE id=?";
        PreparedStatement prst = DBconnection.getInstance().getConnection().prepareStatement(update);
        prst.setInt(1, password);
        prst.setInt(2, userid);
        prst.executeUpdate();
    }
}

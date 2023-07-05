package com.example.kurs_v1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}

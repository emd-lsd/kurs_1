package com.example.kurs_v1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}

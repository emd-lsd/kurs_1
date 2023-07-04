package com.example.kurs_v1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Auth_Reg_model {
    //public Auth_Reg_model(){}
    public static int loginUser (String login, String password) throws SQLException, ClassNotFoundException {
        ResultSet res = null;

        int flag = 0;
        int hashPass = password.hashCode();
        User user = new User(login, hashPass);
        String select = "SELECT login, passw FROM users WHERE login =? AND passw =?";
        PreparedStatement preparedStatement = DBconnection.getInstance().getConnection().prepareStatement(select);
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, Integer.toString(user.getPassword()));
        res = preparedStatement.executeQuery();
        int i = 0;
        while(res.next()) i++;
        if(i>0){
            System.out.println("U're accessed!");
            flag = 1;
        }
        else {
            System.out.println("Check ur login or password again");
            flag = 0;
        }
        return flag;
    }
    public static void regUser (String username, String reg_pass) throws SQLException, ClassNotFoundException {
        int HashPassword = reg_pass.hashCode(); // Хеширование пароля при регистрации по формуле s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1] где s[i] номер символа в строке
        ResultSet res = null;

        String queue = "INSERT INTO users(login, passw, role_id) VALUES (?,?,3)";
        PreparedStatement preparedStatement = DBconnection.getInstance().getConnection().prepareStatement(queue);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, Integer.toString(HashPassword));
        preparedStatement.executeUpdate();
        String select = "SELECT id FROM users WHERE login =? AND passw =?";
        PreparedStatement prSt = DBconnection.getInstance().getConnection().prepareStatement(select);
        prSt.setString(1, username);
        prSt.setString(2, Integer.toString(HashPassword));
        res = prSt.executeQuery();
        int id =0;
        while (res.next()) {
            id = res.getInt("id");
        }

        String quest_stores = "INSERT INTO stores (address, fax_number, user_id) VALUES ('','',"+id+")";
        PreparedStatement prstStores = DBconnection.getInstance().getConnection().prepareStatement(quest_stores);
        prstStores.executeUpdate();
    }


}

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
        String select = "SELECT login, passw FROM users WHERE login =? AND passw =?";
        PreparedStatement preparedStatement = DBconnection.getInstance().getConnection().prepareStatement(select);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, Integer.toString(hashPass));
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

        String queue = "INSERT INTO users(login, passw, role_id) VALUES (?,?,3)";
        PreparedStatement preparedStatement = DBconnection.getInstance().getConnection().prepareStatement(queue);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, Integer.toString(HashPassword));
        preparedStatement.executeUpdate();
    }


}

package com.example.kurs_v1;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Auth_Reg_model {
    //public Auth_Reg_model(){}
    public void loginUser (String login, String password){

    }
    public static void regUser (String username, String reg_pass) throws SQLException, ClassNotFoundException {
        String queue = "INSERT INTO users(login, passw, role_id) VALUES (?,?,3)";
        PreparedStatement preparedStatement = DBconnection.getInstance().getConnection().prepareStatement(queue);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, reg_pass);
        preparedStatement.executeUpdate();
    }
}

package com.example.kurs_v1;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Auth_contr {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Authentification_button;

    @FXML
    private TextField Login_field_auth;

    @FXML
    private TextField Login_field_signup;

    @FXML
    private PasswordField Password_field_auth;

    @FXML
    private PasswordField Password_field_signup;

    @FXML
    private PasswordField Password_field_signup_rep;

    @FXML
    private Button Signup_button;

    @FXML
    void initialize() {
        Authentification_button.setOnAction(actionEvent -> {

            String query = "SELECT * FROM furniture_lines" ; // запрос :(((
            try {
                DBconnection db;
                ResultSet res = DBconnection.st.executeQuery(query);
                while(res.next()){
                    int id = res.getInt("id" );
                    String name = res.getString("name" );
                    //String short_name = result.getString("short_name" );
                    System.out.print("Actual furniture lines: " );
                    System.out.print("id = " + id);
                    System.out.println(", name = \" " + name + " \" " );
                    //System.out.println(" , short name = \" " + short_name + " \" . " );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });


    }

}

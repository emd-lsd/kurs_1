package com.example.kurs_v1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Auth_contr {
    Auth_Reg_model model;
    final String WPASS = "Пароли не совпадают";
    final String EMPTY = "";
    final String WINS = "Логин или пароль не заполнены";

    @FXML
    private Label Diff_passw;

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
    private Label Wrong_Auth;

    @FXML
    private Label Wrong_Reg;

    @FXML
    void initialize() {
        Authentification_button.setOnAction(actionEvent -> {
/*
            String query = "SELECT * FROM furniture_lines" ; // запрос :(((
            try {
                //DBconnection db;
                Statement st = DBconnection.getInstance().getConnection().createStatement();
                ResultSet res = st.executeQuery(query);
                while(res.next()){
                    int id = res.getInt("id" );
                    String name = res.getString("name" );
                    //String short_name = result.getString("short_name" );
                    System.out.print("Actual furniture lines: " );
                    System.out.print("id = " + id);
                    System.out.println(", name = \" " + name + " \" " );
                    //System.out.println(" , short name = \" " + short_name + " \" . " );
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } */
            String AuthLogin = Login_field_auth.getText().trim();
            String AuthPass = Password_field_auth.getText().trim();
            if(!AuthLogin.equals("")&&!AuthPass.equals("")){
                try {
                    //Auth_Reg_model.loginUser(AuthLogin, AuthPass);
                    if(Auth_Reg_model.loginUser(AuthLogin, AuthPass) == 1){
                        Authentification_button.getScene().getWindow().hide();


                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("shop-view.fxml"));
                        //fxmlLoader.setRoot(new AnchorPane());

                        try {
                            fxmlLoader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Parent root = fxmlLoader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                    if(Auth_Reg_model.loginUser(AuthLogin, AuthPass) == 0) Wrong_Auth.setText("Проверьте логин или пароль еще раз");
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else {
                Wrong_Auth.setText(WINS);
            }

        });

        Signup_button.setOnAction(actionEvent -> {
            String RegLogin = Login_field_signup.getText().trim();
            String RegPass = Password_field_signup.getText().trim();
            String RegPass_rep = Password_field_signup_rep.getText().trim();
            if (!RegLogin.equals("")&&!RegPass.equals("")){
                if(RegPass.equals(RegPass_rep)) {
                    try {
                        Auth_Reg_model.regUser(RegLogin, RegPass);
                        Wrong_Reg.setTextFill(Color.GREEN);
                        Wrong_Reg.setText("Вы успешно зарегестрировались");
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Diff_passw.setText(WPASS);
                }
            }
            else {
                Wrong_Reg.setText(WINS);

            }
        });



    }



}

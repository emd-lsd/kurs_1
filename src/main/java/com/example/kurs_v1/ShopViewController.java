package com.example.kurs_v1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ShopViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Change_adress;

    @FXML
    private Button Change_fax;

    @FXML
    private Button Change_pass;

    @FXML
    private Text WRONGPASS;

    @FXML
    private TextField act_pass_field;

    @FXML
    private Text address;

    @FXML
    private TextField address_field;

    @FXML
    private Text fax_num;

    @FXML
    private TextField fax_num_field;

    @FXML
    private Text login;

    @FXML
    private TextField new_pass_field;

    @FXML
    private Text user_id;

    @FXML
    private Text store_id;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        User user = new User();
        System.out.println(User.getUserid() + " initializer of shop "+Shop_role_model.getStoreid(user));
        fax_num.setText(Shop_role_model.getFaxNum(user));
        address.setText(Shop_role_model.getAddress(user));
        store_id.setText(String.valueOf(Shop_role_model.getStoreid(user)));

    }

}

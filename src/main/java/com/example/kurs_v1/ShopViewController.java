package com.example.kurs_v1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
    private TableView<Orders> OrderTable;

    @FXML
    private Text WRONGPASS;

    @FXML
    private TextField act_pass_field;

    @FXML
    private Text address;

    @FXML
    private TextField address_field;

    @FXML
    private TableColumn<Furniture, String> articleColumn;

    @FXML
    private Text fax_num;

    @FXML
    private TextField fax_num_field;

    @FXML
    private TableView<Furniture> furnitureTable;

    @FXML
    private TableColumn<Furniture, Integer> furnitureidColumn;

    @FXML
    private TableColumn<Orders, String> furniturenameColumn;

    @FXML
    private TableColumn<Furniture, String> lineColumn;

    @FXML
    private Text login;

    @FXML
    private TextField new_pass_field;

    @FXML
    private TableColumn<Orders, Date> orderdateColumn;

    @FXML
    private TableColumn<Orders, Integer> orderidColumn;

    @FXML
    private TableColumn<Orders, String> ordernumberColumn;

    @FXML
    private TableColumn<Furniture, Double> priceColumn;

    @FXML
    private TableColumn<Orders, Integer> quantityColumn;

    @FXML
    private Button showFurnitureButton;

    @FXML
    private Button showOrdersButton;

    @FXML
    private Text store_id;

    @FXML
    private TableColumn<Furniture, String> typeColumn;

    @FXML
    private Text user_id;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        User user = new User();
        System.out.println(User.getUserid() + " initializer of shop "+Shop_role_model.getStoreid(user));
        fax_num.setText(Shop_role_model.getFaxNum(user));
        address.setText(Shop_role_model.getAddress(user));
        store_id.setText(String.valueOf(Shop_role_model.getStoreid(user)));
        user_id.setText(String.valueOf(User.getUserid()));
        login.setText(User.getLogin());

        showFurnitureButton.setOnAction(actionEvent -> {
            ResultSet res = null;
            try {
                res = Shop_role_model.getResultFurniture(); // обращение через модель
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            ObservableList<Furniture> furnitures = FXCollections.observableArrayList();
            furnitureidColumn.setCellValueFactory(new PropertyValueFactory<Furniture, Integer>("id"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<Furniture, String>("type"));
            articleColumn.setCellValueFactory(new PropertyValueFactory<Furniture, String>("article"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<Furniture, Double>("price"));
            lineColumn.setCellValueFactory(new PropertyValueFactory<Furniture, String>("line"));
            try {
                while(res.next()) {

                    furnitures.add(new Furniture(res.getInt(1), res.getString(2),
                            res.getString(3), res.getDouble(4), res.getString(5)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            furnitureTable.setItems(furnitures);
        });

        showOrdersButton.setOnAction(actionEvent -> {
            ResultSet res = null;
            try {
                res = Shop_role_model.getResultOrders(Shop_role_model.getStoreid(user)); // обращение через модель
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            ObservableList<Orders> orders = FXCollections.observableArrayList();
            orderidColumn.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("id"));
            ordernumberColumn.setCellValueFactory(new PropertyValueFactory<Orders, String>("order_number"));
            orderdateColumn.setCellValueFactory(new PropertyValueFactory<Orders, Date>("order_date"));
            furniturenameColumn.setCellValueFactory(new PropertyValueFactory<Orders, String>("furniture_name"));
            quantityColumn.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("quantity"));
            try {
                while(res.next()) {

                    orders.add(new Orders(res.getInt(1), res.getString(2),
                            res.getDate(3), res.getString(4), res.getInt(5)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            OrderTable.setItems(orders);
        });

    }

}

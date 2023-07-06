package com.example.kurs_v1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class AdminViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Change_pass;

    @FXML
    private Button ChangeuserpasButton;

    @FXML
    private Button DeleteusernstoreButton;

    @FXML
    private TableView<Orders> OrderTable;

    @FXML
    private TableView<Stores> StoresTable;

    @FXML
    private Text WRONGPASS;

    @FXML
    private TextField act_pass_field;

    @FXML
    private Button addnewWorkerButton;

    @FXML
    private TableColumn<Furniture, String> articleColumn;

    @FXML
    private TableColumn<Components, String> codeComponentColumn;

    @FXML
    private TableView<Components> componentsTable;

    @FXML
    private TableColumn<Components, String> fnameCOmponentColumn;

    @FXML
    private TableView<Furniture> furnitureTable;

    @FXML
    private TableColumn<Furniture, Integer> furnitureidColumn;

    @FXML
    private TableColumn<Orders, String> furniturenameColumn;

    @FXML
    private TableColumn<Components, Integer> idComponentColumn;

    @FXML
    private TableColumn<Furniture, String> lineColumn;

    @FXML
    private Text login;

    @FXML
    private TableColumn<Components, String> nameComponentColumn;

    @FXML
    private TextField new_pass_field;

    @FXML
    private TextField newloginWorkerField;

    @FXML
    private TextField newpasUserEditField;

    @FXML
    private TextField newpasswWorkerField;

    @FXML
    private TableColumn<Orders, Date> orderdateColumn;

    @FXML
    private TableColumn<Orders, Integer> orderidColumn;

    @FXML
    private TableColumn<Orders, String> ordernumberColumn;

    @FXML
    private TableColumn<Furniture, Double> priceColumn;

    @FXML
    private TableColumn<Components, Double> priceComponentColumn;

    @FXML
    private TableColumn<Orders, Integer> quantityColumn;

    @FXML
    private TableColumn<Components, Integer> quantityComponentColumn;

    @FXML
    private Button showComponentsButton;

    @FXML
    private Button showFurnitureButton;

    @FXML
    private Button showOrdersButton;

    @FXML
    private Button showStoresButton;

    @FXML
    private Button showUsersButton;

    @FXML
    private TableColumn<Stores, String> storesaddressColumn;

    @FXML
    private TableColumn<Stores, Integer> storesfaxnColumn;

    @FXML
    private TableColumn<Stores, Integer> storesidColumn;

    @FXML
    private TableColumn<Stores, Integer> storesuseridColumn;

    @FXML
    private TableColumn<Furniture, String> typeColumn;

    @FXML
    private Text user_id;

    @FXML
    private TextField useridDeleteField;

    @FXML
    private TextField useridEditField;

    @FXML
    private TableView<Users> usersTable;

    @FXML
    private TableColumn<Users, Integer> usersidColumn;

    @FXML
    private TableColumn<Users, String> usersloginColumn;

    @FXML
    private TableColumn<Users, Integer> userspasswColumn;

    @FXML
    private TableColumn<Users, Integer> usersroleidColumn;

    @FXML
    void initialize() {
        User user = new User();
        user_id.setText(String.valueOf(User.getUserid()));
        login.setText(User.getLogin());

        Change_pass.setOnAction(actionEvent -> {
            WRONGPASS.setVisible(false);
            String actpass = act_pass_field.getText().trim();
            String newpass = new_pass_field.getText().trim();
            if(!actpass.equals("")&&!newpass.equals("")){
                if(actpass.hashCode()==User.getPassword()){
                    User.setPassword(newpass.hashCode());
                    try {
                        Worker_role_model.updatePassword(User.getPassword(), User.getUserid());
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Новый пароль");
                    alert.setHeaderText("Вы успешно сменили пароль");
                    alert.setContentText("");
                    alert.showAndWait();
                    act_pass_field.setText("");
                    new_pass_field.setText("");

                }
                else {
                    WRONGPASS.setVisible(true);
                }
            }
        });

        showFurnitureButton.setOnAction(actionEvent -> {
            ResultSet res = null;
            try {
                res = Admin_role_model.getResultFurniture(); // обращение через модель
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

        showComponentsButton.setOnAction(actionEvent -> {
            ResultSet res = null;
            try {
                res = Admin_role_model.getResultComponents(); // обращение через модель
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            ObservableList<Components> components = FXCollections.observableArrayList();
            idComponentColumn.setCellValueFactory(new PropertyValueFactory<Components, Integer>("id"));
            fnameCOmponentColumn.setCellValueFactory(new PropertyValueFactory<Components, String>("furniture_name"));
            nameComponentColumn.setCellValueFactory(new PropertyValueFactory<Components, String>("component"));
            codeComponentColumn.setCellValueFactory(new PropertyValueFactory<Components, String>("code"));
            priceComponentColumn.setCellValueFactory(new PropertyValueFactory<Components, Double>("price"));
            quantityComponentColumn.setCellValueFactory(new PropertyValueFactory<Components, Integer>("quantity"));
            try {
                while(res.next()) {

                    components.add(new Components(res.getInt(1), res.getString(2),
                            res.getString(3), res.getString(4), res.getDouble(5), res.getInt(6)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            componentsTable.setItems(components);
        });

        showOrdersButton.setOnAction(actionEvent -> {
            ResultSet res = null;
            try {
                res = Admin_role_model.getResultOrders(); // обращение через модель
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

        showStoresButton.setOnAction(actionEvent -> {
            ResultSet res = null;
            try {
                res = Admin_role_model.getResultStores(); // обращение через модель
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            ObservableList<Stores> stores = FXCollections.observableArrayList();
            storesidColumn.setCellValueFactory(new PropertyValueFactory<Stores, Integer>("id"));
            storesaddressColumn.setCellValueFactory(new PropertyValueFactory<Stores, String>("address"));
            storesfaxnColumn.setCellValueFactory(new PropertyValueFactory<Stores, Integer>("fax_number"));
            storesuseridColumn.setCellValueFactory(new PropertyValueFactory<Stores, Integer>("user_id"));

            try {
                while(res.next()) {

                    stores.add(new Stores(res.getInt(1), res.getString(2),
                            res.getInt(3), res.getInt(4)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            StoresTable.setItems(stores);
        });

        showUsersButton.setOnAction(actionEvent -> {
            ResultSet res = null;
            try {
                res = Admin_role_model.getResultUsers(); // обращение через модель
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            ObservableList<Users> users = FXCollections.observableArrayList();
            usersidColumn.setCellValueFactory(new PropertyValueFactory<Users, Integer>("id"));
            usersloginColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("login"));
            userspasswColumn.setCellValueFactory(new PropertyValueFactory<Users, Integer>("password"));
            usersroleidColumn.setCellValueFactory(new PropertyValueFactory<Users, Integer>("role_id"));

            try {
                while(res.next()) {

                    users.add(new Users(res.getInt(1), res.getString(2),
                            res.getInt(3), res.getInt(4)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            usersTable.setItems(users);
        });

        ChangeuserpasButton.setOnAction(actionEvent -> {
            String id = useridEditField.getText().trim();
            String newpas = newpasUserEditField.getText().trim();
            if(!id.equals("")&&!newpas.equals("")){
                int hasp = newpas.hashCode();
                try {
                    Admin_role_model.updatePassUser(id, String.valueOf(hasp));
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Смена пароля");
                alert.setHeaderText("Вы успешно сменили пароль пользователю");
                alert.setContentText("");
                alert.showAndWait();
            }
        });

        addnewWorkerButton.setOnAction(actionEvent -> {
            String login = newloginWorkerField.getText().trim();
            String passw = newpasswWorkerField.getText().trim();
            if(!login.equals("")&&!passw.equals("")){
                int hasp = passw.hashCode();
                try {
                    Admin_role_model.addnewWorker(login, String.valueOf(hasp));
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Новый работник");
                alert.setHeaderText("Вы успешно создали нового работника "+login);
                alert.setContentText("");
                alert.showAndWait();
            }
        });

        DeleteusernstoreButton.setOnAction(actionEvent -> {
            String userid = useridDeleteField.getText().trim();
            if(!userid.equals("")){
                try {
                    Admin_role_model.deleteUser(Integer.parseInt(userid));
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Удаление");
                alert.setHeaderText("Вы успешно удалили работника с id: "+userid);
                alert.setContentText("");
                alert.showAndWait();
            }
        });

    }

}

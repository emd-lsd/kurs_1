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
import java.util.ResourceBundle;

public class WorkerViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Change_pass;

    @FXML
    private Button SetComponentButton;

    @FXML
    private Text WRONGPASS;

    @FXML
    private TextField act_pass_field;

    @FXML
    private TextField addedpriceFurnitureFIeld;

    @FXML
    private Button addnewComponentButton;

    @FXML
    private Button addnewComponentButton1;

    @FXML
    private Button addnewFurnitureButton;

    @FXML
    private TableColumn<Furniture, String> articleColumn;

    @FXML
    private TextField articleFurnitureFIeld;

    @FXML
    private TableColumn<Components, String> codeComponentColumn;

    @FXML
    private TextField codeComponentFIeld;

    @FXML
    private TextField compidSetCompField;

    @FXML
    private TableView<Components> componentsTable;

    @FXML
    private TableColumn<Components, String> fnameCOmponentColumn;

    @FXML
    private TableView<Furniture> furnitureTable;

    @FXML
    private TableColumn<Furniture, Integer> furnitureidColumn;

    @FXML
    private TableColumn<Components, Integer> idComponentColumn;

    @FXML
    private TextField idfurSetCompField;

    @FXML
    private TableColumn<Furniture, String> lineColumn;

    @FXML
    private TextField lineFurnitureFIeld;

    @FXML
    private Text login;

    @FXML
    private TableColumn<Components, String> nameComponentColumn;

    @FXML
    private TextField nameLineFurnitureFIeld;

    @FXML
    private TextField new_pass_field;

    @FXML
    private TableColumn<Furniture, Double> priceColumn;

    @FXML
    private TableColumn<Components, Double> priceComponentColumn;

    @FXML
    private TextField priceComponentField;

    @FXML
    private TableColumn<Components, Integer> quantityComponentColumn;

    @FXML
    private TextField quantitySetCompField;

    @FXML
    private Button showFurnitureButton;

    @FXML
    private TableColumn<Furniture, String> typeColumn;

    @FXML
    private TextField typeComponentFIeld;

    @FXML
    private TextField typeFurnitureFIeld;

    @FXML
    private Text user_id;

    @FXML
    private Button showComponentsButton;

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
                res = Worker_role_model.getResultFurniture(); // обращение через модель
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
                res = Worker_role_model.getResultComponents(); // обращение через модель
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
    }

}

package com.example.kurs_v1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Worker_role_model {

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
    private TableColumn<?, ?> articleColumn;

    @FXML
    private TextField articleFurnitureFIeld;

    @FXML
    private TableColumn<?, ?> codeComponentColumn;

    @FXML
    private TextField codeComponentFIeld;

    @FXML
    private TextField compidSetCompField;

    @FXML
    private TableView<?> componentsTable;

    @FXML
    private TableColumn<?, ?> fnameCOmponentColumn;

    @FXML
    private TableView<?> furnitureTable;

    @FXML
    private TableColumn<?, ?> furnitureidColumn;

    @FXML
    private TableColumn<?, ?> idComponentColumn;

    @FXML
    private TextField idfurSetCompField;

    @FXML
    private TableColumn<?, ?> lineColumn;

    @FXML
    private TextField lineFurnitureFIeld;

    @FXML
    private Text login;

    @FXML
    private TableColumn<?, ?> nameComponentColumn;

    @FXML
    private TextField nameLineFurnitureFIeld;

    @FXML
    private TextField new_pass_field;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private TableColumn<?, ?> priceComponentColumn;

    @FXML
    private TextField priceComponentField;

    @FXML
    private TableColumn<?, ?> quantityComponentColumn;

    @FXML
    private TextField quantitySetCompField;

    @FXML
    private Button showFurnitureButton;

    @FXML
    private TableColumn<?, ?> typeColumn;

    @FXML
    private TextField typeComponentFIeld;

    @FXML
    private TextField typeFurnitureFIeld;

    @FXML
    private Text user_id;

    @FXML
    void initialize() {


    }

}

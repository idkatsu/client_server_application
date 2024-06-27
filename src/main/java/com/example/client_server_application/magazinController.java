package com.example.client_server_application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class magazinController {
    @FXML
    private Button buttonMagazin1;

    @FXML
    private Button buttonMagazin2;

    @FXML
    private Button buttonMagazin3;

    @FXML
    private TextField str1_1;

    @FXML
    private TextField str1_2;

    @FXML
    private TextField str2_1;

    @FXML
    private TextField str2_2;

    @FXML
    private TextField str3_1;

    @FXML
    private TextField str3_2;
    private Stage stage;
    private Scene scene;

    @FXML
    void switchToScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("app.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void goDate(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("arenda.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goSpisok(ActionEvent event) throws SQLException, ClassNotFoundException {
        DateBaseHandler dateBaseHandler = new DateBaseHandler();
        List<Shop> shops = dateBaseHandler.getMagazin();

        if (!shops.isEmpty()) {
            str1_1.setText(shops.get(0).getName());
            str1_2.setText(shops.get(0).getAdress());
        }

        if (!shops.isEmpty()) {
            str2_1.setText(shops.get(1).getName());
            str2_2.setText(shops.get(1).getAdress());
        }

        if (!shops.isEmpty()) {
            str3_1.setText(shops.get(2).getName());
            str3_2.setText(shops.get(2).getAdress());
        }

    }

    @FXML
    public void handleButtonMagazin1(ActionEvent event) throws IOException {
        SingletonShop.getInstance().setShopId(1);
        goDate(event);
    }

    @FXML
    public void handleButtonMagazin2(ActionEvent event) throws IOException {
        SingletonShop.getInstance().setShopId(2);
        goDate(event);
    }

    @FXML
    public void handleButtonMagazin3(ActionEvent event) throws IOException {
        SingletonShop.getInstance().setShopId(3);
        goDate(event);
    }

    @FXML
    void initialize() {

    }

}


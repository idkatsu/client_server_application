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
    private Button buttonArenda1;

    @FXML
    private Button buttonArenda2;

    @FXML
    private Button buttonArenda3;

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
        Shop shop = null;
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

        if (event.getSource() == buttonArenda1) {
            shop.setId_magazin("1");
        }

        if (event.getSource() == buttonArenda2) {
            shop.setId_magazin("2");
        }

        if (event.getSource() == buttonArenda3) {
            shop.setId_magazin("3");
        }
    }

    @FXML
    void initialize() {

    }

}


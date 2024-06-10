package com.example.client_server_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class HelloController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonRegister;

    @FXML
    private Button buttonEnter;
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private Parent root;

    public void switchToSceneHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("app.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSceneSignUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSceneGL(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void initialize() {

    }
}
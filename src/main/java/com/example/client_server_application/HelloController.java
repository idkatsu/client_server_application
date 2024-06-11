package com.example.client_server_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class HelloController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonRegister;
    @FXML
    private Button buttonBack;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;
    @FXML
    private TextField signUpLastName;

    @FXML
    private TextField signUpLogin;

    @FXML
    private TextField signUpName;

    @FXML
    private TextField signUpPassword;

    @FXML
    private Button buttonEnter;

    @FXML
    private Button buttonEnter2;
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private Parent root;


    public void switchToSceneSignUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSceneGL(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSceneHome(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        String login = login_field.getText().trim();
        String password = password_field.getText().trim();

        if (login.isEmpty() || password.isEmpty()) {
            System.out.println("Error");
            return;
        }

        if (loginUser(login, password)) {
            Parent root = FXMLLoader.load(getClass().getResource("app.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }


    @FXML
    void initialize() {

    }

    private boolean loginUser(String loginText, String passwordText) throws SQLException, ClassNotFoundException {
        DateBaseHandler dateBaseHandler = new DateBaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(passwordText);
        ResultSet result = dateBaseHandler.getUser(user);

        int count = 0;
        while (result.next()) count++;
        if (count >= 1) {
            System.out.println("Вы зашли!");
            return true;
        } else {
            Shake userLoginAnim = new Shake(login_field);
            Shake userPassAnim = new Shake(password_field);
            userLoginAnim.playAnim();
            userPassAnim.playAnim();
            return false;
        }
    }
}
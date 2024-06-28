package com.example.client_server_application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private Button signUpButton;

    @FXML
    private TextField signUpLastName;

    @FXML
    private TextField signUpLogin;

    @FXML
    private TextField signUpName;

    @FXML
    private TextField signUpPassport;
    @FXML
    private TextField signUpAddress;

    @FXML
    private TextField signUpPassword;
    private Stage stage;
    private Scene scene;

    public void switchToSceneRegistered(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("registered.fxml"));
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

    public void switchToSceneGoHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {
        signUpButton.setOnAction(event -> {
            try {
                if (signUpLastName.getText().isEmpty() || signUpLogin.getText().isEmpty() ||
                        signUpName.getText().isEmpty() || signUpPassword.getText().isEmpty() || signUpPassport.getText().isEmpty()
                        || signUpAddress.getText().isEmpty()) {
                    Shake userLoginAnim = new Shake(signUpLogin);
                    Shake userPassAnim = new Shake(signUpPassword);
                    Shake userNameAnim = new Shake(signUpName);
                    Shake userLastNameAnim = new Shake(signUpLastName);
                    Shake userPassportAnim = new Shake(signUpPassport);
                    Shake userAddressAnim = new Shake(signUpAddress);

                    userLoginAnim.playAnim();
                    userPassAnim.playAnim();
                    userNameAnim.playAnim();
                    userLastNameAnim.playAnim();
                    userPassportAnim.playAnim();
                    userAddressAnim.playAnim();
                    return;
                }
                signUpNewUser();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Вы зарегистрировались!");
        });
    }

    private void signUpNewUser() throws SQLException, ClassNotFoundException {
        DateBaseHandler dateBaseHandler = new DateBaseHandler();

        String name = signUpName.getText() + " " + signUpLastName.getText();
        String passport = signUpPassport.getText();
        String address = signUpAddress.getText();

        String userName = signUpLogin.getText();
        String password = signUpPassword.getText();

        User user = new User(userName, password);
        Client client = new Client(name, passport, address);

        dateBaseHandler.addClients(client);
        dateBaseHandler.fetchClientId(name);
        dateBaseHandler.signUpUser(user, client);
    }
}


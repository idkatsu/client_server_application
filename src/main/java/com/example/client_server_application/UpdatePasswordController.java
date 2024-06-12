package com.example.client_server_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdatePasswordController {
    private Stage stage;
    private Scene scene;
    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField oldPassword;
    @FXML
    private TextField login;

    public void switchToSceneGlavnaya(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSceneGlavnaya1(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        test();
        String login = this.login.getText().trim();
        String oldPass = oldPassword.getText().trim();
        String newPass = newPassword.getText().trim();

        if (oldPass.isEmpty() || newPass.isEmpty() || login.isEmpty()) {
            System.out.println("Error");
            return;
        }

        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void test() throws SQLException, ClassNotFoundException {
        DateBaseHandler dateBaseHandler = new DateBaseHandler();
        User user = new User();
        user.setUserName(login.getText());
        user.setPassword(oldPassword.getText());
        ResultSet result = dateBaseHandler.getUser(user);

        int count = 0;
        while (result.next()) count++;
        if (count >= 1) {
            dateBaseHandler.changePasswordByUsername(login.getText(), newPassword.getText());
        } else {
            Shake userLoginAnim = new Shake(login);
            Shake userPassAnim = new Shake(oldPassword);
            Shake userNewPassAnim = new Shake(newPassword);
            userLoginAnim.playAnim();
            userPassAnim.playAnim();
            userNewPassAnim.playAnim();
        }
    }
}

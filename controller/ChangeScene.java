package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeScene {
    // Không nhận ActionEvent nữa, mà nhận trực tiếp một UI Node (ví dụ: cái Nút, cái TextField,...)
    public static void LoginToSignUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(ChangeScene.class.getResource("register.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sign Up");
        stage.show();
    }

    public static void SignUpToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(ChangeScene.class.getResource("loginScene.fxml"));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }
    public static void LoginToDashBoard(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(ChangeScene.class.getResource("/controller/MainDashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("DashBoard");
        stage.show();
    }
}

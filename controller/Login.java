package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class Login {
    @FXML
    private TextField textUsername;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button signInButton;
    @FXML
    private Button signUpButton;
    @FXML
    private ImageView loginImage;

    @FXML
    public void initialize() {
        // Nút Đăng ký (Sign Up) sẽ chuyển sang màn hình đăng ký
        signUpButton.setOnAction(event -> {
            try {
                ChangeScene.LoginToSignUp(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        signInButton.setOnAction(event -> handleLogin(event));
    }

    // METHOD
    @FXML
    public void handleLogin(ActionEvent event) {
        String user =  textUsername.getText();
        String pass = passwordField.getText();
        if(authenticate(user,pass)){
            try{
            ChangeScene.LoginToDashBoard(event);}
            catch (Exception e){e.printStackTrace();}
            
        } else {
            showAlert();
        }
    }
    //
    private boolean authenticate(String username, String password){
        LoginDB.ConnectToDatabase();
        if(!LoginDB.isPassword(username,password)){
            return false;
        }
        return true;
    }

    private void showAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Lỗi đăng nhập");
        alert.setHeaderText(null);
        alert.setContentText("Tài khoản hoặc mật khẩu không chính xác!");
        alert.showAndWait();
    }
}

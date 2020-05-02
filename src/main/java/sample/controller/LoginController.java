package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXPasswordField loginPassword;

    @FXML
    private JFXTextField loginUsername;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXButton loginSignupButton;

    @FXML
    void initialize() {
        loginButton.setOnAction(event -> {
            System.out.println("loginClicked");
        });


    }
}

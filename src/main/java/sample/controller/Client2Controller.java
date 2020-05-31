package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public class Client2Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton clientPs1Comenzi;

    @FXML
    private JFXButton clientPs1Cos;

    @FXML
    void initialize() {
        assert clientPs1Comenzi != null : "fx:id=\"clientPs1Comenzi\" was not injected: check your FXML file 'clientps1.fxml'.";
        assert clientPs1Cos != null : "fx:id=\"clientPs1Cos\" was not injected: check your FXML file 'clientps1.fxml'.";

    }
}

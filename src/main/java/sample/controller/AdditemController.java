package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public class AdditemController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton additemComenziButton;

    @FXML
    private JFXButton additemCosButton;

    @FXML
    void initialize() {
        assert additemComenziButton != null : "fx:id=\"additemComenziButton\" was not injected: check your FXML file 'additem.fxml'.";
        assert additemCosButton != null : "fx:id=\"additemCosButton\" was not injected: check your FXML file 'additem.fxml'.";

    }

}

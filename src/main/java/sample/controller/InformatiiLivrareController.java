package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import sample.services.UserDataService;

import java.net.URL;
import java.util.ResourceBundle;

public class InformatiiLivrareController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton backButton;
    @FXML
    private void goBack(javafx.event.ActionEvent ev) throws Exception{
        UserDataService.muta(ev,"src/main/resources/ClientCosCumparaturi.fxml");

    }
    @FXML
    void initialize() {
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'informatiiLivrare.fxml'.";

    }
}

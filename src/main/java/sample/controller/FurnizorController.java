package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class FurnizorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton furnizorStoc;

    @FXML
    private JFXButton furnizorStatus;


    @FXML
    private void mutaEditareProduse(javafx.event.ActionEvent ev) throws Exception{
        URL url=new File("src/main/resources/furnizorEditareProduse.fxml").toURI().toURL();
        Parent home= FXMLLoader.load(url);
        Scene s=new Scene(home);
        Stage window=(Stage)((Node)ev.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();

    }
    @FXML
    void initialize() {
        assert furnizorStoc != null : "fx:id=\"furnizorStoc\" was not injected: check your FXML file 'furnizor.fxml'.";
        assert furnizorStatus != null : "fx:id=\"furnizorStatus\" was not injected: check your FXML file 'furnizor.fxml'.";

    }
}

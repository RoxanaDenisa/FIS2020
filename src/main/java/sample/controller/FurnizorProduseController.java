package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class FurnizorProduseController {

    ObservableList list= FXCollections.observableArrayList();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton edPrStoc;

    @FXML
    private JFXButton edPrStatus;

    @FXML
    private TilePane furnizorTP;
    @FXML
    void initialize() {

        Image img=new Image("/assets/add.png");
        ImageView iv=new ImageView(img);
        iv.setFitHeight(70);
        iv.setFitWidth(70);
        Hyperlink link=new Hyperlink("   ",iv);
        link.setOnAction(e->{

            try {
                URL url=new File("src/main/resources/clientps1.fxml").toURI().toURL();
                Parent home= null;
                home = FXMLLoader.load(url);
                Scene s=new Scene(home);
                Stage window=(Stage)((Node)e.getSource()).getScene().getWindow();
                window.setScene(s);
                window.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        VBox vb =new VBox();
        vb.getChildren().add(link);
        furnizorTP.getChildren().add(vb);
    }
    }

package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sample.services.UserDataService;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientCosCumparaturiController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton clientComanda;

    @FXML
    private JFXButton clientCos;

    @FXML
    private VBox prodBox;
    @FXML
    private void goCosCumparaturi(javafx.event.ActionEvent ev) throws Exception {
        UserDataService.muta(ev,"src/main/resources/clientCosCumparaturi.fxml");
    }
    @FXML
    private void goComenzi(javafx.event.ActionEvent ev) throws Exception {
       UserDataService.muta(ev,"src/main/resources/ComenziClient.fxml");
    }
    @FXML
    private void goback(javafx.event.ActionEvent ev) throws Exception {
       UserDataService.muta(ev,"src/main/resources/additem.fxml");
    }
    @FXML
    void plasareComanda(javafx.event.ActionEvent ev) throws Exception {
        JSONArray jrr=new JSONArray();
        jrr=UserDataService.OpenFile("cosCumparaturi.json");
        if(jrr.size()!=0){
        URL url = new File("src/main/resources/formularComanda.fxml").toURI().toURL();
        Parent home = FXMLLoader.load(url);
        Scene s = new Scene(home);
        Stage window = (Stage) ((Node) ev.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();}
        else
            JOptionPane.showMessageDialog(null,"Nu ati adaugat produse in cos");
    }
    @FXML
    private void goLivrare(javafx.event.ActionEvent ev) throws Exception{
        UserDataService.muta(ev,"src/main/resources/informatiiLivrare.fxml");

    }
    private Integer total=0;
    @FXML
    void initialize() throws Exception {

        JSONArray jrr=new JSONArray();
        JSONParser jp=new JSONParser();
        jrr=UserDataService.OpenFile("cosCumparaturi.json");
        int size=jrr.size();
        for (int i=0; i<size; i++) {
            HBox h = new HBox();
            JSONObject x=(JSONObject)jrr.get(i);
            String p = (String) x.get("Imagine");
            String n=(String) x.get("Nume");
            String pr=(String) x.get("Pret");
            Integer pret=Integer.parseInt(pr);
            total=total+pret;
            WritableImage wi = null;
            wi=UserDataService.deodarePoza(p);
                ImageView imgv = new ImageView(wi);
                imgv.setFitHeight(100);
                imgv.setFitWidth(100);
                h.getChildren().add(imgv);
                Text t=new Text("Nume produs: "+n+" \n");
                TextFlow tf=new TextFlow(t);
                tf.setTextAlignment(TextAlignment.CENTER);
                h.getChildren().add(tf);
                Text t2=new Text(", pret: "+pr+" lei \n");
                TextFlow tf2=new TextFlow(t2);
                tf2.setTextAlignment(TextAlignment.CENTER);
                h.getChildren().add(tf2);
            prodBox.getChildren().add(h);
        }
        if (total!=0)
        total=total+20;
          HBox tot=new HBox();
          Text t=new Text("Cost total: "+total.toString()+"lei");
          t.setFont(new Font("Times New Roman",30));
          t.setTextAlignment(TextAlignment.RIGHT);
        tot.getChildren().add(t);
        prodBox.getChildren().add(tot);
    }
}

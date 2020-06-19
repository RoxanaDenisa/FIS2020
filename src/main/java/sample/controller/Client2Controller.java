package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sample.services.UserDataService;

import javax.swing.*;
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
    private TilePane client2TP;
    @FXML
    private void goComenzi(javafx.event.ActionEvent ev) throws Exception {
        UserDataService.muta(ev,"src/main/resources/ComenziClient.fxml");
    }
    @FXML
    private void goBack(javafx.event.ActionEvent ev) throws Exception{
        UserDataService.muta(ev,"src/main/resources/additem.fxml");

    }
    @FXML
    void goCosCumparaturi(javafx.event.ActionEvent ev) throws Exception {
       UserDataService.muta(ev,"src/main/resources/clientCosCumparaturi.fxml");
    }
    @FXML
    void initialize() throws Exception {
        JSONArray jrr=new JSONArray();
        jrr=UserDataService.OpenFile("ProductData.json");
        int size=jrr.size();
        for(int i=0;i<size;i++){
            VBox v=new VBox();
            JSONObject j=(JSONObject) jrr.get(i);
            String n=(String)j.get("Firma");
            if(ClientController.firma.equals(n))
            {
                String p = (String) j.get("Imagine");
                String np=(String) j.get("Nume produs");
                String cant=(String) j.get("Cantitate");
                String pret=(String) j.get("Pret");
                WritableImage wi = null;
               wi=UserDataService.deodarePoza(p);
                    ImageView imgv = new ImageView(wi);
                    imgv.setFitHeight(100);
                    imgv.setFitWidth(100);
                    v.getChildren().add(imgv);
                    Text t=new Text("Denumire: "+np+"\nCantitate: "+cant+" g\n Pret: "+pret+" lei");
                    v.getChildren().add(t);
                    Button c=new Button("Adauga in cos");
                    c.setOnAction(e->{
                        JSONObject obj=new JSONObject();
                        JSONArray jsn=new JSONArray();
                        JSONParser jsp=new JSONParser();
                        try {
                            jsn=UserDataService.OpenFile("cosCumparaturi.json");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        obj.put("Nume",np );
                        obj.put("Pret",pret);
                        obj.put("Imagine",p);
                        jsn.add(obj);
                        UserDataService.writeFile(jsn,"cosCumparaturi.json");
                        JOptionPane.showMessageDialog(null, "Produs adaugat in cosul de cumparaturi");
                    });
                    v.getChildren().add(c);
                }
                client2TP.getChildren().add(v);
            }
            }
    }

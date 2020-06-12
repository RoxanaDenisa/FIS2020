package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sample.Elemente;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.controller.LoginController.retinNume;

public class ClientComenziController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton ComenziComenzi;

    @FXML
    private JFXButton CosComenzi;

    @FXML
    private TableView<Elemente> TableComenzi;

    @FXML
    private TableColumn<Elemente, String> TableDetaliiComenzi;

    @FXML
    private TableColumn<Elemente, String> TableStatusComenzi;

    @FXML
    private JFXButton Back;
    @FXML
    private void reload(javafx.event.ActionEvent ev){
        try {
            URL url=new File("src/main/resources/ComenziClient.fxml").toURI().toURL();
            Parent home= null;
            home = FXMLLoader.load(url);
            Scene s=new Scene(home);
            Stage window=(Stage)((Node)ev.getSource()).getScene().getWindow();
            window.setScene(s);
            window.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    private void goCosCumparaturi(javafx.event.ActionEvent ev){
        try {
            URL url=new File("src/main/resources/clientCosCumparaturi.fxml").toURI().toURL();
            Parent home= null;
            home = FXMLLoader.load(url);
            Scene s=new Scene(home);
            Stage window=(Stage)((Node)ev.getSource()).getScene().getWindow();
            window.setScene(s);
            window.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    private void goback(javafx.event.ActionEvent ev){
        try {
            URL url=new File("src/main/resources/additem.fxml").toURI().toURL();
            Parent home= null;
            home = FXMLLoader.load(url);
            Scene s=new Scene(home);
            Stage window=(Stage)((Node)ev.getSource()).getScene().getWindow();
            window.setScene(s);
            window.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    void initCol() {
        TableDetaliiComenzi.setCellValueFactory(new PropertyValueFactory<Elemente, String>("detalii"));
        TableStatusComenzi.setCellValueFactory(new PropertyValueFactory<Elemente, String>("status"));

    }
    @FXML
    void initialize() throws IOException {
        initCol();
        ObservableList<Elemente> data = FXCollections.observableArrayList();
        JSONArray jrr = new JSONArray();
        JSONParser jp = new JSONParser();
        try {
            FileReader file = new FileReader("Comenzi.json");
            jrr = (JSONArray) jp.parse(file);

        } catch (Exception e) {

        }
        int size = jrr.size();
        for (int i = 0; i < size; i++) {
            JSONObject x = (JSONObject) jrr.get(i);
            String n = (String) x.get("Utilizator");
            String aux1=null;
            String aux2=null;
            if (n.equals(retinNume)) {
                String nc=(String)x.get("Nume Complet");
                String ad=(String)x.get("Adresa");
                String cp=(String)x.get("Cod postal");
                String nt=(String)x.get("Telefon");
                String pr=(String)x.get("Produse");
                aux1=nc+",Adresa: "+ad+", Cod Postal:"+cp+",Telefon:"+nt+"Produse:"+pr;
                String s=(String)x.get("Status");
                aux2=s;
                data.add(new Elemente(aux1,aux2));
            }

        }
        TableComenzi.setItems(data);
    }
}

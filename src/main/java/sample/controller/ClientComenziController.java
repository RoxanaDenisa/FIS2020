package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sample.Elemente;
import sample.services.UserDataService;

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
    private void reload(javafx.event.ActionEvent ev) throws Exception {
        UserDataService.muta(ev,"src/main/resources/ComenziClient.fxml");
    }
    @FXML
    private void goCosCumparaturi(javafx.event.ActionEvent ev) throws Exception {
        UserDataService.muta(ev,"src/main/resources/clientCosCumparaturi.fxml");
    }
    @FXML
    private void goback(javafx.event.ActionEvent ev) throws Exception {
        UserDataService.muta(ev,"src/main/resources/additem.fxml");
    }
    @FXML
    void initCol() {
        TableDetaliiComenzi.setCellValueFactory(new PropertyValueFactory<Elemente, String>("detalii"));
        TableStatusComenzi.setCellValueFactory(new PropertyValueFactory<Elemente, String>("status"));

    }
    @FXML
    void initialize() throws Exception {
        initCol();
        ObservableList<Elemente> data = FXCollections.observableArrayList();
        JSONArray jrr = new JSONArray();
        jrr=UserDataService.OpenFile("Comenzi.json");
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

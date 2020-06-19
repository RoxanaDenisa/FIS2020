package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sample.services.UserDataService;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.controller.ClientController.firma;
import static sample.controller.LoginController.retinNume;

public class FormularComandaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField npformular;

    @FXML
    private JFXTextField nrtelformular;

    @FXML
    private JFXTextField codformular;

    @FXML
    private JFXTextField adresaformular;

    @FXML
    private JFXButton finalizareformular;
    @FXML
    private void goCosCumparaturi(javafx.event.ActionEvent ev) throws Exception {
        UserDataService.muta(ev,"src/main/resources/clientCosCumparaturi.fxml");
    }
    @FXML
    void finalizareComanda(javafx.event.ActionEvent ev) throws Exception {
        JSONObject obj=new JSONObject();
        JSONArray jrr=new JSONArray();
        jrr=UserDataService.OpenFile("Comenzi.json");
        obj.put("Nume Complet", npformular.getText());
        obj.put("Telefon", nrtelformular.getText());
        obj.put("Adresa", adresaformular.getText());
        obj.put("Cod postal", codformular.getText());
        JSONArray jrr2=new JSONArray();
        jrr2=UserDataService.OpenFile("cosCumparaturi.json");
        String p= "";
        int size2=jrr2.size();
        for (int i=0; i<size2; i++) {
            JSONObject x = (JSONObject) jrr2.get(i);
            p = p + (String) x.get("Nume");
        }
        obj.put("Produse", p);
        obj.put("Firma", firma);
        obj.put("Utilizator", retinNume);
        obj.put("Status"," ");
        jrr.add(obj);
        UserDataService.writeFile(jrr,"Comenzi.json");
        JOptionPane.showMessageDialog(null, "Comanda s-a finalizat cu succes!");
        UserDataService.muta(ev,"src/main/resources/additem.fxml");
    }
    @FXML
    void initialize() {
        assert npformular != null : "fx:id=\"npformular\" was not injected: check your FXML file 'formularComanda.fxml'.";
        assert nrtelformular != null : "fx:id=\"nrtelformular\" was not injected: check your FXML file 'formularComanda.fxml'.";
        assert codformular != null : "fx:id=\"codformular\" was not injected: check your FXML file 'formularComanda.fxml'.";
        assert adresaformular != null : "fx:id=\"adresaformular\" was not injected: check your FXML file 'formularComanda.fxml'.";
        assert finalizareformular != null : "fx:id=\"finalizareformular\" was not injected: check your FXML file 'formularComanda.fxml'.";

    }
}

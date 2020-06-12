package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
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
    void finalizareComanda(javafx.event.ActionEvent ev){
        JSONObject obj=new JSONObject();
        JSONArray jrr=new JSONArray();
        JSONParser jp=new JSONParser();
        try{
            FileReader file= new FileReader("Comenzi.json");
            jrr= (JSONArray)jp.parse(file);

        }
        catch (Exception e){

        }
        obj.put("Nume Complet", npformular.getText());
        obj.put("Telefon", nrtelformular.getText());
        obj.put("Adresa", adresaformular.getText());
        obj.put("Cod postal", codformular.getText());
        JSONArray jrr2=new JSONArray();
        JSONParser jp2=new JSONParser();
        try{
            FileReader file= new FileReader("cosCumparaturi.json");
            jrr2= (JSONArray)jp2.parse(file);

        }
        catch (Exception e){


        }
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
        try{
            FileWriter file=new FileWriter("Comenzi.json");
            file.write(jrr.toJSONString());
            file.close();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error occured");
        }
        JOptionPane.showMessageDialog(null, "Comanda s-a finalizat cu succes!");

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

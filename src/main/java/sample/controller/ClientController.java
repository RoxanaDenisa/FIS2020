package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.util.Callback;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton additemComenziButton;

    @FXML
    private JFXButton additemCosButton;

    @FXML
    private TreeTableView<String> clientTableView;

    @FXML
    private TreeTableColumn<String, String> branduri;


    @FXML
    void initialize() {
        JSONArray jrr=new JSONArray();
        Object ob=null;
        JSONParser jp=new JSONParser();
        ArrayList<TreeItem<String>> firme=new ArrayList<TreeItem<String>>();

        //fetch file
        try{
            FileReader file= new FileReader( "UserData.json");
            ob=jp.parse(file);
            jrr=(JSONArray)ob;
            file.close();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error occured while fetching");

        }
        int size=jrr.size();

        for (int i=0; i<size; i++){
            JSONObject x=(JSONObject)jrr.get(i);
            String n=(String) x.get("Nume de utilizator");
            boolean cf=(boolean) x.get("Firma");

                if(cf==true)
                {

                    TreeItem<String> item=new TreeItem<String>(n);
                    firme.add(item);
                }

            }
            TreeItem<String> Parent=new TreeItem<>("Firme disponibile:");
            for(TreeItem a:firme){
                Parent.getChildren().add(a);
            }
             branduri.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<String,String>, ObservableValue<String>>() {
                 @Override
                 public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<String,String> param) {
                     return new SimpleStringProperty(param.getValue().getValue());
                 }
             });
             clientTableView.setRoot(Parent);
            }

        }

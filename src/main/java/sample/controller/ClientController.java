package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.io.File;
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
    private TreeTableColumn<Hyperlink, Hyperlink> branduri;


    @FXML
    void initialize() {
        JSONArray jrr=new JSONArray();
        Object ob=null;
        JSONParser jp=new JSONParser();
        ArrayList<TreeItem<Hyperlink>> firme=new ArrayList<TreeItem<Hyperlink>>();

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
                     Image img=new Image("/assets/edit.png");
                     ImageView iv=new ImageView(img);
                    Hyperlink link=new Hyperlink(n,iv);
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
                    TreeItem<Hyperlink> item=new TreeItem<Hyperlink>(link);
                    firme.add(item);
                }

            }
            TreeItem<String> Parent=new TreeItem<>("Firme disponibile:");
            for(TreeItem a:firme){
                Parent.getChildren().add(a);
            }
             branduri.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Hyperlink,Hyperlink>, ObservableValue<Hyperlink>>() {
                 @Override
                 public ObservableValue<Hyperlink> call(TreeTableColumn.CellDataFeatures<Hyperlink,Hyperlink> param) {
                     return new SimpleObjectProperty<>(param.getValue().getValue());
                 }
             });
             clientTableView.setRoot(Parent);
            }

        }

package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class FormularProdusController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField produsNume;

    @FXML
    private JFXTextField produsIngrediente;

    @FXML
    private JFXTextField produsStoc;

    @FXML
    private JFXTextField produsCantitate;

    @FXML
    private JFXTextField produsPret;

    @FXML
    private JFXButton produsImagine;

    @FXML
    private JFXButton salvareProdus;
    @FXML
    private JFXTextField furnizorFirma;
    @FXML
    private JFXButton backButton;

    @FXML
    void inapoiEditare(ActionEvent event) throws  Exception {
        URL url=new File("src/main/resources/furnizorEditareProduse.fxml").toURI().toURL();
        Parent home= FXMLLoader.load(url);
        Scene s=new Scene(home);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }
    @FXML
    void goToSalvareImagine(ActionEvent event) throws  Exception {
        URL url=new File("src/main/resources/adaugaImagineProdus.fxml").toURI().toURL();
        Parent home= FXMLLoader.load(url);
        Scene s=new Scene(home);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }

    @FXML
    void initialize() {
        IncarcaImagineController ic;
        salvareProdus.setOnAction(event -> {
            JSONObject obj=new JSONObject();
            JSONArray jrr=new JSONArray();
            JSONParser jp=new JSONParser();
            try{
                FileReader file= new FileReader("ProductData.json");
                jrr= (JSONArray)jp.parse(file);

            }
            catch (Exception e){

            }
            obj.put("Nume produs", produsNume.getText());
            obj.put("Ingrediente", produsIngrediente.getText());
            obj.put("Stoc", produsStoc.getText());
            obj.put("Cantitate", produsCantitate.getText());
            obj.put("Pret", produsPret.getText());
            obj.put("Firma",furnizorFirma.getText());

            jrr.add(obj);
            try{
                FileWriter file=new FileWriter("ProductData.json");
                file.write(jrr.toJSONString());
                file.close();
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null,"Error occured");
            }
            JOptionPane.showMessageDialog(null, "Salvare reusita");

        });

    }
}

package sample.controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sample.services.UserDataService;

import javax.swing.*;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import static sample.services.UserDataService.hash;

public class SignupController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField SignupNumeComplet;

    @FXML
    private JFXTextField SignupNumeUtilizator;

    @FXML
    private JFXPasswordField SignupParola;

    @FXML
    private JFXTextField SignupAdresaMail;

    @FXML
    private JFXCheckBox SignupCheckClient;

    @FXML
    private JFXCheckBox SignUpCheckFirma;

    @FXML
    private JFXButton SignupInregistrare;

    @FXML
    private void handleClient(){
        if (SignupCheckClient.isSelected())
        {
            SignUpCheckFirma.setSelected(false);
        }
    }
    @FXML
    private void handleFirma(){
        if (SignUpCheckFirma.isSelected())
        {
            SignupCheckClient.setSelected(false);
        }
    }
    @FXML
    private void goBack(javafx.event.ActionEvent ev) throws Exception{
        UserDataService.muta(ev,"src/main/resources/login.fxml");

    }

    @FXML
    void initialize() {
        SignupInregistrare.setOnAction(event -> {
            JSONObject obj=new JSONObject();
            JSONArray jrr=new JSONArray();
            JSONParser jp=new JSONParser();
            try {
                jrr=UserDataService.OpenFile("UserData.json");
            } catch (Exception e) {
                e.printStackTrace();
            }
            obj.put("Nume Complet", SignupNumeComplet.getText());
            obj.put("Nume de utilizator", SignupNumeUtilizator.getText());
            obj.put("Adresa de mail", SignupAdresaMail.getText());
            String pass=SignupParola.getText();
            try {
                String hashedpass=hash(pass);
                obj.put("Parola", hashedpass);
            } catch (NoSuchAlgorithmException e) {
            }

            obj.put("Client", SignupCheckClient.isSelected());
            obj.put("Firma", SignUpCheckFirma.isSelected());
            jrr.add(obj);
            UserDataService.writeFile(jrr,"UserData.json");
            JOptionPane.showMessageDialog(null, "Inregistrare reusita");

        });



    }


    }


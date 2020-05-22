package sample.controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
        URL url=new File("src/main/resources/login.fxml").toURI().toURL();
        Parent home= FXMLLoader.load(url);
        Scene s=new Scene(home);
        Stage window=(Stage)((Node)ev.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();

    }
    public static String hash(String p) throws NoSuchAlgorithmException{
        MessageDigest md=MessageDigest.getInstance("MD5");
        md.update(p.getBytes());
        byte[] b=md.digest();
        StringBuffer SB=new StringBuffer();
        for (byte b1:b){
            SB.append(Integer.toHexString(b1& 0xff).toString());

        }
        return SB.toString();
    }
    @FXML
    void initialize() {
        SignupInregistrare.setOnAction(event -> {
            JSONObject obj=new JSONObject();
            JSONArray jrr=new JSONArray();
            JSONParser jp=new JSONParser();
            try{
                FileReader file= new FileReader("UserData.json");
                jrr= (JSONArray)jp.parse(file);

            }
            catch (Exception e){

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
            try{
                FileWriter file=new FileWriter("UserData.json");
                file.write(jrr.toJSONString());
                file.close();
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null,"Error occured");
            }
            JOptionPane.showMessageDialog(null,jrr);

        });



    }


    }


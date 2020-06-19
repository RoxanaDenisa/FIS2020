package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sample.services.UserDataService;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import static sample.services.UserDataService.hash;

public class LoginController {
    public static String retinNume;
    @FXML
    private ResourceBundle resources;
    @FXML
    public static Text registrationMessage;
    @FXML
    private URL location;

    @FXML
    public JFXPasswordField loginPassword;

    @FXML
    public JFXTextField loginUsername;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXButton loginSignupButton;
    JSONArray jrr=new JSONArray();
    private void initializareCosCumparaturi(){
        JSONArray jrr=new JSONArray();
        try{
            FileWriter file=new FileWriter("cosCumparaturi.json");
            file.write(jrr.toJSONString());
            file.close();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error occured");
        }

    }

    @FXML
    private void mutaIngregistrare(javafx.event.ActionEvent ev) throws Exception{
        URL url=new File("src/main/resources/inregistrare.fxml").toURI().toURL();
        Parent home=FXMLLoader.load(url);
        Scene s=new Scene(home);
        Stage window=(Stage)((Node)ev.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();

    }

    @FXML
    public void apasaAutentificare(javafx.event.ActionEvent ev)throws Exception{
        JSONArray jrr=new JSONArray();
        try{
        jrr= UserDataService.OpenFile("UserData.json");
            //registrationMessage.setText("Login successfully!");
        }

        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error occured while fetching");
            //registrationMessage.setText("Error login!");
        }
        JSONObject obj=new JSONObject();

        int size=jrr.size();
        obj.put("Nume de utilizator", loginUsername.getText());
        String pass=loginPassword.getText();
        try {
            String hashedpass=hash(pass);
            obj.put("Parola", hashedpass);
        } catch (NoSuchAlgorithmException e) {
        }

        String nume=(String) obj.get("Nume de utilizator");
        String parola=(String) obj.get("Parola");
        for (int i=0; i<size; i++){
            JSONObject x=(JSONObject)jrr.get(i);
            String n=(String) x.get("Nume de utilizator");
            retinNume=n;
            String p=(String) x.get("Parola");
            boolean cc=(boolean) x.get("Client");
            boolean cf=(boolean) x.get("Firma");
            if (nume.equals(n)&& parola.equals(p)){
                if(cc==true)
                {
                URL url=new File("src/main/resources/additem.fxml").toURI().toURL();
                Parent home=FXMLLoader.load(url);
                Scene s=new Scene(home);
                Stage window=(Stage)((Node)ev.getSource()).getScene().getWindow();
                window.setScene(s);
                window.show();

                }
                else
                    if(cf==true)
                    {
                        URL url=new File("src/main/resources/furnizor.fxml").toURI().toURL();
                        Parent home=FXMLLoader.load(url);
                        Scene s=new Scene(home);
                        Stage window=(Stage)((Node)ev.getSource()).getScene().getWindow();
                        window.setScene(s);
                        window.show();

                    }
                break;
            }
            else{
                if (i==size-1){
                    JOptionPane.showMessageDialog(null,"Password/Username incorrect");}

            }

        }

      initializareCosCumparaturi();


    }
}
package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sample.services.UserDataService;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;

public class ClientController {
    public static String firma;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton additemComenziButton;

    @FXML
    private JFXButton additemCosButton;

    @FXML
    private TilePane clientTP;
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
    private void goComenzi(javafx.event.ActionEvent ev) throws Exception {
        UserDataService.muta(ev,"src/main/resources/ComenziClient.fxml");
    }
    @FXML
    private void deconectare(javafx.event.ActionEvent ev) throws Exception {
        UserDataService.muta(ev,"src/main/resources/login.fxml");
    }
    @FXML
    private void goCosCumparaturi(javafx.event.ActionEvent ev) throws Exception {
        UserDataService.muta(ev,"src/main/resources/clientCosCumparaturi.fxml");
    }
    @FXML
    void initialize() {

        JSONArray jrr=new JSONArray();
        JSONParser jp=new JSONParser();
        try{
            FileReader file= new FileReader("LoggoFirme.json");
            jrr= (JSONArray)jp.parse(file);

        }
        catch (Exception e){

        }
        JSONArray jrrr=new JSONArray();
        JSONParser jpp=new JSONParser();
        try{
            FileReader filee= new FileReader("UserData.json");
            jrrr= (JSONArray)jpp.parse(filee);

        }
        catch (Exception e){

        }
        int size=jrr.size();
        int sizee=jrrr.size();
        int sw;
        for (int i=0; i<sizee; i++) {
            sw=1;
            VBox v = new VBox();
            JSONObject x=(JSONObject) jrrr.get(i);
            String n = (String) x.get("Nume de utilizator");
            boolean f=(boolean) x.get("Firma");
            if(f==true) {
                for (int j = 0; j < size && sw == 1; j++) {
                    JSONObject xx=(JSONObject) jrr.get(j);
                    String nn = (String) xx.get("Nume");
                    if(nn.equals(n)){
                        sw=0;
                        String p = (String) xx.get("Imagine");
                        byte[] decodedBytes = Base64.getDecoder().decode(p.getBytes());
                        ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
                        BufferedImage bi = null;
                        try {
                            bi = ImageIO.read(bis);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        WritableImage wi = null;
                        if (bi != null) {
                            wi = new WritableImage(bi.getWidth(), bi.getHeight());
                            PixelWriter pw = wi.getPixelWriter();
                            for (int t = 0; t < bi.getWidth(); t++) {
                                for (int u = 0; u < bi.getHeight(); u++) {
                                    pw.setArgb(t, u, bi.getRGB(t, u));
                                }
                            }
                            ImageView imgv = new ImageView(wi);
                            imgv.setFitHeight(150);
                            imgv.setFitWidth(150);
                            Hyperlink link = new Hyperlink(null, imgv);
                            link.setOnAction(e->{

                                try {
                                    firma=n;

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
                            v.getChildren().add(link);
                            Text t=new Text(n);
                            TextFlow tf=new TextFlow(t);
                            tf.setTextAlignment(TextAlignment.CENTER);
                            v.getChildren().add(tf);
                        }
                    }

                }
                if(sw==1){
                    Image wi=new Image("/assets/faraLoggo.png");
                    ImageView imgv = new ImageView(wi);
                    imgv.setFitHeight(150);
                    imgv.setFitWidth(150);
                    Hyperlink link = new Hyperlink(null, imgv);
                    link.setOnAction(e->{

                        try {
                            firma=n;
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
                    v.getChildren().add(link);
                    Text t=new Text(n);
                    TextFlow tf=new TextFlow(t);
                    tf.setTextAlignment(TextAlignment.CENTER);
                    v.getChildren().add(tf);
                }
                clientTP.getChildren().add(v);

            }
            }
        initializareCosCumparaturi();
    }

}
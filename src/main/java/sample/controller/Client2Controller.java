package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;


public class Client2Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton clientPs1Comenzi;

    @FXML
    private JFXButton clientPs1Cos;

    @FXML
    private TilePane client2TP;

    @FXML
    void goCosCumparaturi(javafx.event.ActionEvent ev){
        try {
            URL url=new File("src/main/resources/clientCosCumparaturi.fxml").toURI().toURL();
            Parent home= null;
            home = FXMLLoader.load(url);
            Scene s=new Scene(home);
            Stage window=(Stage)((Node)ev.getSource()).getScene().getWindow();
            window.setScene(s);
            window.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    void initialize() {
        JSONArray jrr=new JSONArray();
        JSONParser jp=new JSONParser();
        try{
            FileReader file= new FileReader("ProductData.json");
            jrr= (JSONArray)jp.parse(file);

        }
        catch (Exception e){

        }
        int size=jrr.size();
        for(int i=0;i<size;i++){
            VBox v=new VBox();
            JSONObject j=(JSONObject) jrr.get(i);
            String n=(String)j.get("Firma");
            if(ClientController.firma.equals(n))
            {
                String p = (String) j.get("Imagine");
                String np=(String) j.get("Nume produs");
                String cant=(String) j.get("Cantitate");
                String pret=(String) j.get("Pret");
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
                    imgv.setFitHeight(100);
                    imgv.setFitWidth(100);
                    v.getChildren().add(imgv);
                    Text t=new Text("Denumire: "+np+"\nCantitate: "+cant+" g\n Pret: "+pret+" lei");
                    v.getChildren().add(t);
                    Button c=new Button("Adauga in cos");
                    c.setOnAction(e->{
                        JSONObject obj=new JSONObject();
                        JSONArray jsn=new JSONArray();
                        JSONParser jsp=new JSONParser();
                        try{
                            FileReader fi= new FileReader("cosCumparaturi.json");
                            jsn= (JSONArray)jsp.parse(fi);

                        }
                        catch (Exception exc){

                        }
                        obj.put("Nume",np );
                        obj.put("Pret",pret);
                        obj.put("Imagine",p);
                        jsn.add(obj);
                        try{
                            FileWriter file=new FileWriter("cosCumparaturi.json");
                            file.write(jsn.toJSONString());
                            file.close();
                        }
                        catch (Exception exc){
                            JOptionPane.showMessageDialog(null,"Error occured");
                        }
                        JOptionPane.showMessageDialog(null, "Produs adaugat in cosul de cumparaturi");

                    });
                    v.getChildren().add(c);
                }
                client2TP.getChildren().add(v);
            }
            }
    }}

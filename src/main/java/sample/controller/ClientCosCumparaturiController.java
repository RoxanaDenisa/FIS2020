package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;

public class ClientCosCumparaturiController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton clientComanda;

    @FXML
    private JFXButton clientCos;

    @FXML
    private VBox prodBox;

    @FXML
    void initialize() {
        JSONArray jrr=new JSONArray();
        JSONParser jp=new JSONParser();
        try{
            FileReader file= new FileReader("cosCumparaturi.json");
            jrr= (JSONArray)jp.parse(file);

        }
        catch (Exception e){

        }
        int size=jrr.size();
        for (int i=0; i<size; i++) {
            HBox h = new HBox();
            JSONObject x=(JSONObject)jrr.get(i);
            String p = (String) x.get("Imagine");
            String n=(String) x.get("Nume");
            String pr=(String) x.get("Pret");
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
                h.getChildren().add(imgv);
                Text t=new Text(n);
                t.setTextAlignment(TextAlignment.CENTER);
                h.getChildren().add(t);
                Text t2=new Text(pr);
                t2.setTextAlignment(TextAlignment.CENTER);
                h.getChildren().add(t2);
            }
            prodBox.getChildren().add(h);
        }
    }
}

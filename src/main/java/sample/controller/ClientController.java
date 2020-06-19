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
import javafx.scene.image.WritableImage;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sample.services.UserDataService;

import java.io.File;
import java.net.URL;
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
        UserDataService.writeFile(jrr,"cosCumparaturi.json");
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
    void initialize() throws Exception {

        JSONArray jrr=new JSONArray();
        jrr=UserDataService.OpenFile("LoggoFirme.json");
        JSONArray jrrr=new JSONArray();
        jrrr=UserDataService.OpenFile("UserData.json");
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
                        WritableImage wi = null;
                        wi=UserDataService.deodarePoza(p);
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
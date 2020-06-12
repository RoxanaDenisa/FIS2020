package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sample.Items;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.controller.LoginController.retinNume;

public class ComenziFurnizorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton goLoggo;

    @FXML
    private JFXButton goEdit;

    @FXML
    private TableView<Items> tabComFur;

    @FXML
    private TableColumn<Items, String> ColComFur;

    @FXML
    private TableColumn<Items, String> colStatFur;

    void initCol() {
        ColComFur.setCellValueFactory(new PropertyValueFactory<Items, String>("date"));
        colStatFur.setCellValueFactory(new PropertyValueFactory<Items, String>("hb"));

    }
    void modificare(String s,String p,JSONObject o) throws IOException {

        {
            JSONArray jrr = new JSONArray();
            JSONParser jp = new JSONParser();
            try {
                FileReader file = new FileReader("Comenzi.json");
                jrr = (JSONArray) jp.parse(file);

            } catch (Exception e) {

            }
            int size = jrr.size();
            for (int i = 0; i < size; i++) {
                JSONObject x = (JSONObject) jrr.get(i);
                String n=(String) x.get("Nume Complet");
                String pr=(String)x.get("Produse");
                if(n.equals(s)&&pr.equals(p)) {
                    jrr.remove(i);
                    jrr.add(o);
                    FileWriter file = new FileWriter("Comenzi.json");
                    file.write(jrr.toJSONString());
                    file.close();
                    break;
                }
            }
        }
    }
    @FXML
    void initialize() throws IOException {
        initCol();
        ObservableList<Items> data = FXCollections.observableArrayList();
        //data.add(new Items("merge??",new Hyperlink("nuu")));
        JSONArray jrr = new JSONArray();
        JSONParser jp = new JSONParser();
        try {
            FileReader file = new FileReader("Comenzi.json");
            jrr = (JSONArray) jp.parse(file);

        } catch (Exception e) {

        }
        int size = jrr.size();
        for (int i = 0; i < size; i++) {
            JSONObject x = (JSONObject) jrr.get(i);
            String n = (String) x.get("Firma");
            String aux1=null;
            HBox aux2=new HBox();
            if (n.equals(retinNume)) {
                String nc=(String)x.get("Nume Complet");
                String ad=(String)x.get("Adresa");
                String cp=(String)x.get("Cod postal");
                String nt=(String)x.get("Telefon");
                String pr=(String)x.get("Produse");
                aux1=nc+",Adresa: "+ad+", Cod Postal:"+cp+",Telefon:"+nt+"Produse:"+pr;
                String s=(String)x.get("Status");
                if(s.equals(" ")){
                    System.out.println("mrg");
                Image wi = new Image("/assets/yes.png");
                ImageView imgv = new ImageView(wi);
                imgv.setFitHeight(20);
                imgv.setFitWidth(20);
                Hyperlink h1=new Hyperlink("  ",imgv);
                h1.setOnAction(e->{
                    x.put("Status","Comanda acceptata");

                    try {
                        modificare(nc,pr,x);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                Image wi2 = new Image("/assets/No.png");
                ImageView imgv2 = new ImageView(wi2);
                imgv2.setFitHeight(20);
                imgv2.setFitWidth(20);
                Hyperlink h2=new Hyperlink("  ",imgv2);
                    h2.setOnAction(e->{
                          x.put("Status","Comanda respinsa");
                        try {
                            modificare(nc,pr,x);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    aux2.getChildren().addAll(h1,h2);
               }
               else {
                    ObservableList<String> options =
                            FXCollections.observableArrayList(
                                    "comanda predata curierului",
                                    "comanda in tranzit",
                                    "comanda livrata"
                            );
                    ComboBox cb = new ComboBox(options);
                    cb.setPromptText("Modifiare status");
                    cb.setOnAction(e->{
                          String val=(String) cb.getValue();
                          x.put("Status",val);
                          String tr=(String) x.get("Status");
                          System.out.println(tr);
                        try {
                            modificare(nc,pr,x);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    aux2.getChildren().add(cb);
                }
                data.add(new Items(aux1,aux2));
            }

        }
        tabComFur.setItems(data);
    }

}
package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sample.Items;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;

import static sample.controller.LoginController.retinNume;

public class ComenziFurnizorController extends Component {

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
    private final JFileChooser openFileChooser;
    private BufferedImage originalBI;
    public ComenziFurnizorController() {
        openFileChooser=new JFileChooser();
        openFileChooser.setCurrentDirectory(new File("D:\\Img"));
        openFileChooser.setFileFilter(new FileNameExtensionFilter("Png Images","Png"));
    }
    public static String encoder(String imagePath) {
        String base64Image = "";
        File file = new File(imagePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            // Reading a Image file from file system
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
            base64Image = Base64.getEncoder().encodeToString(imageData);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        return base64Image;
    }

    @FXML
    public void selecteazaPoza(ActionEvent ev) {
        System.out.println("ceva");
        JSONObject obj=new JSONObject();
        JSONArray jrr=new JSONArray();
        JSONParser jp=new JSONParser();
        try{
            FileReader file= new FileReader("LoggoFirme.json");
            jrr= (JSONArray)jp.parse(file);

        }
        catch (Exception e){

        }
        int returnValue=openFileChooser.showOpenDialog(this);
        if(returnValue== JFileChooser.APPROVE_OPTION){
            try{
                originalBI= ImageIO.read(openFileChooser.getSelectedFile());
                JOptionPane.showMessageDialog(null, "Imagine incarcata");
                String img;
                String x=openFileChooser.getSelectedFile().getAbsolutePath();
                img=encoder(x);
                obj.put("Nume",LoginController.retinNume);
                obj.put("Imagine",img);
                jrr.add(obj);
                try{
                    FileWriter file=new FileWriter("LoggoFirme.json");
                    file.write(jrr.toJSONString());
                    file.close();
                }
                catch (Exception e){
                    JOptionPane.showMessageDialog(null,"Error occured");
                }
            }
            catch (IOException ex)
            {
                JOptionPane.showMessageDialog(null, "Nu s.a putut incarca");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Nu ati selectat niciun fisier");

        }

    }
    @FXML
    private void goback(javafx.event.ActionEvent ev){
        try {
            URL url=new File("src/main/resources/furnizor.fxml").toURI().toURL();
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
    private void mutaEditareProduse(javafx.event.ActionEvent ev) throws Exception{
        URL url=new File("src/main/resources/furnizorEditareProduse.fxml").toURI().toURL();
        Parent home= FXMLLoader.load(url);
        Scene s=new Scene(home);
        Stage window=(Stage)((Node)ev.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();

    }
    @FXML
    private void mutaComenzi(javafx.event.ActionEvent ev) throws Exception{
        URL url=new File("src/main/resources/comenziFurnizor.fxml").toURI().toURL();
        Parent home= FXMLLoader.load(url);
        Scene s=new Scene(home);
        Stage window=(Stage)((Node)ev.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();

    }

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
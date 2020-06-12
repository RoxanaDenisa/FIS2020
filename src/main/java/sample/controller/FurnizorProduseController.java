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
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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

public class FurnizorProduseController extends Component {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton edPrStoc;

    @FXML
    private JFXButton edPrStatus;

    @FXML
    private TilePane furnizorTP;
    private final JFileChooser openFileChooser;
    private BufferedImage originalBI;

    public FurnizorProduseController() {
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
    private void goBack(javafx.event.ActionEvent ev){
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
    private void goVizualizare(javafx.event.ActionEvent ev){
        try {
            URL url=new File("src/main/resources/comenziFurnizor.fxml").toURI().toURL();
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
    private void goEditareProduse(javafx.event.ActionEvent ev){
        try {
            URL url=new File("src/main/resources/furnizorEditareProduse.fxml").toURI().toURL();
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
    public void selecteazaPoza(javafx.event.ActionEvent ev) {
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
                obj.put("Nume", retinNume);
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
    void initialize() {
        Image img=new Image("/assets/add.png");
        ImageView iv=new ImageView(img);
        iv.setFitHeight(70);
        iv.setFitWidth(70);
        Hyperlink link=new Hyperlink("   ",iv);
        link.setOnAction(e->{

            try {
                URL url=new File("src/main/resources/formularAddProdus.fxml").toURI().toURL();
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
        JSONArray jrr=new JSONArray();
        JSONParser jp=new JSONParser();
        try{
            FileReader file= new FileReader("ProductData.json");
            jrr= (JSONArray)jp.parse(file);

        }
        catch (Exception e){

        }
        int size=jrr.size();
        for (int i=0; i<size; i++) {
            JSONObject x = (JSONObject) jrr.get(i);
            String n = (String) x.get("Firma");
            if(n.equals(retinNume)){
                VBox v =new VBox();
                String p = (String) x.get("Imagine");

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
                    }
                    ImageView imgv=new ImageView(wi);
                    imgv.setFitHeight(70);
                    imgv.setFitWidth(70);
                    v.getChildren().add(imgv);
                    furnizorTP.getChildren().add(v);


            }
        }
        VBox vb =new VBox();
        vb.getChildren().add(link);
        furnizorTP.getChildren().add(vb);
    }

    }


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
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sample.services.UserDataService;

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
    private void goBack(javafx.event.ActionEvent ev) throws Exception {
        UserDataService.muta(ev,"src/main/resources/furnizor.fxml");
    }
    @FXML
    private void goVizualizare(javafx.event.ActionEvent ev) throws Exception {
        UserDataService.muta(ev,"src/main/resources/comenziFurnizor.fxml");
    }
    @FXML
    private void goEditareProduse(javafx.event.ActionEvent ev) throws Exception {
       UserDataService.muta(ev,"src/main/resources/furnizorEditareProduse.fxml");
    }
    @FXML
    private void goFormular(javafx.event.ActionEvent ev) throws Exception {
        UserDataService.muta(ev,"src/main/resources/formularAddProdus.fxml");
    }
    @FXML
    public void selecteazaPoza(javafx.event.ActionEvent ev) throws Exception {
        JSONObject obj=new JSONObject();
        JSONArray jrr;
        jrr=UserDataService.OpenFile("LoggoFirme.json");
        int returnValue=openFileChooser.showOpenDialog(this);
        if(returnValue== JFileChooser.APPROVE_OPTION){
            try{
                originalBI= ImageIO.read(openFileChooser.getSelectedFile());
                JOptionPane.showMessageDialog(null, "Imagine incarcata");
                String img;
                String x=openFileChooser.getSelectedFile().getAbsolutePath();
                img=encoder(x);
                int n=jrr.size();
                for(int i=0;i<n;i++){
                    JSONObject o = (JSONObject) jrr.get(i);
                    String str = (String) o.get("Nume");
                    if(str.equals(LoginController.retinNume))
                    {
                        jrr.remove(i);
                        UserDataService.writeFile(jrr,"LoggoFirme.json");
                        break;
                    }
                }
                obj.put("Nume",LoginController.retinNume);
                obj.put("Imagine",img);
                jrr.add(obj);
                UserDataService.writeFile(jrr,"LoggoFirme.json");
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
    void editare(Integer integ, javafx.event.ActionEvent e) throws Exception {
        JSONArray jr = new JSONArray();
        JSONParser jp = new JSONParser();
        try {
            FileReader file= new FileReader("ProductData.json");
            jr = (JSONArray) jp.parse(file);
            file.close();

        } catch (Exception ex) {

        }
        int size = jr.size();
        for (int i = 0; i < size; i++) {
            JSONObject x = (JSONObject) jr.get(i);
            if(integ==i){
                jr.remove(x);
                UserDataService.writeFile(jr,"ProductData.json");
                    goFormular(e);
                break;
                }
            }
        }
    void stergeprod(Integer integ, javafx.event.ActionEvent e) throws Exception {
        JSONArray jr = new JSONArray();
        jr=UserDataService.OpenFile("ProductData.json");
        int size = jr.size();
        for (int i = 0; i < size; i++) {
            JSONObject x = (JSONObject) jr.get(i);
            System.out.println(integ==i);
            if(integ==i){
                jr.remove(x);
                System.out.println("Pr"+jr.size());
               UserDataService.writeFile(jr,"ProductData.json");
                break;
            }
        }
        goEditareProduse(e);
    }
    @FXML
    void initialize() throws Exception {
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
        jrr=UserDataService.OpenFile("ProductData.json");
        int size=jrr.size();
        for (int i=0; i<size; i++) {
            JSONObject x = (JSONObject) jrr.get(i);
            String n = (String) x.get("Firma");
            if(n.equals(retinNume)){
                VBox v =new VBox();
                String p = (String) x.get("Imagine");
                WritableImage wi = null;
                wi=UserDataService.deodarePoza(p);
                    ImageView imgv=new ImageView(wi);
                    imgv.setFitHeight(150);
                    imgv.setFitWidth(150);
                    v.getChildren().add(imgv);
                    String np=(String) x.get("Nume produs");
                    String ing=(String) x.get("Ingrediente");
                    String pre=(String) x.get("Pret");
                    String can=(String) x.get("Cantitate");
                    Text t=new Text("Nume produs:"+np);
                    v.getChildren().add(t);
                    t=new Text("Ingrediente:"+ing);
                    v.getChildren().add(t);
                    t=new Text("Pret:"+pre);
                    v.getChildren().add(t);
                    t=new Text("Cantitate:"+can);
                    v.getChildren().add(t);
                    HBox h=new HBox();
                    h.getChildren().add(v);
                    Image image=new Image("/assets/trash.png");
                    ImageView imageView=new ImageView(image);
                    imageView.setFitWidth(30);
                    imageView.setFitHeight(30);
                    Hyperlink hy=new Hyperlink(null,imageView);
                    Integer intg=new Integer(i);
                    hy.setOnAction(e->{
                       try {
                            stergeprod(intg,e);
                        } catch (Exception ex) {
                          ex.printStackTrace();
                        }
                    });
                   h.getChildren().add(hy);
                   Image image2=new Image("/assets/edit.png");
                ImageView imageView2=new ImageView(image2);
                imageView2.setFitWidth(30);
                imageView2.setFitHeight(30);
                Hyperlink hy2=new Hyperlink(null,imageView2);
                Integer intg2=new Integer(i);
                hy2.setOnAction(e->{
                    try {
                        editare(intg2,e);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
                h.getChildren().add(hy2);
                    furnizorTP.getChildren().add(h);

            }
        }
        VBox vb =new VBox();
        vb.getChildren().add(link);
        furnizorTP.getChildren().add(vb);
    }

    }


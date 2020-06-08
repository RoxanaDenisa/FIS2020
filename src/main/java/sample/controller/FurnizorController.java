package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class FurnizorController extends Component {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton furnizorStoc;

    @FXML
    private JFXButton furnizorStatus;
    @FXML
    private JFXButton modificareLoggo;

    private final JFileChooser openFileChooser;
    private BufferedImage originalBI;
    public FurnizorController() {
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
    private void mutaEditareProduse(javafx.event.ActionEvent ev) throws Exception{
        URL url=new File("src/main/resources/furnizorEditareProduse.fxml").toURI().toURL();
        Parent home= FXMLLoader.load(url);
        Scene s=new Scene(home);
        Stage window=(Stage)((Node)ev.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();

    }
    @FXML
    void initialize() {
    }
}

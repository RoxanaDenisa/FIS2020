package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
    private void Deconectare(javafx.event.ActionEvent ev) throws Exception {
        UserDataService.muta(ev,"src/main/resources/login.fxml");
    }
    @FXML
    public void selecteazaPoza(ActionEvent ev) throws Exception {
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
    @FXML
    private void mutaEditareProduse(javafx.event.ActionEvent ev) throws Exception{
      UserDataService.muta(ev,"src/main/resources/furnizorEditareProduse.fxml");
    }
    @FXML
    private void mutaComenzi(javafx.event.ActionEvent ev) throws Exception{
        UserDataService.muta(ev,"src/main/resources/comenziFurnizor.fxml");

    }
    @FXML
    void initialize() {
    }
}

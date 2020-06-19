package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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

public class FormularProdusController extends Component {
    private final JFileChooser openFileChooser;
    public FormularProdusController() {
        openFileChooser=new JFileChooser();
        openFileChooser.setCurrentDirectory(new File("D:\\Img"));
        openFileChooser.setFileFilter(new FileNameExtensionFilter("Png Images","Png"));
    }
    private BufferedImage originalBI;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField produsNume;

    @FXML
    private JFXTextField produsIngrediente;

    @FXML
    private JFXTextField produsStoc;

    @FXML
    private JFXTextField produsCantitate;

    @FXML
    private JFXTextField produsPret;

    @FXML
    private JFXButton produsImagine;

    @FXML
    private JFXButton salvareProdus;

    @FXML
    private JFXButton backButton;

    @FXML
    void inapoiEditare(ActionEvent ev) throws  Exception {
        UserDataService.muta(ev,"src/main/resources/furnizorEditareProduse.fxml");
    }
    @FXML
    void openFile(ActionEvent e){
        int returnValue=openFileChooser.showOpenDialog(this);
        if(returnValue== JFileChooser.APPROVE_OPTION){
            try{
                originalBI= ImageIO.read(openFileChooser.getSelectedFile());
                JOptionPane.showMessageDialog(null, "Imagine incarcata");
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
    void initialize() {
        IncarcaImagineController ic;
        salvareProdus.setOnAction(event -> {
            JSONObject obj=new JSONObject();
            JSONArray jrr=new JSONArray();
            try {
                jrr=UserDataService.OpenFile("ProductData.json");
            } catch (Exception e) {
                e.printStackTrace();
            }
            obj.put("Nume produs", produsNume.getText());
            obj.put("Ingrediente", produsIngrediente.getText());
            obj.put("Stoc", produsStoc.getText());
            obj.put("Cantitate", produsCantitate.getText());
            obj.put("Pret", produsPret.getText());
            obj.put("Firma",LoginController.retinNume);
            String img;
            try {
                String x = openFileChooser.getSelectedFile().getAbsolutePath();
                img=encoder(x);
                obj.put("Locatie:",x);
                obj.put("Imagine",img);
                jrr.add(obj);
                UserDataService.writeFile(jrr,"ProductData.json");
                JOptionPane.showMessageDialog(null, "Salvare reusita");
                try {
                    inapoiEditare(event);
                } catch (Exception e) {

                }
            }
            catch(NullPointerException ex){
                JOptionPane.showMessageDialog(null, "Nu ati selectat nici o poza");
                try {
                    UserDataService.muta(event,"src/main/resources/formularAddProdus.fxml");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

    }
}

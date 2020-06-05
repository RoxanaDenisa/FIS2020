package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IncarcaImagineController extends javax.swing.JFrame {

    private final JFileChooser openFileChooser;
    private final JFileChooser saveFileChooser;
    private BufferedImage originalBI;
    private BufferedImage newBI;

    public BufferedImage getNewBI() {
        return newBI;
    }

    private int[][] pixels;
    public IncarcaImagineController(){
        //initComponents();
        openFileChooser=new JFileChooser();
        openFileChooser.setCurrentDirectory(new File("D:\\Img"));
        openFileChooser.setFileFilter(new FileNameExtensionFilter("Png Images","Png"));
        saveFileChooser=new JFileChooser();
        saveFileChooser.setCurrentDirectory(new File("/resources/imaginiProduse/"));
        saveFileChooser.setFileFilter(new FileNameExtensionFilter("Png Images","Png"));
    }
    private void immageToArray(){
        int width=originalBI.getWidth();
        int height=originalBI.getHeight();
        newBI=new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        pixels=new int[width][height];
        for(int i=0;i<width;i++)
        {
            for(int j=0;j<height;j++)
            {
                pixels[i][j]=originalBI.getRGB(i,j);
            }
        }
    }
    private void makeFiltredImage(){
        int alpha,red,green,blue,width,height=0;
        width=originalBI.getWidth();
        height=originalBI.getHeight();
        boolean hasAlpha=(originalBI.getAlphaRaster()!=null);
        for(int i=0;i<width;i++)
        {
            for(int j=0;j<height;j++){
                alpha=255;
                if(hasAlpha){
                    alpha=(pixels[i][j]&0xff000000)>>24;
                }
                red=(pixels[i][j]&0x00ff0000)>>16;
                green=(pixels[i][j]&0x0000ff00)>>8;
                blue=(pixels[i][j]&0x000000ff);
                alpha=alpha<<24;
                red=red<<16;
                green=green<<8;
                newBI.setRGB(i,j,(alpha|red|green|blue));
            }
        }
    }
    private void saveImg(){
        int returnValue=saveFileChooser.showSaveDialog(this);
        if(returnValue==JFileChooser.APPROVE_OPTION){
            try{
                ImageIO.write(newBI,"png",saveFileChooser.getSelectedFile());
                JOptionPane.showMessageDialog(null, "imagine salvata");
            }
             catch(IOException ioe){
                 JOptionPane.showMessageDialog(null, "Nu s.a putut salva");
             }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Nu s.a ales poza");
        }
    }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton incarcaImg;

    @FXML
    private JFXButton salveazaImg;

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
   @FXML
   void saveFile(ActionEvent e){
       immageToArray();
       makeFiltredImage();
       saveImg();

   }
    @FXML
    void initialize() {


    }
}

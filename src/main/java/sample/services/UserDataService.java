package sample.services;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class UserDataService  {
    public static String merge;
    public static JSONArray OpenFile (String path) throws Exception {
        JSONArray jrr=new JSONArray();
        Object ob=null;
        JSONParser jp=new JSONParser();

            FileReader file= new FileReader(path);
            ob=jp.parse(file);
            jrr=(JSONArray)ob;
            file.close();
        return jrr;
    }
    public static String hash(String p) throws NoSuchAlgorithmException {
        MessageDigest md=MessageDigest.getInstance("MD5");
        md.update(p.getBytes());
        byte[] b=md.digest();
        StringBuffer SB=new StringBuffer();
        for (byte b1:b){
            SB.append(Integer.toHexString(b1& 0xff).toString());

        }
        return SB.toString();
    }
    @FXML
    public static void muta(javafx.event.ActionEvent ev, String st) {
        try{
        URL url=new File(st).toURI().toURL();
        Parent home= FXMLLoader.load(url);
        Scene s=new Scene(home);
        Stage window=(Stage)((Node)ev.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();}
        catch(Exception e){

        }

    }
    public static void writeFile(JSONArray jrr,String st){
        try{
            FileWriter file=new FileWriter(st);
            file.write(jrr.toJSONString());
            file.close();
            merge="da";
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error occured");
            merge="nu";
        }
    }
    public static WritableImage deodarePoza(String p){
        WritableImage wi = null;
        byte[] decodedBytes = Base64.getDecoder().decode(p.getBytes());
        ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bi != null) {
            wi = new WritableImage(bi.getWidth(), bi.getHeight());
            PixelWriter pw = wi.getPixelWriter();
            for (int t = 0; t < bi.getWidth(); t++) {
                for (int u = 0; u < bi.getHeight(); u++) {
                    pw.setArgb(t, u, bi.getRGB(t, u));
                }
            }
    }
        return wi;
    }
}

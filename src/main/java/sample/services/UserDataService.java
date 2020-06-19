package sample.services;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserDataService  {
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
    public static void muta(javafx.event.ActionEvent ev, String st) throws Exception{
        URL url=new File(st).toURI().toURL();
        Parent home= FXMLLoader.load(url);
        Scene s=new Scene(home);
        Stage window=(Stage)((Node)ev.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();

    }

}

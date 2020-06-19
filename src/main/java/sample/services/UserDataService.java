package sample.services;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
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

}

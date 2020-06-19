package sample.services;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

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


}

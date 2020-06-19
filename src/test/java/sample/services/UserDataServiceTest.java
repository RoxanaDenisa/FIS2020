package sample.services;
import javafx.scene.image.WritableImage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserDataServiceTest {
    @Test
    public void testOpenFile() throws Exception {
        JSONArray jrr = UserDataService.OpenFile("UserData.json");
        assertNotNull(jrr);
    }
    @Test
    public void testPasswordEncoding() throws NoSuchAlgorithmException {
        assertEquals("afa939adf52ddcbd204c814afcdd754a", UserDataService.hash("daa"));

    }
    @Test
    public void  testWriteFile(){
        JSONArray jrr=new JSONArray();
        JSONObject jo=new JSONObject();
        jo.put("Text","test");
        jrr.add(jo);
        UserDataService.writeFile(jrr,"Test.json");
        assertEquals("da",UserDataService.merge);
    }
    @Test
    public void testDecodarePoza() throws Exception {
        JSONArray jrr = UserDataService.OpenFile("LoggoFirme.json");
        JSONObject ob=(JSONObject)jrr.get(0);
        String p=(String) ob.get("Imagine");
        WritableImage wi=UserDataService.deodarePoza(p);
        assertNotNull(wi);
    }

}

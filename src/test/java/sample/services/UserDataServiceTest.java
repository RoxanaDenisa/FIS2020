package sample.services;
import org.json.simple.JSONArray;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

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


}

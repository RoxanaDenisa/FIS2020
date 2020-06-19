package sample.services;
import org.json.simple.JSONArray;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UserDataServiceTest {
    @Test
    public void testOpenFile() throws Exception {
        JSONArray jrr = UserDataService.OpenFile("UserData.json");
        assertNotNull(jrr);
    }

}

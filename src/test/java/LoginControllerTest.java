import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.controller.LoginController;
import sample.services.FileSystemService;
import sample.services.UserDataService;

import static org.junit.Assert.assertEquals;

public class LoginControllerTest extends ApplicationTest {
    public static final String TEST_USER = "testUser";
    public static final String TEST_PASSWORD = "testPassword";
    private LoginController controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileSystemService.initApplicationHomeDirIfNeeded();
        UserDataService.OpenFile("UserData.json");

    }

    @Before
    public void setUp() throws Exception {
        controller = new LoginController();
        controller.loginUsername = new JFXTextField();
        controller.loginPassword = new JFXPasswordField();
        controller.registrationMessage = new Text();
        controller.loginPassword.setText(TEST_PASSWORD);
        controller.loginUsername.setText(TEST_USER);
    }

    @Test
    public void testHandleAddUserActionCode() throws Exception {
        controller.apasaAutentificare(new ActionEvent());
        assertEquals("Login successfully!", controller.registrationMessage.getText());
    }
}

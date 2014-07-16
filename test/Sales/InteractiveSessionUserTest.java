package Sales;

import Data.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

/**
 * Created by wateryheart on 11/Apr/2014.
 * @author MAR Chun Sum Connie (20057384)
 */
public class InteractiveSessionUserTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    InteractiveSessionUser interactiveSessionUser;
    Scanner scan;
    User user = new User("yangjun","yangjun");

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        scan = new Scanner(System.in);
        interactiveSessionUser = new InteractiveSessionUser();
        interactiveSessionUser.currentUser = new User("test","testing");
    }

    @After
    public void tearDown() throws Exception {
        scan.close();
        System.setOut(null);
        System.setErr(null);
        File finalLog = new File(System.getProperty("user.dir") +"/logAndSales.txt");
        if (finalLog.exists())
            finalLog.delete();
    }

    @Test
    public void testLogin() throws Exception {
        systemInMock.provideText("yangjun\nyangjun\n");
        assertTrue(interactiveSessionUser.login(scan));
    }

    @Test
    public void testGetInputName() throws Exception {
        systemInMock.provideText("yangjun");
        assertTrue(interactiveSessionUser.getInputName(scan).getName().equals("yangjun"));
    }

    @Test
    public void testGetLoginPassword() throws Exception {
        systemInMock.provideText("yangjun");
        interactiveSessionUser.getLoginPassword(user,scan);
    }

    @Test
    public void testLogOut() throws Exception {
        assertFalse(interactiveSessionUser.logOut());
    }
}

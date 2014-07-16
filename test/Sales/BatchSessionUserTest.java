package Sales;

import Data.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by wateryheart on 11/Apr/2014.
 * @author MAR Chun Sum Connie (20057384)
 */
public class BatchSessionUserTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    BatchSessionUser batchSessionUser;
    Scanner scan;
    File batchFile;
    User user = new User("yangjun","yangjun");
    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        batchFile = new File(System.getProperty("user.dir") +"/test/db/batchSessionUserTest.txt");
        scan = new Scanner(batchFile);
        batchSessionUser = new BatchSessionUser();
        batchSessionUser.currentUser = new User("test","testing");
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
        assertTrue(batchSessionUser.login(scan));
    }

    @Test
    public void testGetInputName() throws Exception {
        assertTrue(batchSessionUser.getInputName(scan).getName().equals("yangjun"));
    }

    @Test
    public void testGetLoginPassword() throws Exception {
        batchSessionUser.getLoginPassword(user,scan);
    }

    @Test
    public void testLogOut() throws Exception {
        assertFalse(batchSessionUser.logOut());
    }
}

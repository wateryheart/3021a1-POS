package Data;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

/**
 * Created by wateryheart on 29/Mar/2014.
 * @author MAR Chun Sum Connie (20057384)
 */
public class UserStoreTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    UserStore mockUserStore = mock(UserStore.class);
    UserStore mockUserStore2 = new UserStore();

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(null);
        System.setErr(null);
    }

    @Test   //wrong user file
    public void testCheckImportFile01() throws Exception {
        systemInMock.provideText("\n");
        mockUserStore2.checkImportFile("/wrongUserPasswordFile.txt");
    }

    @Test   //correct user file
    public void testCheckImportFile02() throws Exception {
        mockUserStore2.checkImportFile("/test/db/userPasswordFile.txt");
    }

    @Test
    public void testWrongImportFile() throws Exception {
        systemInMock.provideText("\n");
        mockUserStore2.wrongImportFile();
        assertEquals("<LOG> /userPasswordFile.txt does not exist.\n"+
                "Please put user name and password in a userPasswordFile.txt file under the directory.\n"+
                "Press Enter to continue.\n", outContent.toString());
    }

    @Test   //normal import user file
    public void testImportFile01() throws Exception {
        mockUserStore2.importFile("/test/db/userPasswordFile.txt");
    }

    @Test   (expected = Exception.class) //import user file exception
    public void testImportFile02() throws Exception {
        doThrow(new Exception()).when(mockUserStore).importFile("mockUserFile.txt");
        mockUserStore.importFile("mockUserFile.txt");
    }

    @Test   //wrong name
    public void testUserExist01() throws Exception {
        mockUserStore2.checkImportFile("/test/db/userPasswordFile.txt");
        mockUserStore2.userExist("cs");
    }

    @Test   //correct name
    public void testUserExist02() throws Exception {
        mockUserStore2.checkImportFile("/test/db/userPasswordFile.txt");
        mockUserStore2.userExist("chunlin");
    }

    @Test   //correct password
    public void testPasswordCorrect01() throws Exception {
        mockUserStore.checkImportFile("/test/db/userPasswordFile.txt");
        mockUserStore.passwordCorrect(mockUserStore.userExist("chunlin"),"chunlin");
    }

    @Test   //wrong password
    public void testPasswordCorrect02() throws Exception {
        mockUserStore.checkImportFile("/test/db/userPasswordFile.txt");
        mockUserStore.passwordCorrect(mockUserStore.userExist("chunlin"),"zhulin");
    }
}

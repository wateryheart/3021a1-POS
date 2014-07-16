package POS;

import org.junit.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by wateryheart on 18/Mar/2014.
 * @author MAR Chun Sum Connie (20057384)
 */
public class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(null);
        System.setErr(null);
        File finalLog = new File(System.getProperty("user.dir") +"/logAndSales.txt");
        if (finalLog.exists())
            finalLog.delete();
    }

    @Test
    public void testMainConstructor() throws Exception {
        assertNotNull(new Main());
    }

    @Test   //test interactive mode
    public void testMain01() throws Exception {
        Menu.finished = true;
        Main.main(new String[]{});
    }
    @Test   //test batch mode
    public void testMain02() throws Exception {
        exit.expectSystemExitWithStatus(0);
        Main.main(new String[]{"correctInput.txt"});
    }

    @Test   //test wrong input
    public void testMain03() throws Exception {
        exit.expectSystemExitWithStatus(0);
        Main.main(new String[]{"/test/db/input01.txt", "interactive"});
        assertEquals("Usage:\n" +
                "(1)batch mode: java -jar POS.jar input.txt  OR \n" +
                "(2)interactive mode: java -jar POS.jar", errContent.toString());
    }

    @Test
    public void testUsage() {
        Main.usage();
        assertEquals("Usage:\n" +
                "(1)batch mode: java -jar POS.jar input.txt  OR \n" +
                "(2)interactive mode: java -jar POS.jar\n\n", outContent.toString());
    }
}

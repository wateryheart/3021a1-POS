package POS;

import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by wateryheart on 11/Mar/2014.
 * @author MAR Chun Sum Connie (20057384)
 */
public class LogTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    Log logging = new POS.Log();

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
    public void testLog01() throws Exception {
        logging.clearTempLog();
        logging.log("testing", true);
        assertEquals("testing\n", outContent.toString());
        assertEquals("testing", Log.tempLog.get(0));
    }

    @Test
    public void testLog02() throws Exception {
        logging.clearTempLog();
        logging.log("test again", false);
        assertEquals("", outContent.toString());
        assertEquals("test again", Log.tempLog.get(0));
    }

    @Test()//expected = IOException.class)
    public void testFinalLog01() throws Exception {
        logging.clearTempLog();
        logging.log("test",false);
        assertFalse(Log.tempLog.isEmpty());
        File finalLog = new File(System.getProperty("user.dir") +"/logAndSales.txt");
        if (finalLog.exists())
            finalLog.delete();
        logging.finalLog();
        Scanner log = new Scanner(finalLog);
        assertEquals("test", log.nextLine());
        assertFalse(log.hasNext());
        assertTrue(Log.tempLog.isEmpty());
        logging.clearTempLog();
        finalLog.delete();
    }

    @Test(expected = RuntimeException.class)
    public void testFinalLog02() throws Exception {
        Log mockLogging = mock(POS.Log.class);
        doThrow(new RuntimeException()).when(mockLogging).finalLog();
        mockLogging.finalLog();
    }

    @Test
    public void testClearTempLog() throws Exception {
        logging.clearTempLog();
        assertTrue(Log.tempLog.isEmpty());
    }
}

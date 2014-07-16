package POS;

import org.junit.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

/**
 * Created by wateryheart on 28/Mar/2014.
 * @author MAR Chun Sum Connie (20057384)
 */
public class MenuTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

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
    public void testInteractiveMenuConstructor01(){

        try{
            Thread thread = new Thread(){
                public void run(){
                    Menu.finished = false;
                    systemInMock.provideText("cs\nchunlin\ncs\nchunlin\n3\n1\nid001\nID001\nd\n3\nID002\n9999\n2\nc\n1\nd\n250\n1\nc\n0\n1\nid003\nc\n1\nID004\n2\nc\ng\n0\n2\n");
                    new Menu();
                }
            };
            thread.start();
            thread.join(2000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testBatchMenuConstructor01(){
        new Menu("/test/db/","correctInput.txt");
    }

    @Test
    public void testBatchMenuConstructor02()throws Exception{
        exit.expectSystemExitWithStatus(0);
        new Menu("/test/db/","wrongMenu.txt");
    }

    @Test
    public void testBatchMenuConstructor03()throws Exception{
        new Menu("/test/db/","inputLost.txt");
    }

    @Test
    public void testBatchMenuConstructor04()throws Exception{
        exit.expectSystemExitWithStatus(0);
        new Menu("/test/db/","wrongName.txt");
    }

    @Test
    public void testBatchMenuConstructor05()throws Exception{
        exit.expectSystemExitWithStatus(0);
        new Menu("/test/db/","wrongPassword.txt");
    }

    @Test
    public void testBatchMenuConstructor06()throws Exception{
        exit.expectSystemExitWithStatus(0);
        new Menu("/test/db/","wrongProductId.txt");
    }

    @Test
    public void testBatchMenuConstructor07()throws Exception{
        exit.expectSystemExitWithStatus(0);
        new Menu("/test/db/","wrongProductNo.txt");
    }

    @Test
    public void testBatchMenuConstructor08()throws Exception{
        exit.expectSystemExitWithStatus(0);
        new Menu("/test/db/","notEnoughProduct.txt");
    }

    @Test
    public void testBatchMenuConstructor09()throws Exception{
        exit.expectSystemExitWithStatus(0);
        new Menu("/test/db/","wrongCash.txt");
    }

    @Test
    public void testBatchMenuConstructor10()throws Exception{
        exit.expectSystemExitWithStatus(0);
        new Menu("/test/db/","notEnoughCash.txt");
    }

    @Test
    public void testBatchMenuConstructor11()throws Exception{
        new Menu("/test/db/","cancelSales.txt");
    }

}

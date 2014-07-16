package Data;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

/**
 * Created by wateryheart on 29/Mar/2014.
 * @author MAR Chun Sum Connie (20057384)
 */
public class ProductStoreTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    ProductStore mockProductStore = mock(ProductStore.class);
    ProductStore mockProductStore2 = new ProductStore();

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

    @Test   //wrong product file
    public void testCheckImportFile01() throws Exception {
        systemInMock.provideText("\n");
        mockProductStore2.checkImportFile("/wrongProductListFile.txt");
    }

    @Test   //correct product file
    public void testCheckImportFile02() throws Exception {
        systemInMock.provideText("\n");
        mockProductStore2.checkImportFile("/test/db/productListFile.txt");
    }

    @Test
    public void testWrongImportFile() throws Exception {
        systemInMock.provideText("\n");
        File file = mockProductStore2.wrongImportFile();
        assertEquals("<LOG> /productListFile.txt does not exist.\n"+
                "Please put product details in a productListFile.txt file under the directory."+
                "\nPress Enter to continue.\n", outContent.toString());
        assertEquals(file,new File(System.getProperty("user.dir") + "/productListFile.txt"));
    }

    @Test   //import correct product file
    public void testImportFile01() throws Exception {
        mockProductStore2.importFile(("/test/db/productListFile.txt"));
    }

    @Test   (expected = Exception.class) //import product file exception
    public void testImportFile02() throws Exception {
        doThrow(new Exception()).when(mockProductStore).importFile("mockProductFile.txt");
        mockProductStore.importFile("mockProductFile.txt");
    }

    @Test
    public void testProductExist01() throws Exception {
        mockProductStore2.checkImportFile("/test/db/productListFile.txt");
        mockProductStore2.productExist("ID003");
    }

    @Test
    public void testProductExist02() throws Exception {
        mockProductStore2.checkImportFile("/test/db/productListFile.txt");
        mockProductStore2.productExist("id003");
    }
}

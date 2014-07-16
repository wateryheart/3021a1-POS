package Sales;

import Data.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

/**
 * Created by wateryheart on 11/Apr/2014.
 * @author MAR Chun Sum Connie (20057384)
 */
public class InteractiveSaleTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    Scanner scan;
    InteractiveSale interactiveSale;
    Product product;
    List<SaleItem> saleItems;

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        product = new Product("ID000","name",7.5f,999);
        saleItems = new ArrayList<SaleItem>();
        scan = new Scanner(System.in);
        systemInMock.provideText("ID004\n2\nc\n0\n");
        interactiveSale = new InteractiveSale(0,scan);
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
    public void testIsInteger01() throws Exception {
        assertTrue(interactiveSale.isInteger("9"));
    }

    @Test
    public void testIsInteger02() throws Exception {
        assertFalse(interactiveSale.isInteger("nine"));
    }

    @Test
    public void testIsFloat01() throws Exception {
        assertTrue(interactiveSale.isFloat("9.9"));
    }

    @Test
    public void testIsFloat02() throws Exception {
        assertFalse(interactiveSale.isFloat("nine"));
    }

    @Test
    public void testCheckPurchaseNoCorrection() throws Exception {

        assertEquals(interactiveSale.checkPurchaseNoCorrection(scan, "8", product), 8);
    }

    @Test
    public void testGetPurchasedNo() throws Exception {
        outContent.reset();
        systemInMock.provideText("1\n");
        interactiveSale.getPurchasedNo(scan,product,saleItems);
        assertEquals("Product name is name, purchased number: ",outContent.toString());

    }

    @Test
    public void testPurchaseAnItem() throws Exception {
        interactiveSale.purchaseAnItem(scan,"c",saleItems);
    }

    @Test
    public void testCharge() throws Exception {
        saleItems.add(new SaleItem(product, 1));
        assertTrue(interactiveSale.charge(saleItems) == 7.5f);
    }

    @Test
    public void testSaleDetail() throws Exception {
        saleItems.add(new SaleItem(product, 1));
        assertEquals(interactiveSale.saleDetail(saleItems), "name 1");
    }

    @Test
    public void testCheckChangeNoCorrection() throws Exception {
        assertTrue(interactiveSale.checkChangeNoCorrection(scan, "10", 7.5f) == 10f);
    }

    @Test
    public void testChange() throws Exception {
        assertEquals(interactiveSale.change(10.0f, 10.0f), "0.0");
    }

    @Test
    public void testCancelSale01() throws Exception {
        assertTrue(interactiveSale.cancelSale(0,0f));
    }

    @Test
    public void testCancelSale02() throws Exception {
        assertFalse(interactiveSale.cancelSale(0, 10f));
    }

    @Test
    public void testSumUpSales() throws Exception {
        outContent.reset();
        systemInMock.provideText("10\n");
        interactiveSale.sumUpSales(scan, saleItems, 0);
        assertEquals("--------------------------------------------------------\n" +
                "Purchasing-product list: \n\nTotal price is $0.0\n" +
                "Please pay with cash, received cash (or cancel by entering '0')$: "
                +"Change $: 10.0\n",outContent.toString());
    }
}

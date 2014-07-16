package Sales;

import Data.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by wateryheart on 29/Mar/2014.
 * @author MAR Chun Sum Connie (20057384)
 */
public class BatchSaleTest{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    Scanner scan;
    BatchSale batchSale;
    Product product;
    List<SaleItem> saleItems;
    File batchFile;
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        product = new Product("ID003","name",7.5f,999);
        saleItems = new ArrayList<SaleItem>();
        batchFile = new File(System.getProperty("user.dir") +"/test/db/batchSaleTest.txt");
        scan = new Scanner(batchFile);
        batchSale = new BatchSale(0,scan);

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
    public void testBatchError() throws Exception {
        exit.expectSystemExitWithStatus(0);
        batchSale.batchError();
    }

    @Test
    public void testIsInteger01() throws Exception {
        assertTrue(batchSale.isInteger("9"));
    }

    @Test
    public void testIsInteger02() throws Exception {
        assertFalse(batchSale.isInteger("nine"));
    }

    @Test
    public void testIsFloat01() throws Exception {
        assertTrue(batchSale.isFloat("9.9"));
    }

    @Test
    public void testIsFloat02() throws Exception {
        assertFalse(batchSale.isFloat("nine"));
    }

    @Test
    public void testCheckPurchaseNoCorrection() throws Exception {
        assertEquals(batchSale.checkPurchaseNoCorrection(scan, "8", product), 8);
    }

    @Test
    public void testGetPurchasedNo() throws Exception {
        outContent.reset();
        batchSale.getPurchasedNo(scan,product,saleItems);
        assertEquals("Product name is name, purchased number: 8\n",outContent.toString());
    }

    @Test
    public void testPurchaseAnItem() throws Exception {
        batchSale.purchaseAnItem(scan,"c",saleItems);
    }

    @Test
    public void testCharge() throws Exception {
        saleItems.add(new SaleItem(product, 1));
        assertTrue(batchSale.charge(saleItems) == 7.5f);
    }

    @Test
    public void testSaleDetail() throws Exception {
        saleItems.add(new SaleItem(product, 1));
        assertEquals(batchSale.saleDetail(saleItems), "name 1");
    }

    @Test
    public void testCheckChangeNoCorrection() throws Exception {
        assertTrue(batchSale.checkChangeNoCorrection(scan, "10", 7.5f) == 10f);
    }

    @Test
    public void testChange() throws Exception {
        assertEquals(batchSale.change(10.0f, 10.0f), "0.0");
    }

    @Test
    public void testCancelSale01() throws Exception {
        assertTrue(batchSale.cancelSale(0,0f));
    }

    @Test
    public void testCancelSale02() throws Exception {
        assertFalse(batchSale.cancelSale(0, 10f));
    }

    @Test
    public void testSumUpSales() throws Exception{
        outContent.reset();
        batchSale.sumUpSales(scan, saleItems, 0);
        assertEquals("--------------------------------------------------------\n" +
                "Purchasing-product list: \n" +
                "\nTotal price is $0.0\n" +
                "Please pay with cash, received cash (or cancel by entering '0')$: 8\n" +
                "Change $: 8.0\n",outContent.toString());
    }
}

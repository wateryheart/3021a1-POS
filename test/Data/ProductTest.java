package Data;

import org.junit.Test;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by wateryheart on 29/Mar/2014.
 * @author MAR Chun Sum Connie (20057384)
 */
public class ProductTest {
    Product p1 = new Product("ID001","mouse",35.0f,99);
    Product p2 = new Product("","",0.0f,0);

    @Test
    public void testSetProductID() throws Exception {
        p2.setProductID("ID002");
        assertEquals(p2.getProductID(), "ID002");
    }

    @Test
    public void testGetProductID() throws Exception {
        assertEquals(p1.getProductID(),"ID001");
    }

    @Test
    public void testSetProductName() throws Exception {
        p2.setProductName("Camera");
        assertEquals(p2.getProductName(),"Camera");
    }

    @Test
    public void testGetProductName() throws Exception {
        assertEquals(p1.getProductName(),"mouse");
    }

    @Test
    public void testSetStock() throws Exception {
        p2.setStock(999);
        assertTrue(p2.getStock() == 999);
    }

    @Test
    public void testGetStock() throws Exception {
        assertSame(p1.getStock(),99);
    }

    @Test
    public void testSetUnitPrice() throws Exception {
        p2.setUnitPrice(600.4f);
        assertTrue(p2.getUnitPrice() == 600.4f);
    }

    @Test
    public void testGetUnitPrice() throws Exception {
        assertTrue(p1.getUnitPrice()==35.0f);
    }
}

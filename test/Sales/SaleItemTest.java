package Sales;

import Data.Product;
import Sales.SaleItem;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wateryheart on 13/Mar/2014.
 * @author MAR Chun Sum Connie (20057384)
 */
public class SaleItemTest {
    Product p1 = new Product("ID001","mouse",35.0f,99);
    Product p2 = new Product("ID002","keyboard",100.0f,99);
    SaleItem s = new SaleItem(p1,1);

    @Test
    public void testSetItem() throws Exception {
        s.setItem(p2);
        assertSame(s.getItem(), p2);
    }

    @Test
    public void testGetItem() throws Exception {
        SaleItem s1 = new SaleItem(p1,2);
        assertSame(s1.getItem(), p1);
    }

    @Test
    public void testSetAmount() throws Exception {
        s.setAmount(3);
        assertSame(s.getAmount(), 3);
    }

    @Test
    public void testGetAmount() throws Exception {
        SaleItem s1 = new SaleItem(p1,4);
        assertSame(s1.getAmount(), 4);
    }
}

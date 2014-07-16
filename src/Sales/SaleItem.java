package Sales;

import Data.Product;

/**
 * Created by wateryheart on 02/Mar/2014.
 * @author MAR Chun Sum Connie (20057384)
 */
public class SaleItem {
    private Product item;
    private Integer amount;


    public SaleItem(Product purchasedItem, Integer purchasedAmount){
        this.setItem(purchasedItem);
        this.setAmount(purchasedAmount);
    }

    public void setItem(Product item) {
        this.item = item;
    }
    public Product getItem() {
        return item;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public Integer getAmount() {
        return amount;
    }


}

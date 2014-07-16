package Data;
/**
 * Created by wateryheart on 20/Feb/2014.
 * Store product details such as ID, name, unit price and stock.
 * @author MAR Chun Sum Connie (20057384)

 */

public class Product {
    private String productID;
    private String productName;
    private float unitPrice;
    private Integer stock;

    /**constructor*/
    public Product(String newProductID, String newProductName, float newUnitPrice,Integer newStock){
        this.setProductID(newProductID);
        this.setProductName(newProductName);
        this.setUnitPrice(newUnitPrice);
        this.setStock(newStock);
    }

    //getter and setter
    public void setProductID(String newProductID) {
        this.productID = newProductID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductName(String newProductName) {
        this.productName = newProductName;
    }

    public String getProductName(){
        return productName;
    }

    public void setStock(Integer newStock) {
        this.stock = newStock;
    }

    public Integer getStock() {
        return stock;
    }

    public void setUnitPrice(float newUnitPrice) {
        this.unitPrice = newUnitPrice;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

}

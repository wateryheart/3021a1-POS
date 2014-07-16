package Sales;

import Data.Product;
import java.util.List;
import java.util.Scanner;

/**
 * Created by wateryheart on 27/Mar/2014.
 * Interface for two mode sales
 * @author MAR Chun Sum Connie (20057384)
 */
public interface Sale {

    /**check if the input is an integer*/
    boolean isInteger(String s);
    /** check if input is a float*/
    boolean isFloat(String s);
    /** check if the amount user want to purchase is a correct integer number*/
    int checkPurchaseNoCorrection(Scanner scan, String purchasedNumber, Product currentProduct);
    /** get user input of amount they want to purchase*/
    void getPurchasedNo(Scanner scan, Product currentProduct,List<SaleItem> saleItems);
    /** get the priduct id from user*/
    void purchaseAnItem(Scanner scan, String productChoice, List<SaleItem> saleItems);
    /**calculate the total charge of the purchase*/
    float charge(List<SaleItem> saleItems);
    /**prepare sales detail for logging*/
    String saleDetail(List<SaleItem> saleItems);
    /** check if user enter a correct cash number*/
    float checkChangeNoCorrection(Scanner scan, String cash, float total);
    /** calculate the amount of change*/
    String change(Float cashGiven, float total);
    /** check if the sale is cancel*/
    boolean cancelSale(int salesNo, Float cashGiven);
    /** sum up the sales from the purchase list*/
    void sumUpSales(Scanner scan, List<SaleItem> saleItems,int salesNo);
}

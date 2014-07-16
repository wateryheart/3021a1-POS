package Sales;

import Data.Product;
import Data.ProductStore;
import POS.Main;
import POS.Menu;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by wateryheart on 28/Mar/2014.
 * interactive sale recorder
 * @author MAR Chun Sum Connie (20057384)
 */
public class InteractiveSale implements Sale {
    private DecimalFormat priceForm = new DecimalFormat("0.0");
    /**constructor. get all product id input and record them. press c to quit it.*/
    public InteractiveSale(int salesNo, Scanner scan){
        List<SaleItem> saleItems = new ArrayList<SaleItem>();

        System.out.println("Please enter a list of purchasing-product ID and number");
        System.out.println("--------------------------------------------------------");
        System.out.print("Please enter product ID or press 'c' to end the list: ");
        String productChoice = scan.nextLine();
        purchaseAnItem(scan, productChoice, saleItems);

        // user enter 'c' to sum up saleItems
//        if(saleItems.size()!=0)
//        {
        sumUpSales(scan, saleItems,salesNo);
//        }
//        else
//        {
//            Menu.logging.log("<LOG> Nothing purchased!", true);
//        }

        saleItems.clear();
    }
    /**check if the input is an integer*/
    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
    /** check if input is a float*/
    public boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
    /** check if the amount user want to purchase is a correct integer number*/
    public int checkPurchaseNoCorrection(Scanner scan, String purchasedNumber, Product currentProduct){
        while(!isInteger(purchasedNumber) || currentProduct.getStock() < Integer.parseInt(purchasedNumber))
        {
            if(!isInteger(purchasedNumber))
            {
                Menu.logging.log("<LOG> Incorrect Number!",true);
            }
            else
            {
                Menu.logging.log("<LOG> Not enough stock! Only have "+currentProduct.getStock()+" left.",true);
            }
            System.out.print("Product name is " + currentProduct.getProductName() + ", purchased number: ");
            purchasedNumber = scan.nextLine();
        }
        return Integer.parseInt(purchasedNumber);
    }
    /** get user input of amount they want to purchase*/
    public void getPurchasedNo(Scanner scan, Product currentProduct,List<SaleItem> saleItems){
        System.out.print("Product name is "+currentProduct.getProductName()+", purchased number: ");
        String purchasedNumber = scan.nextLine();
        Integer no = checkPurchaseNoCorrection(scan,purchasedNumber, currentProduct);
        saleItems.add(new SaleItem(currentProduct, no));
    }
    /** get the priduct id from user*/
    public void purchaseAnItem(Scanner scan, String productChoice, List<SaleItem> saleItems) {
        while(!productChoice.equals("c"))
        {
            Product currentProduct = Main.productStore.productExist(productChoice);
            while (currentProduct == null)
            {
                Menu.logging.log("<LOG> Incorrect product ID!",true);
                System.out.print("Please enter product ID or press 'c' to end the list: ");
                productChoice = scan.nextLine();
                if (productChoice.equals("c"))
                {
                    break;
                }
                currentProduct = Main.productStore.productExist(productChoice);
            }
            if (productChoice.equals("c"))
            {
                break;
            }
            getPurchasedNo(scan, currentProduct,saleItems);
            System.out.print("Please enter product ID or press 'c' to end the list: ");
            productChoice = scan.nextLine();
        }
    }
    /**calculate the total charge of the purchase*/
    public float charge(List<SaleItem> saleItems){
        float total = 0;
        if(saleItems.size()!=0)
        {
            for (SaleItem s:saleItems)
            {
                int no = s.getAmount();
                float subTotal = no*s.getItem().getUnitPrice();
                total = total + subTotal;
                System.out.println(s.getItem().getProductName() + " * " + no + " = $" + subTotal);
            }
        }
        return total;
    }
    /**prepare sales detail for logging*/
    public String saleDetail(List<SaleItem> saleItems){
        String detail = "";
        if(saleItems.size()!=0)
        {
            for (SaleItem s:saleItems)
            {
                if (detail.equals(""))
                {
                    detail = s.getItem().getProductName()+" "+s.getAmount();
                }
                else
                {
                    detail = detail+"; "+s.getItem().getProductName()+" "+s.getAmount();
                }
            }
        }
        return detail;
    }
    /** check if user enter a correct cash number*/
    public float checkChangeNoCorrection(Scanner scan, String cash, float total){

        while(!isFloat(cash) || (Float.parseFloat(cash) < total && !cash.equals("0")) )
        {
            if(!isFloat(cash) )
            {
                Menu.logging.log("<LOG> Incorrect Number!",true);
                System.out.print("Please pay with cash, received cash (or cancel by entering '0')$: ");

            }
            else
            {
                Menu.logging.log("<LOG> Cash not enough!",false);
                System.out.print("Sorry, cash not enough, please enter cash amount again: ");
            }
            cash = scan.nextLine();
        }

        return Float.parseFloat(cash);
    }
    /** calculate the amount of change*/
    public String change(Float cashGiven, float total){
        return priceForm.format(cashGiven - total);
    }
    /** check if the sale is cancel*/
    public boolean cancelSale(int salesNo, Float cashGiven){
        boolean canceledSale = false;
        if (cashGiven == 0)
        {
            Menu.logging.log("<LOG> SalesID" + String.format("%02d", salesNo) + " cancelled!", true);
            canceledSale = true;
        }
        return canceledSale;
    }
    /** sum up the sales from the purchase list*/
    public void sumUpSales(Scanner scan, List<SaleItem> saleItems,int salesNo){
        System.out.println("--------------------------------------------------------");
        System.out.println("Purchasing-product list: ");
        float total = charge(saleItems);
        String detail = saleDetail(saleItems);
        System.out.println("\nTotal price is $" + priceForm.format(total));
        System.out.print("Please pay with cash, received cash (or cancel by entering '0')$: ");
        String cash = scan.nextLine();
        Float cashGiven = checkChangeNoCorrection(scan, cash,total);
        if (!cancelSale(salesNo, cashGiven))
        {
            System.out.println("Change $: " + change(cashGiven,total));
            Menu.logging.log("<LOG> SalesID"+String.format("%02d", salesNo)+"\t total $"+priceForm.format(total)+"; "+detail,false);
        }
        Menu.salesNo++;
        Menu.logging.finalLog();
    }
}

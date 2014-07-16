package Data;

import POS.Menu;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by wateryheart on 28/Mar/2014.
 * Get all kinds of products from productListFile.txt
 * @author MAR Chun Sum Connie (20057384)
 */
public class ProductStore implements ResourceFile{
    public static final List<Product> pList = new ArrayList<Product>();

    public ProductStore(){
        checkImportFile("/productListFile.txt");
    }

    /**check product file exist*/
    public void checkImportFile(String FileName){
        File productFile = new File(System.getProperty("user.dir") + FileName);
        while (!productFile.exists()){
            productFile = wrongImportFile();
        }
        importFile("/productListFile.txt");
    }

    /**handle wrong product file*/
    public File wrongImportFile(){
        Menu.logging.log("<LOG> /productListFile.txt does not exist.\nPlease put product details in a productListFile.txt file under the directory.\nPress Enter to continue.",true);
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
        return new File(System.getProperty("user.dir") + "/productListFile.txt");
    }

    /**read product file*/
    public void importFile(String FileName){
        try {
            File productFile = new File(System.getProperty("user.dir") + FileName);
            Scanner scan = new Scanner(productFile);
            while (scan.hasNext())   {
                //TODO:check type
                String id = scan.next();
                String name = scan.next();
                float unitPrice = scan.nextFloat();
                int stock = scan.nextInt();
                Product p = new Product(id,name,unitPrice,stock);
                pList.add(p);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**check product exist in the store or not*/
    public Product productExist(String Id){
        for(Product p: pList){
            if(p.getProductID().equals(Id))
            {
                return p;
            }
        }
        return null;
    }

//
//    //check amount of stock is enough
//    public boolean enoughStock(Product thisProduct,Integer enteredStock){
//        if(thisProduct.getStock()<enteredStock)
//            return true;
//        else
//            return false;
//    }
}

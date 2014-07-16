package POS;

import Data.ProductStore;
import Data.UserStore;

import java.io.File;

/**
 * Created by wateryheart on 13/Mar/2014.
 * Main starter of the POS program
 * @author MAR Chun Sum Connie (20057384)
 */
public class Main {
    public static final UserStore userStore = new UserStore();
    public static final ProductStore productStore = new ProductStore();
    /**provide two ways of running the program, interactive mode and batch mode. If user input wrong command, tell them the correct usage and quit the program.*/
    public static void main(String[] args){
        usage();
        while (args.length>=2)
        {
            System.err.println("Usage:\n(1)batch mode: java -jar POS.jar input.txt  OR \n(2)interactive mode: java -jar POS.jar");
            System.exit(0);
        }

        if (args.length == 1) //batch mode
        {
            new Menu("/",args[0]);
        }
        else  //interactive mode
        {
            new Menu();
        }
    }

    /**tell user the usage of the program*/
    public static void usage(){
        System.out.println("Usage:\n" +
                "(1)batch mode: java -jar POS.jar input.txt  OR \n" +
                "(2)interactive mode: java -jar POS.jar\n");
    }
}

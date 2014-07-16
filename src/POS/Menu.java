package POS;

import Sales.*;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by wateryheart on 18/Feb/2014.
 * Main menu of the program
 * @author MAR Chun Sum Connie (20057384)
 */

public class Menu {
    public static final POS.Log logging = new POS.Log();
    public static int salesNo = 0;
    private static boolean loggedIn = false;
    public static boolean finished = false;

    /**batch mode menu, let user choose login/log out or record sales*/
    public Menu(String dir,String input){
        File batchFile = new File(System.getProperty("user.dir") + dir +input);
        if (batchFile.exists())
        {
            try {
                Scanner scan = new Scanner(batchFile);
                while(scan.hasNextLine())
                {
                    System.out.println("Welcome to the Point-Of_Sale Registration System");
                    BatchSessionUser sessionUser = new BatchSessionUser();
                    loggedIn = sessionUser.login(scan);
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Welcome to the Electronic-Sales Counter!");
                    while (loggedIn)
                    {
                        String choice = scan.nextLine();
                        System.out.println("Please enter '1' to record sales or '2' to exit: "+choice);
                        if (!choice.equals("1") && !choice.equals("2"))
                        {
                            logging.log("<LOG> Invalid command",true);
                            logging.finalLog();
                            System.exit(0);
                        }
                        else if (choice.equals("2"))
                        {
                            loggedIn = sessionUser.logOut();
                        }
                        else //choice.equals("1");
                        {
                            new BatchSale(salesNo,scan);
                        }
                    }
                }
                scan.close();
            }catch (NoSuchElementException e){
                logging.log("System end accidentally due to the end of the file.",true);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getMessage());
            }
        }
        else
        {
            logging.log("<LOG> The file " + input + " does not exist!",true);
            System.exit(0);
        }
    }

    /**interactive mode menu, let user choose login/log out or record sales*/
    public Menu(){
        System.out.println("Welcome to the Point-Of_Sale Registration System");
        Scanner scan = new Scanner(System.in);
        while(!finished)
        {
            InteractiveSessionUser sessionUser = new InteractiveSessionUser();
            loggedIn = sessionUser.login(scan);
            System.out.println("--------------------------------------------------------");
            System.out.println("Welcome to the Electronic-Sales Counter!");
            while (loggedIn)
            {
                System.out.print("Please enter '1' to record sales or '2' to exit: ");
                String choice = scan.nextLine();
                while (!choice.equals("1") && !choice.equals("2"))
                {
                    logging.log("<LOG> Invalid command",true);
                    System.out.print("Please enter '1' to record sales or '2' to exit: ");
                    choice = scan.nextLine();

                }
                if (choice.equals("2"))
                {
                    loggedIn = sessionUser.logOut();
                    finished = true;
                }
                else //choice.equals("1");
                {
                    new InteractiveSale(salesNo,scan);
                }
            }
        }
        scan.close();
    }
}

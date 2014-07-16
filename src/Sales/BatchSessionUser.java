package Sales;

import Data.User;
import Data.UserStore;
import POS.Main;
import POS.Menu;

import java.util.Scanner;

/**
 * Created by wateryheart on 28/Mar/2014.
 * record batch mode session user
 * @author MAR Chun Sum Connie (20057384)
 */
public class BatchSessionUser implements SessionUser {

    public User currentUser;

    /**for login*/
    public boolean login(Scanner scan){
        currentUser = getInputName(scan);
        getLoginPassword(currentUser, scan);
        Menu.logging.log("Electronic-Sales Counter is started successfully by user "+currentUser.getName(),true);
        Menu.logging.finalLog();
        return true;
    }
    /** get user name from user*/
    public User getInputName(Scanner scan){
        String name = scan.nextLine();
        System.out.println("Please enter your user name: "+name);
        User currentUser = Main.userStore.userExist(name);
        if (currentUser == null)
        {
            Menu.logging.log("<LOG> Non-existent user " + name + ".", true);
            Menu.logging.finalLog();
            System.exit(0);
        }
        return currentUser;
    }
    /** get password from user*/
    public void getLoginPassword(User currentUser,Scanner scan) {
        String password = scan.nextLine();
        System.out.println("Please enter your password: "+password);
        if(!Main.userStore.passwordCorrect(currentUser,password))
        {
            Menu.logging.log("<LOG> Wrong password "+password+" for user "+currentUser.getName()+" ,please enter again!",true);
            Menu.logging.finalLog();
            System.exit(0);
        }
    }
    /**log out*/
    public boolean logOut() {
        Menu.logging.log("<LOG> User " + currentUser.getName() + " has successfully logged off!", true);
        Menu.logging.finalLog();
        currentUser = null;
        return false;
    }
}

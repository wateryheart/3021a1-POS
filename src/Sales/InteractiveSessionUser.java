package Sales;

import Data.User;
import Data.UserStore;
import POS.Main;
import POS.Menu;

import java.util.Scanner;

/**
 * Created by wateryheart on 28/Mar/2014.
 * record interactive mode session user
 * @author MAR Chun Sum Connie (20057384)
 */
public class InteractiveSessionUser implements SessionUser {

    public User currentUser;
    /**for login*/
    public boolean login(Scanner scan){
        currentUser = getInputName(scan);
        getLoginPassword(currentUser,scan);
        Menu.logging.log("Electronic-Sales Counter is started successfully by user "+currentUser.getName(),true);
        Menu.logging.finalLog();
        return true;
    }
    /** get user name from user*/
    public User getInputName(Scanner scan){
        String name;
        User currentUser;
        do {
            System.out.print("Please enter your user name: ");
            name = scan.nextLine();
            currentUser = Main.userStore.userExist(name);
            if (currentUser == null)
                Menu.logging.log("<LOG> Non-existent user " + name + ", Please enter again!", true);
        }while(currentUser == null);
        return currentUser;
    }
    /** get password from user*/
    public void getLoginPassword(User currentUser,Scanner scan) {
        String password;
        do{
            System.out.print("Please enter your password: ");
            password = scan.nextLine();
            if(!Main.userStore.passwordCorrect(currentUser,password))
                Menu.logging.log("<LOG> Wrong password "+password+" for user "+currentUser.getName()+" ,please enter again!",true);
        }while(!Main.userStore.passwordCorrect(currentUser,password));
    }
    /**log out*/
    public boolean logOut() {
        Menu.logging.log("<LOG> User " + currentUser.getName() + " has successfully logged off!", true);
        Menu.logging.finalLog();
        currentUser = null;
        return false;
    }
}

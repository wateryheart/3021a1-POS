package Sales;

import Data.User;

import java.util.Scanner;

/**
 * Created by wateryheart on 27/Mar/2014.
 * Interface of session user
 * @author MAR Chun Sum Connie (20057384)
 */
public interface SessionUser{

    /**for login*/
    boolean login(Scanner scan);
    /** get user name from user*/
    User getInputName(Scanner scan);
    /** get password from user*/
    void getLoginPassword(User currentUser,Scanner scan);
    /**log out*/
    boolean logOut();
}

package Data;

import POS.Menu;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by wateryheart on 28/Mar/2014.
 * Get all user from userPasswordFile.txt
 * @author MAR Chun Sum Connie (20057384)
 */

public class UserStore implements ResourceFile {
    public static final List<User> uList= new ArrayList<User>();

    public UserStore(){
        checkImportFile("/userPasswordFile.txt");
    }

    /**check user file*/
    public void checkImportFile(String fileName){
        File userFile = new File(System.getProperty("user.dir") + fileName);
        while (!userFile.exists()){
            userFile = wrongImportFile();
        }
        importFile("/userPasswordFile.txt");
    }

    /**handle wrong user file*/
    public File wrongImportFile(){
        Menu.logging.log("<LOG> /userPasswordFile.txt does not exist.\nPlease put user name and password in a userPasswordFile.txt file under the directory.\nPress Enter to continue.",true);
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
        return new File(System.getProperty("user.dir") + "/userPasswordFile.txt");
    }

    /**read user file*/
    public void importFile(String fileName){
        try{
            File userFile = new File(System.getProperty("user.dir") + fileName);
            Scanner scan = new Scanner(userFile);
            while (scan.hasNext())   {
                String name = scan.next();
                String password = scan.next();
                User u = new User(name,password);
                uList.add(u);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**check user name exist in user store*/
    public User userExist(String userName){
        for(User u: uList){
            if(u.getName().equals(userName))
            {
                return u;
            }
        }
        return null;
    }

    /**check user Password correct*/
    public boolean passwordCorrect(User thisUser, String enteredPassword){
        if(thisUser.getPassword().equals(enteredPassword))
            return true;
        else
            return false;
    }
}

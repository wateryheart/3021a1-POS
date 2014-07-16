package Data;


/**
 * Created by wateryheart on 20/Feb/2014.
 * Store user data such as name and password
 * @author MAR Chun Sum Connie (20057384)
 */
public class User {
    private String name;
    private String password;

    /**constructor*/
    public User(String newName, String newPassword){
        this.setName(newName);
        this.setPassword(newPassword);
    }

    //getter and setter
    public void setName(String newName){
        this.name = newName;
    }

    public String getName(){
        return name;
    }

    public void setPassword(String newPassword){
        this.password = newPassword;
    }

    public String getPassword(){
        return password;
    }

}

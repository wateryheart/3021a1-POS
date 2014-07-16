package POS;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wateryheart on 25/Feb/2014.
 * Record all event loggings
 * @author MAR Chun Sum Connie (20057384)
 */
public class Log {
    /** temporary store the log information before the sale finish*/
    public static List<String> tempLog = new ArrayList<String>();
    /**constructor*/
    public void log(String message, boolean show){
        tempLog.add(message);
        if(show)
            System.out.println(message);
    }

    /** put logging into the logAndSales.txt*/
    public void finalLog() {
        try{
            File finalLog = new File(System.getProperty("user.dir") +"/logAndSales.txt");
            if(!finalLog.exists())
                finalLog.createNewFile();
            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(finalLog, true)));
            for(String s :tempLog)
            {
                output.println(s);
            }
            output.close();
            clearTempLog();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /** clear the temporary logs*/
    public void clearTempLog(){
        tempLog.clear();
    }
}

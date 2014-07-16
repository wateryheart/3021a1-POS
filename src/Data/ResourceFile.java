package Data;

import java.io.*;

/**
 * Created by wateryheart on 21/Feb/2014.
 * Interface for both product and user stores storing data.
 * @author MAR Chun Sum Connie (20057384)
 */
public interface ResourceFile {

    /**check file exist*/
    void checkImportFile(String FileName);

    /**handle wrong file*/
    File wrongImportFile();

    /**read file*/
    void importFile(String FileName);
}

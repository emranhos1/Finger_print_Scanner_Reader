/**
 *
 * @author Md. Emran Hossain
 * @email: emranhos1@gmail.com
 */
package classes;

import classes.findFile.AllPath;
import classes.findFile.ReadXMLForPath;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileCopyDelete {

    private static PrintWriter writer;
    private static BufferedReader reader;
    private static String oldFile;
    private static String newFile;
    private static AllPath rxfp;
    private static String uploadFilePath;
    private static String allData;

    public static boolean MoveDelete(String fileName) throws IOException {

        rxfp = ReadXMLForPath.ReadXMLForPath();
        uploadFilePath = rxfp.getUploadFilePath();
        allData = rxfp.getAllDataPath();

        oldFile = allData + fileName;
        newFile = uploadFilePath + "\\" + fileName + "";
        try {
            Files.move(Paths.get(oldFile), Paths.get(newFile), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(FileCopyDelete.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}

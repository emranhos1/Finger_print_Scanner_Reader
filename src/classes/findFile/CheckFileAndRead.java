/**
 *
 * @author Md. Emran Hossain
 * @email: emranhos1@gmail.com
 */
package classes.findFile;

import java.io.File;
import java.io.FileFilter;
import java.util.Scanner;

public class CheckFileAndRead {

    private static Scanner scanner;
    private static String USERID;
    private static String CHECKTIME, CHECKTIME1, CHECKTIME2;
    private static String CHECKTYPE;
    private static String VERIFYCODE;
    private static String SENSORID;
    private static String Memoinfo;
    private static String WorkCode;
    private static String sn;
    private static String UserExtFmt;
    private static File dir;
    private static File[] files;
    private static long lastMod;
    private static File choice;
    private static String fileName;

//    public static void main(String[] args) {
//        lastFileModified();
//    }
    
    public static boolean isEmpty() {

        File file = new File("E:\\Programming\\1. Office project\\Project\\Desktop base\\uploaded file");
        if (file.isDirectory()) {
            if (file.list().length > 0) {
                System.out.println("There is files");
                return false;
            } else {
                System.out.println("There is no files");
                return true;
            }
        } else {
            System.out.println("There is no Directory");
            return true;
        }
    }

    public static String lastFileModified() {
        dir = new File("E:\\Programming\\1. Office project\\Project\\Desktop base\\uploaded file");
        files = dir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.isFile();
            }
        });
        lastMod = Long.MIN_VALUE;
        choice = null;
        for (File file : files) {
            if (file.lastModified() > lastMod) {
                choice = file;
                lastMod = file.lastModified();
                fileName = choice.getName();
            }
        }
        return fileName;
    }

    public static void openFile(String fileName) {

        try {
            scanner = new Scanner(new File("E:\\Programming\\1. Office project\\Project\\Desktop base\\uploaded file\\" + fileName));
        } catch (Exception e) {
            System.out.println("Could not find file");
            e.printStackTrace();
        }
    }

    public static String readFile() {

        while (scanner.hasNext()) {
            USERID = scanner.next();
            CHECKTIME1 = scanner.next();
            CHECKTIME2 = scanner.next();
            CHECKTYPE = scanner.next();
            VERIFYCODE = scanner.next();
            SENSORID = scanner.next();
            Memoinfo = scanner.next();
            WorkCode = scanner.next();
            sn = scanner.next();
            UserExtFmt = scanner.next();
        }
        CHECKTIME = CHECKTIME1 + " " + CHECKTIME2;
        return CHECKTIME;
    }

    public static void closeFile() {
        scanner.close();
    }
}

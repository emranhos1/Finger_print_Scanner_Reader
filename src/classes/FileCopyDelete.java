/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PLAYBOY
 */
public class FileCopyDelete {

    private static PrintWriter writer;
    private static BufferedReader reader;
    private static String oldFile;
    private static String newFile;

    public static boolean MoveDelete(String fileName) {
        oldFile = "E:\\Programming\\1. Office project\\Project\\Desktop base\\allData\\" + fileName + "";
        newFile = "E:\\Programming\\1. Office project\\Project\\Desktop base\\uploaded file\\" + fileName + "";
        try {
            Files.move(Paths.get(oldFile), Paths.get(newFile), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(FileCopyDelete.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}

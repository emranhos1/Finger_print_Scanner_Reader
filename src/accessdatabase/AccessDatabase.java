package accessdatabase;

import classes.FileCopyDelete;
import classes.UploadFileToServer;
import dao.SelectQueryDao;
import dbConnection.conRs;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

public class AccessDatabase {

    private static String columnName;
    private static String tableName;
    private static String CHECKTYPE;
    private static String SENSORID;
    private static String Memoinfo;
    private static String WorkCode;
    private static String sn;
    private static String inputDate;
    private static String whereCondition;
    private static String fileName;
    private static String allData;
    private static String lastDateTime = "";
    private static int USERID, VERIFYCODE, UserExtFmt;
    private static PrintWriter outputStream;
    private static conRs conrs1;
    private static Connection con1;
    private static ResultSet rs1;
    private static PreparedStatement pstm1;
    private static SimpleDateFormat dateFormat;
    private static Date date;
    private static Timestamp CHECKTIME;
    private static FileWriter writer;
    private static BufferedWriter bufferedWriter;
    private static boolean uploaded;
    private static boolean moveDeleteFile;

    public static void main(String[] args) throws SQLException, FileNotFoundException, InterruptedException {

        columnName = " * ";
        tableName = " CHECKINOUT ";
        whereCondition = "CHECKTIME > #" + lastDateTime + "#";

        System.out.println(lastDateTime);
        System.out.println("Select " + columnName + " from " + tableName + " where " + whereCondition);
        try {
            if (lastDateTime.equals("")) {

//                if (Folder.equals("")) {
                conrs1 = SelectQueryDao.selectQueryWithOutWhereClause(columnName, tableName);
//                } else{
//                    
//                }
            } else {
                conrs1 = SelectQueryDao.selectQueryWithWhereClause(columnName, tableName, whereCondition);
            }
            con1 = conrs1.getCon();
            rs1 = conrs1.getRs();
            pstm1 = conrs1.getPstm();

            dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss.S");
            date = new Date();
            inputDate = dateFormat.format(date);

            fileName = inputDate + ".txt";

            writer = new FileWriter("E:\\Programming\\1. Office project\\Project\\Desktop base\\log\\MyLogFile.log", true);
            bufferedWriter = new BufferedWriter(writer);

            if (rs1.isBeforeFirst()) {
                outputStream = new PrintWriter(new FileWriter(new File("E:\\Programming\\1. Office project\\Project\\Desktop base\\allData\\" + fileName), true));
                while (rs1.next()) {
                    USERID = rs1.getInt("USERID");
                    CHECKTIME = rs1.getTimestamp("CHECKTIME");
                    CHECKTYPE = rs1.getString("CHECKTYPE");
                    VERIFYCODE = rs1.getInt("VERIFYCODE");
                    SENSORID = rs1.getString("SENSORID");
                    Memoinfo = rs1.getString("Memoinfo");
                    WorkCode = rs1.getString("WorkCode");
                    sn = rs1.getString("sn");
                    UserExtFmt = rs1.getInt("UserExtFmt");
                    allData = USERID + "--" + CHECKTIME + "--" + CHECKTYPE + "--" + VERIFYCODE + "--" + SENSORID + "--" + Memoinfo + "--" + WorkCode + "--" + sn + "--" + UserExtFmt + "\n";
                    System.out.print(allData);
                    outputStream.println(allData);

                    bufferedWriter.write(inputDate);
                    bufferedWriter.write("  Info:   " + allData);
                    bufferedWriter.newLine();
                }
                bufferedWriter.close();
                dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                lastDateTime = dateFormat.format(CHECKTIME);
                System.out.println(lastDateTime);
                outputStream.close();

                System.out.println("File write successfully.");
                UploadFileToServer.FileUpload(fileName);
                System.out.println("File Upload successfully.");
                System.out.println("");
                
                System.err.println(uploaded);
                if (uploaded) {
                    bufferedWriter = new BufferedWriter(writer);
                    bufferedWriter.write("  Info:   File Uploaded");
                    moveDeleteFile = FileCopyDelete.MoveDelete(fileName);
                    if (moveDeleteFile) {
                        writer = new FileWriter("E:\\Programming\\1. Office project\\Project\\Desktop base\\log\\MyLogFile.log", true);
                        bufferedWriter.write(inputDate);
                        bufferedWriter.write("  Info:   File Moved");
                        bufferedWriter.newLine();
                        System.out.println("File Moved");
                    }
                } else {
                    bufferedWriter.write(inputDate);
                    bufferedWriter.write("  Info:   File not Uploaded");
                    bufferedWriter.write("  Info:   File not Moved");
                    bufferedWriter.newLine();
                    System.out.println("File not Moved");
                }
            } else {
                allData = "no data found";
                bufferedWriter.write(inputDate);
                bufferedWriter.write("  Info:   ");
                bufferedWriter.write(allData);
                bufferedWriter.newLine();
                bufferedWriter.close();
                System.out.println(allData);
                System.out.println("");
            }

        } catch (SQLException | IOException ex) {
            Logger.getLogger(AccessDatabase.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } finally {

            con1.close();
            rs1.close();
            pstm1.close();
            Thread.sleep(20000);
            main(new String[0]);
        }
    }
}

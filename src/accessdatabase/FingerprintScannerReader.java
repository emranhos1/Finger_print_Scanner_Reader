package accessdatabase;

import classes.FileCopyDelete;
import classes.UploadFileToServer;
import dao.SelectQueryDao;
import dbConnection.conRs;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FingerprintScannerReader {

    private static String columnName;
    private static String tableName;
    private static String lastDateTime = "";
    private static String whereCondition;
    private static conRs conrs1;
    private static Connection con1;
    private static ResultSet rs1;
    private static PreparedStatement pstm1;
    private static SimpleDateFormat dateFormat;
    private static Date date;
    private static String inputDate;
    private static String fileName;
    private static FileWriter writer;
    private static BufferedWriter bufferedWriter;
    private static PrintWriter outputStream;
    private static int USERID;
    private static Timestamp CHECKTIME;
    private static String CHECKTYPE;
    private static int VERIFYCODE;
    private static String SENSORID;
    private static String Memoinfo;
    private static String WorkCode;
    private static String sn;
    private static int UserExtFmt;
    private static String allData;
    private static boolean moveDeleteFile;
    private static int upload;

    public static void main(String[] args) throws IOException, SQLException, InterruptedException {

        columnName = " * ";
        tableName = " CHECKINOUT ";
        whereCondition = "CHECKTIME > #" + lastDateTime + "#";

        System.out.println(lastDateTime);
        System.out.println("Select " + columnName + " from " + tableName + " where " + whereCondition);

        if (lastDateTime.equals("")) {
            conrs1 = SelectQueryDao.selectQueryWithOutWhereClause(columnName, tableName);
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

        try {
            if (rs1.isBeforeFirst()) {
                try {
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
                    outputStream.close();

                    dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                    lastDateTime = dateFormat.format(CHECKTIME);
                    System.out.println(lastDateTime);

                    System.out.println("File write successfully.");
                    upload = UploadFileToServer.FileUpload(fileName);
                    System.out.println("File Upload successfully.");
                    System.out.println("");
                    
                    if (upload != 0) {
                        System.err.println(upload);
                        System.err.println("File not Uploaded");
                    } else{
                        System.out.println(upload);
//                        moveDeleteFile = FileCopyDelete.MoveDelete(fileName);
//                        System.out.println(moveDeleteFile);
                        System.out.println("File Uploaded");
                    }
//                        if (moveDeleteFile) {
//                            moveDeleteFile = FileCopyDelete.MoveDelete(fileName);
////                            bufferedWriter.write(inputDate);
////                            bufferedWriter.write("  Info:   File Moved");
////                            bufferedWriter.newLine();
//                            System.out.println("File Moved");
//                        } else {
////                            bufferedWriter.write(inputDate);
////                            bufferedWriter.write("  Info:   File not Moved");
////                            bufferedWriter.newLine();
//                            System.out.println("File not Moved");
//                        }
//                    } else {
////                        bufferedWriter.write(inputDate);
////                        bufferedWriter.write("  Info:   File not Uploaded");
////                        bufferedWriter.newLine();
//                        System.out.println("File not Uploaded");
//                    }
                    bufferedWriter.close();
//                    outputStream.close();
//                    if (uploaded != null) {
//                    moveDeleteFile = FileCopyDelete.MoveDelete(fileName);
//                    if (moveDeleteFile) {
////                        writer = new FileWriter("E:\\Programming\\1. Office project\\Project\\Desktop base\\log\\MyLogFile.log", true);
//                        bufferedWriter = new BufferedWriter(writer);
//                        bufferedWriter.write(inputDate);
//                        bufferedWriter.write("  Info:   File Moved");
//                        bufferedWriter.newLine();
//                        bufferedWriter.close();
//                        System.out.println("File Moved");
////                        }
//                    } else {
//                        bufferedWriter = new BufferedWriter(writer);
//                        bufferedWriter.write(inputDate);
//                        bufferedWriter.write("  Info:   File not Uploaded");
//                        bufferedWriter.write("  Info:   File not Moved");
//                        bufferedWriter.newLine();
//                        bufferedWriter.close();
//                        System.out.println("File not Moved");
//                    }
                } catch (IOException ex) {
                    Logger.getLogger(FingerprintScannerReader.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                bufferedWriter = new BufferedWriter(writer);
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
            Logger.getLogger(FingerprintScannerReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con1.close();
                rs1.close();
                pstm1.close();
//                Thread.sleep(20000);
//                main(new String[0]);
            } catch (SQLException ex) {
                Logger.getLogger(FingerprintScannerReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

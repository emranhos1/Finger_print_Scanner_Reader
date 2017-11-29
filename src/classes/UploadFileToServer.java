/**
 *
 * @author Md. Emran Hossain
 * @email: emranhos1@gmail.com
 */
package classes;

public class UploadFileToServer {

    private static Process process;
    private static String openCMD;
    private static String puttyDir;
    private static String uplodeCode;
    private static String fileDir;
    private static int exitVal;
    private static String exit;
    private static String[] commands;

    public static int FileUpload(String fileName) {

        try {
//            System.out.println("UploadFileToServer called");
            openCMD = "cmd /c cmd.exe";
            puttyDir = "cd E:\\Programming\\1. Office project\\Project\\Desktop base\\software\\Putty\\PuTTYPortable\\App\\putty && E:";
            uplodeCode = "pscp -pw 123456 \"E:\\Programming\\1. Office project\\Project\\Desktop base\\uploaded file\\" + fileName + "\" sunjurahmed@35.188.134.200:/home/sunjurahmed/attn";
            fileDir = "E:\\Programming\\1. Office project\\Project\\Desktop base\\log\\MyLogFile.log";
            //If run several commands in the cmd then construct a single command like this:
            //.....exec("cmd /c start cmd.exe /K \"cd c:/ && dir\"");
            process = Runtime.getRuntime().exec(openCMD + " /K \"" + puttyDir + " && " + uplodeCode + " >> \"" + fileDir + "\"");
//            System.out.println("UploadFileToServer Ended");
            return exitVal;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
}

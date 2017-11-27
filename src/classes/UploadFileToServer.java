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
//            String[] commands = { "cmd.exe", "/C", "Start", "cd E:\\Programming\\1. Office project\\Project\\Desktop base\\software\\Putty\\PuTTYPortable\\App\\putty && E: && ", "&& pscp -pw 123456 \"E:\\Programming\\1. Office project\\Project\\Desktop base\\allData\\" + fileName + "\" sunjurahmed@35.188.134.200:/home/sunjurahmed/attn >> ", "\"E:\\Programming\\1. Office project\\Project\\Desktop base\\log\\MyLogFile.log\""," exit" };

//            commands = new String[6];
//            commands[0] = "cmd";
//            commands[1] = "/c cmd.exe /K ";
//
//            commands[2] = "cd E:\\Programming\\1. Office project\\Project\\Desktop base\\software\\Putty\\PuTTYPortable\\App\\putty && E: && ";
//            commands[3] = "pscp -pw 123456 \"E:\\Programming\\1. Office project\\Project\\Desktop base\\allData\\" + fileName + "\" sunjurahmed@35.188.134.200:/home/sunjurahmed/attn >> ";
//            commands[4] = "\"E:\\Programming\\1. Office project\\Project\\Desktop base\\log\\MyLogFile.log\"";
//            commands[5] = " exit";
//
//            process = Runtime.getRuntime().exec(commands);

            System.out.println("UploadFileToServer called");
            openCMD = "cmd /c cmd.exe";
            puttyDir = "cd E:\\Programming\\1. Office project\\Project\\Desktop base\\software\\Putty\\PuTTYPortable\\App\\putty && E:";
            uplodeCode = "pscp -pw 123456 \"E:\\Programming\\1. Office project\\Project\\Desktop base\\allData\\" + fileName + "\" sunjurahmed@35.188.134.200:/home/sunjurahmed/attn";
            fileDir = "E:\\Programming\\1. Office project\\Project\\Desktop base\\log\\MyLogFile.log";
//            exit = " && exit";
            //If run several commands in the cmd then construct a single command like this:
            //.....exec("cmd /c start cmd.exe /K \"cd c:/ && dir\"");
            process = Runtime.getRuntime().exec(openCMD + " /K \"" + puttyDir + " && " + uplodeCode + " >> \"" + fileDir + "\"");
//            exitVal = process.waitFor();
            System.out.println("UploadFileToServer Ended");
            System.out.println(openCMD + " /K \"" + puttyDir + " && " + uplodeCode + " >> \"" + fileDir + "\"" + exit);
            return exitVal;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
}

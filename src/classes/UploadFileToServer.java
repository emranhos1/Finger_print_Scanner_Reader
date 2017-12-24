/**
 *
 * @author Md. Emran Hossain
 * @email: emranhos1@gmail.com
 */
package classes;

import classes.findFile.AllPath;
import classes.findFile.ReadXMLForPath;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class UploadFileToServer {

    private static Process process;
    private static String openCMD;
    private static String puttyDir;
    private static String uplodeCode;
    private static String fileDir;
    private static int exitVal;
    private static String exit;
    private static String[] commands;
    private static DocumentBuilder documentBuilder;
    private static Document document;
    private static DocumentBuilderFactory documentBuilderFactory;
    private static String password;
    private static String username;
    private static String host;
    private static AllPath rxfp;
    private static String xmlFilePath;
    private static String puttyPath;
    private static String uploadFilePath;
    private static String logFilePath;

    public static int FileUpload(String fileName) throws IOException {
        
        rxfp = ReadXMLForPath.ReadXMLForPath();
        xmlFilePath = rxfp.getXmlFilePath();
        puttyPath = rxfp.getPuttyDir();
        uploadFilePath = rxfp.getUploadFilePath();
        logFilePath = rxfp.getLogFilePath();
        
        File xmlFile = new File(xmlFilePath);

        documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(xmlFile);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(UploadFileToServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        NodeList nodeList = document.getElementsByTagName("putty");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node theNode = nodeList.item(i);
            Element aelement = (Element) theNode;

            Node thePass = aelement.getElementsByTagName("password").item(0);
            Node theUsername = aelement.getElementsByTagName("username").item(0);
            Node theHost = aelement.getElementsByTagName("host").item(0);

            Element pelement = (Element) thePass;
            Element uelement = (Element) theUsername;
            Element helement = (Element) theHost;

            password = pelement.getTextContent();
            username = uelement.getTextContent();
            host = helement.getTextContent();
        }

        try {
            openCMD = "cmd /c cmd.exe";
            puttyDir = "cd "+puttyPath+" && E:";
            uplodeCode = "pscp -pw "+password+" \""+uploadFilePath+"\\" + fileName + "\" "+username+host;
            fileDir = logFilePath;
            //If run several commands in the cmd then construct a single command like this:
            //.....exec("cmd /c start cmd.exe /K \"cd c:/ && dir\"");
            process = Runtime.getRuntime().exec(openCMD + " /K \"" + puttyDir + " && " + uplodeCode + " >> \"" + fileDir + "\"");
            return exitVal;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
}
/**
 *
 * @author Md. Emran Hossain
 * @email: emranhos1@gmail.com
 */
package classes.findFile;

import fingerprintscannerreader.FingerprintScannerReader;
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

public class ReadXMLForPath {

    private static DocumentBuilderFactory documentBuilderFactory;
    private static DocumentBuilder documentBuilder;
    private static Document document;
    private static String logFilePath = "";
    private static String allDataPath = "";
    private static String dataBaseLocation;
    private static String uploadFilePath;
    private static String xmlFilePath;
    private static String puttyDir;

    public static AllPath ReadXMLForPath() throws IOException {
        File xmlFile = new File("./XML\\ForPath.xml");

        documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(xmlFile);
        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(FingerprintScannerReader.class.getName()).log(Level.SEVERE, null, ex);
        }

        NodeList nodeList = document.getElementsByTagName("path");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node theNode = nodeList.item(i);
            Element aelement = (Element) theNode;

            Node lFile = aelement.getElementsByTagName("logFilePath").item(0);
            Node aData = aelement.getElementsByTagName("allDataPath").item(0);
            Node dbLocation = aelement.getElementsByTagName("databaseLocation").item(0);
            Node ufPath = aelement.getElementsByTagName("uploadFilePath").item(0);
            Node xF = aelement.getElementsByTagName("xmlFilePath").item(0);
            Node pD = aelement.getElementsByTagName("puttyDir").item(0);

            Element pelement = (Element) lFile;
            Element uelement = (Element) aData;
            Element dbLoc = (Element) dbLocation;
            Element uFilePath = (Element) ufPath;
            Element xFile = (Element) xF;
            Element pDir = (Element) pD;

            logFilePath = pelement.getTextContent();
            allDataPath = uelement.getTextContent();
            dataBaseLocation = dbLoc.getTextContent();
            uploadFilePath = uFilePath.getTextContent();
            xmlFilePath = xFile.getTextContent();
            puttyDir = pDir.getTextContent();
        }
        
        AllPath allPath = new AllPath(logFilePath, allDataPath, dataBaseLocation, uploadFilePath, xmlFilePath, puttyDir);
        return allPath;
    }
}

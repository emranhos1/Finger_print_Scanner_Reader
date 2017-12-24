/**
 *
 * @author Md. Emran Hossain
 * @email: emranhos1@gmail.com
 */
package classes.findFile;

public class AllPath {
    
    private String logFilePath;
    private String allDataPath;
    private String dataBaseLocation;
    private String uploadFilePath;
    private String xmlFilePath;
    private String puttyDir;

    public String getXmlFilePath() {
        return xmlFilePath;
    }

    public void setXmlFilePath(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    public String getPuttyDir() {
        return puttyDir;
    }

    public void setPuttyDir(String puttyDir) {
        this.puttyDir = puttyDir;
    }

    public String getUploadFilePath() {
        return uploadFilePath;
    }

    public void setUploadFilePath(String uploadFilePath) {
        this.uploadFilePath = uploadFilePath;
    }

    public String getDataBaseLocation() {
        return dataBaseLocation;
    }

    public void setDataBaseLocation(String dataBaseLocation) {
        this.dataBaseLocation = dataBaseLocation;
    }
    
    public String getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public String getAllDataPath() {
        return allDataPath;
    }

    public void setAllDataPath(String allDataPath) {
        this.allDataPath = allDataPath;
    }

    public AllPath(String logFilePath, String allDataPath, String dataBaseLocation, String uploadFilePath, String xmlFilePath,String puttyDir) {
        this.logFilePath = logFilePath;
        this.allDataPath = allDataPath;
        this.dataBaseLocation = dataBaseLocation;
        this.uploadFilePath = uploadFilePath;
        this.xmlFilePath = xmlFilePath;
        this.puttyDir = puttyDir;
    }
}

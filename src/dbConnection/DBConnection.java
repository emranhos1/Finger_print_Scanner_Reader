/**
 *
 * @author Md. Emran Hossain
 * @email: emranhos1@gmail.com
 */
package dbConnection;

import classes.findFile.AllPath;
import classes.findFile.ReadXMLForPath;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
    
    Connection con;
    PreparedStatement pstm;
    ResultSet rs;
    static final String url = "jdbc:ucanaccess://";
    static final String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
    private AllPath rxfp;
    private String location;

    public Connection myConn() throws SQLException, IOException {
        rxfp = ReadXMLForPath.ReadXMLForPath();
        location = rxfp.getDataBaseLocation();
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url + location);
            return con;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}

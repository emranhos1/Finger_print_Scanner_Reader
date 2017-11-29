/**
 *
 * @author Md. Emran Hossain
 * @email: emranhos1@gmail.com
 */
package DBConnection;

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
    static final String location = "E:\\Programming\\1. Office project\\Project\\Desktop base\\att2000.mdb";

    public Connection myConn() throws SQLException {

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

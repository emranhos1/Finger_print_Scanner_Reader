/**
 *
 * @author Md. Emran Hossain
 * @email: emranhos1@gmail.com
 */
package dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class conRs {

    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public PreparedStatement getPstm() {
        return pstm;
    }

    public void setPstm(PreparedStatement pstm) {
        this.pstm = pstm;
    }

    public conRs(Connection con, ResultSet rs, PreparedStatement pstm) {
        this.con = con;
        this.rs = rs;
        this.pstm = pstm;
    }
    
}

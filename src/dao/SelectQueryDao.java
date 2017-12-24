/**
 *
 * @author Md. Emran Hossain
 * @email: emranhos1@gmail.com
 */
package dao;

import dbConnection.DBConnection;
import dbConnection.conRs;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectQueryDao {

    static DBConnection db = new DBConnection();
    static Connection con;
    static ResultSet rs;
    static PreparedStatement pstm;

    public static conRs selectQueryWithOutWhereClause(String columnName, String tableName) throws SQLException, IOException {
        con = db.myConn();
        pstm = con.prepareStatement("Select " + columnName + " from " + tableName);
        rs = pstm.executeQuery();
        conRs conrs = new conRs(con, rs, pstm);
        return conrs;
    }

    public static conRs selectQueryWithWhereClause(String columnName, String tableName, String whereCondition) throws SQLException, IOException {
        con = db.myConn();
        pstm = con.prepareStatement("Select " + columnName + " from " + tableName + " where " + whereCondition);
        rs = pstm.executeQuery();
        conRs conrs = new conRs(con, rs, pstm);
        return conrs;
    }
    
    public static conRs selectQueryWithOutWhereClauseWithOrderBy(String columnName, String tableName, String orderBy) throws SQLException, IOException {
        con = db.myConn();
        pstm = con.prepareStatement("Select " + columnName + " from " + tableName + " order by " + orderBy);
        rs = pstm.executeQuery();
        conRs conrs = new conRs(con, rs, pstm);
        return conrs;
    }
}

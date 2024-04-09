package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * MySQLConnector
 * 
 * @author nsr
 * @since 2024-04-08
 **/
public class MySQLConnector {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String DB_URL = "jdbc:mysql://127.0.0.1:3306/corups_db";
	private final String DB_ID = "root";
	private final String DB_PWD = "1234";

	public MySQLConnector() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Connect to DB
	 * @param 
	 * @return 
	 */
	public Connection connection() {
		if(conn == null){
			try {
				Class.forName(this.JDBC_DRIVER);
				this.conn = DriverManager.getConnection(this.DB_URL, this.DB_ID, this.DB_PWD);
				
				return conn;
				
			} catch (Exception e) {
				System.err.println("CONNECTION ERR : " + e.getMessage());
			}
			
		}
		
		return conn;
	}
	
	/**
	 * Close DB
	 * @param Connection connector, Statement stmt, ResultSet rs
	 * @return 
	 */
	public void close(Connection connector, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (connector != null) {
				connector.close();
				conn = null;
			}
		} catch (SQLException e) {
			System.err.println("Connection, Statement, ResultSet CLOSE ERR : " + e.getMessage());
		}
	}

	/**
	 * Close DB
	 * @param Connection connector, PreparedStatement pstmt, ResultSet rs
	 * @return 
	 */
	public void close(Connection connector, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (connector != null) {
				connector.close();
				conn = null;
			}
		} catch (SQLException e) {
			System.err.println("Connection, PreparedStatement, ResultSet CLOSE ERR : " + e.getMessage());
		}
	}


}

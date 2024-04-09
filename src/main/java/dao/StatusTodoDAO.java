package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.MySQLConnector;
import dto.StatusTodoDTO;

/**
 * Status-Todo DAO
 * 
 * @author nsr
 * @since 2024-04-08
 **/
public class StatusTodoDAO extends MySQLConnector{

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public StatusTodoDAO() {
	}
	/**
	 * Get Todo No List
	 * 
	 * @param String id
	 * @return ArrayList<StatusDTO>
	 */
	public ArrayList<StatusTodoDTO> todoNoList(String id) {
		
		ArrayList<StatusTodoDTO> stausTodoArray = new ArrayList<StatusTodoDTO>();
		conn = connection();
		
		try {
			String query = "SELECT * FROM status_todo WHERE member_id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				StatusTodoDTO statusTodo = new StatusTodoDTO();
				statusTodo.setMemberId(rs.getString("member_id"));
				statusTodo.setTodoNo(rs.getInt("todo_no"));
				statusTodo.setStatus(rs.getString("status"));
				stausTodoArray.add(statusTodo);
				
			}
			
		} catch (SQLException e) {
			System.err.println("todoNoList() ERR : " + e.getMessage());
		}
		finally {
			close(conn, pstmt, rs);
		}
		
		return stausTodoArray;
	}

	/**
	 * Get Status
	 * 
	 * @param String id, int todo_no
	 * @return String
	 */
	public String getStatus(String id, int todo_no) {
		
		String status = "";
		conn = connection();
		
		try {
			String query = "SELECT status FROM status_todo WHERE member_id=? AND todo_no=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setInt(2, todo_no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				status = rs.getString("status");
			}
			
		} catch (SQLException e) {
			System.err.println("getStatus() ERR : " + e.getMessage());
		}
		finally {
			close(conn, pstmt, rs);
		}
		
		return status;
	}
}

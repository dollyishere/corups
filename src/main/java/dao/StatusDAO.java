package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.MySQLConnector;
import dto.StatusDTO;

/**
 * Status-Todo DAO
 * 
 * @author nsr
 * @since 2024-04-08
 **/
public class StatusDAO extends MySQLConnector{

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public StatusDAO() {
	}
	/**
	 * Get Todo No List
	 * 
	 * @param String id
	 * @return ArrayList<StatusDTO>
	 */
	public ArrayList<StatusDTO> todoNoList(String id) {
		
		ArrayList<StatusDTO> statusTodoArray = new ArrayList<StatusDTO>();
		conn = connection();
		
		try {
			String query = "SELECT * FROM status WHERE member_id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				StatusDTO statusTodo = new StatusDTO();
				statusTodo.setMemberId(rs.getString("member_id"));
				statusTodo.setTodoNo(rs.getInt("todo_no"));
				statusTodo.setStatus(rs.getString("status"));
				statusTodoArray.add(statusTodo);
				
			}
			
		} catch (SQLException e) {
			System.err.println("todoNoList() ERR : " + e.getMessage());
		}
		finally {
			close(conn, pstmt, rs);
		}
		
		return statusTodoArray;
	}

	/**
	 * Get Status
	 * 
	 * @param String id, int todo_no
	 * @return StatusTodoDTO
	 */
	public StatusDTO getStatus(String id, int todo_no) {
		
		StatusDTO statusTodo = new StatusDTO();
		conn = connection();
		
		try {
			String query = "SELECT status FROM status WHERE member_id=? AND todo_no=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setInt(2, todo_no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				statusTodo.setMemberId(id);
				statusTodo.setTodoNo(todo_no);
				statusTodo.setStatus(rs.getString("status"));
			}
			
		} catch (SQLException e) {
			System.err.println("getStatus() ERR : " + e.getMessage());
		}
		finally {
			close(conn, pstmt, rs);
		}
		
		return statusTodo;
	}
	

	/**
	 * Insert Status
	 * 
	 * @param StatusDTO status
	 * @return boolean
	 */
	public boolean insertStatus(StatusDTO status) {
		
		conn = connection();
		
		try {
			String query = "INSERT IGNORE INTO status (member_id, todo_no, status) VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, status.getMemberId());
			pstmt.setInt(2, status.getTodoNo());
			pstmt.setString(3, status.getStatus());
			
			pstmt.executeUpdate();

			return true;
			
		} catch (SQLException e) {
			System.err.println("insertStatus() ERR : " + e.getMessage());
			return false;
		}
		finally {
			close(conn, pstmt, rs);
		}
	}

	/**
	 * Get Status
	 * 
	 * @param StatusDTO status
	 * @return boolean
	 */
	public boolean updateStatus(StatusDTO status) {
		
		conn = connection();
		
		try {
			String query = "UPDATE status SET status=? WHERE member_id=? AND todo_no=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, status.getStatus());
			pstmt.setString(2, status.getMemberId());
			pstmt.setInt(3, status.getTodoNo());
			
			pstmt.executeUpdate();

			return true;
			
		} catch (SQLException e) {
			System.err.println("updateStatus() ERR : " + e.getMessage());
			return false;
		}
		finally {
			close(conn, pstmt, rs);
		}
	}
	
	
	/**
	 * Delete the Status
	 * @param int no
	 * @return boolean
	 */
	public boolean deleteStatus(int todoNo) {
		
		conn = connection();
		  
		try {
			
			String query = "DELETE FROM status WHERE todo_no=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, todoNo);
	        pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			System.err.println("deleteStatus() ERR : " + e.getMessage());
			return false;
		}
		finally {
			close(conn, pstmt, null);
		}
	}
	
}

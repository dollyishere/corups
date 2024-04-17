package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.MySQLConnector;
import dto.FileDTO;

/**
 * FILE DAO
 * @author nsr
 * @since 2024-04-08
 * **/
public class FileDAO extends MySQLConnector{
	
	private Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public FileDAO() {
		
		
	}
	
	
	/**
	 * Get file List of todo
	 * @param int todo_no
	 * @return ArrayList<FileDTO>
	 */
	public ArrayList<FileDTO> fileList(int todoNo) {
		ArrayList<FileDTO> files = new ArrayList<FileDTO>();
		conn = connection();
		String query = "SELECT * FROM files WHERE todo_no=? ORDER BY no";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, todoNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FileDTO file = new FileDTO();
				file.setName(rs.getString("name"));
				file.setNo(rs.getInt("no"));
				file.setTodoNo(rs.getInt("todo_no"));
				
				files.add(file);
			}
		} catch (SQLException e) {
			System.err.println("fileList() ERR : " + e.getMessage());
		}
		return files;
	}

	/**
	 * Get file Detail
	 * @param int no
	 * @return FileDTO
	 */
	public FileDTO fileDetail(int no) {
		return null;
	}
	
	/**
	 * Insert file
	 * @param String file, int todoNo
	 * @return boolean
	 */
	public boolean insertFiles(String fileName, int todoNo) {
		
		System.out.println("파일 업로드");
		System.out.println(fileName);
		System.out.println(todoNo);
		boolean success = false;
		conn = connection();
		String query = "INSERT INTO files (todo_no, name) VALUES (?, ?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, todoNo);
			pstmt.setString(2, fileName);
			
			int n =pstmt.executeUpdate();
			
			if(n > 0)
				success = true;
		} catch (SQLException e) {
			System.err.println("insertFiles() ERR: " + e.getMessage());
		}
		
		return success;
	}
	
	/**
	 * Delete file
	 * @param int no
	 * @return boolean
	 */
	public boolean deleteFile(int no) {
		
		conn = connection();
		String query = "DELETE FROM files WHERE no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			int n = pstmt.executeUpdate();
			
			if(n > 0)
				return true;
			
		} catch (SQLException e) {
			System.err.println("deleteFile() ERR : " + e.getMessage());
		}
		
		return false;
	}
	
	
	/**
	 * Delete file
	 * @param int no
	 * @return boolean
	 */
	public boolean deleteFiles(int todoNo) {
		
		conn = connection();
		String query = "DELETE FROM files WHERE todo_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, todoNo);
			
			int n = pstmt.executeUpdate();
			
			if(n > 0)
				return true;
			
		} catch (SQLException e) {
			System.err.println("deleteFiles() ERR : " + e.getMessage());
		}
		
		return false;
	}
}

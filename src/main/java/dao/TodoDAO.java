package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.MySQLConnector;
import dto.StatusDTO;
import dto.TodoDTO;

/**
 * Todo DAO
 * 
 * @author nsr
 * @since 2024-04-08
 **/
public class TodoDAO extends MySQLConnector{

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	public TodoDAO() {
	}
	
	/**
	 * Get Todo List of chapter
	 * @param int chapter_no
	 * @return ArrayList<TodoDTO>
	 */
	public ArrayList<TodoDTO> todoList(int chapter_no) {
		ArrayList<TodoDTO> todoArray = null;
		conn = connection();
		
		try {
			String query = "SELECT * FROM todo WHERE chapter_no=?";
			pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, chapter_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TodoDTO todoDTO = createTodoDTO();
				todoArray.add(todoDTO);
			}
			
		} catch (SQLException e) {
			System.err.println("todoList() ERR : " + e.getMessage());
		}
		finally {
			close(conn, pstmt, rs);
		}	
		return todoArray;
	}
	
	/**
	 * Get the Todo List of the specified searchText and category
	 * @param String searchText, ArrayList<StatusDTO> statusTodoArray
	 * @return ArrayList<TodoDTO>
	 */
	public ArrayList<TodoDTO> searchTodo(String searchText, ArrayList<StatusDTO> statusTodoArray) {
		ArrayList<TodoDTO> todoArray = new ArrayList<TodoDTO>();
		conn = connection();
		System.out.println("여기보세요~~~~~");
		System.out.println(searchText);
		
		try {
			String query = "SELECT * FROM todo WHERE name LIKE ? OR detail LIKE ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + searchText + "%");
			pstmt.setString(2, "%" + searchText + "%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TodoDTO todoDTO = createTodoDTO();
				// todoDTO.getNo() 과 같다면 추가하고 break;
				for(int i = 0; i < statusTodoArray.size(); i++){
					if(todoDTO.getNo() == statusTodoArray.get(i).getTodoNo()) {
						todoArray.add(todoDTO);	
						break;
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("searchTodo() ERR : " + e.getMessage());
		}
		finally {
			close(conn, pstmt, rs);
		}
		
		
		return todoArray;
	}
	
	/**
	 * Get the Todo Detail
	 * @param int no
	 * @return TodoDTO
	 */
	public TodoDTO todoDetail(int no) {
		TodoDTO todoDTO = null;
		conn = connection();
		
		try {
			String query = "SELECT * FROM todo WHERE no=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				todoDTO = createTodoDTO();
			}
			
		} catch (SQLException e) {
			System.err.println("todoDetail() ERR : " + e.getMessage());
		}
		finally {
			close(conn, pstmt, rs);
		}
		
		
		return todoDTO;
	}
	
	/**
	 * Insert the Todo
	 * @param TodoDTO todoDTO
	 * @return int
	 */
	public int insertTodo(TodoDTO todo) {
		int todo_no = -1;
		conn = connection();
		  
		try {
			
			String query = "INSERT INTO todo (name, chapter_no, detail, created_date, update_date, start_date, end_date) VALUES (?, ?, ?, now(), now(), ?, ?)";
			pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, todo.getName());
			pstmt.setInt(2, todo.getChapterNo());
	        pstmt.setString(3, todo.getDetail());
	        pstmt.setDate(4, todo.getStartDate());
	        pstmt.setDate(5, todo.getEndDate());     
	        pstmt.executeUpdate();
			
	        rs = pstmt.getGeneratedKeys();  
	        if (rs.next()) {
	        	todo_no = rs.getInt(1);  // 첫 번째 칼럼의 값을 가져옴 (보통 자동 증가된 값)
	        }
			
		} catch (SQLException e) {
			System.err.println("insertTodo() ERR : " + e.getMessage());		
		}
		finally {
			close(conn, pstmt, rs);
		}
		return todo_no;
	}

	/**
	 * Update the Todo
	 * @param TodoDTO todoDTO
	 * @return boolean
	 */
	public boolean updateTodo(TodoDTO todo) {
		conn = connection();
		  
		try {
			String query = "UPDATE todo SET name=?, detail=?, start_date=?, end_date=?, update_date=now() WHERE no=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, todo.getName());
	        pstmt.setString(2, todo.getDetail());
	        pstmt.setDate(3, todo.getStartDate());
	        pstmt.setDate(4, todo.getEndDate());
	        pstmt.setInt(5, todo.getNo());	        
	        pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			System.err.println("updateTodo() ERR : " + e.getMessage());
			return false;			
		}
		finally {
			close(conn, pstmt, null);
		}
	}

	/**
	 * Delete the Todo
	 * @param int no
	 * @return boolean
	 */
	public boolean deleteTodo(int no) {
		
		conn = connection();
		  
		try {
			
			String query = "DELETE FROM todo WHERE no=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
	        pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			System.err.println("deleteTodo() ERR : " + e.getMessage());
			return false;			
		}
		finally {
			close(conn, pstmt, null);
		}
	}
	
	
	private TodoDTO createTodoDTO() throws SQLException {
		TodoDTO todoDTO = new TodoDTO();
		todoDTO.setNo(rs.getInt("no"));
		todoDTO.setChapterNo(rs.getInt("chapter_no"));
		todoDTO.setName(rs.getString("name"));
		todoDTO.setDetail(rs.getString("detail"));
		todoDTO.setCreatedDate(rs.getDate("created_date"));
		todoDTO.setUpdateDate(rs.getDate("update_date"));
		todoDTO.setStartDate(rs.getDate("start_date"));
		todoDTO.setEndDate(rs.getDate("end_date"));
		return todoDTO;
	}

}

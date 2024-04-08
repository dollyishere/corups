package dao;

import java.util.ArrayList;

import dto.TodoDTO;

/**
 * Todo DAO
 * 
 * @author nsr
 * @since 2024-04-08
 **/
public class TodoDAO {

	public TodoDAO() {
	}
	
	/**
	 * Get Todo List of chapter
	 * @param int chapter_no
	 * @return ArrayList<TodoDTO>
	 */
	public ArrayList<TodoDTO> todoList(int chapter_no) {
		return null;
	}
	
	/**
	 * Get the Todo List of the specified searchText and category
	 * @param String searchText, char category
	 * @return ArrayList<TodoDTO>
	 */
	public ArrayList<TodoDTO> searchTodo(String searchText, char category) {
		return null;
	}
	
	/**
	 * Get the Todo Detail
	 * @param String searchText, char category
	 * @return TodoDTO
	 */
	public TodoDTO todoDetail(int no) {
		return null;
	}
	
	/**
	 * Insert the Todo
	 * @param TodoDTO todoDTO
	 * @return boolean
	 */
	public boolean insertTodo(TodoDTO todoDTO) {
		return false;
	}

	/**
	 * Update the Todo
	 * @param TodoDTO todoDTO
	 * @return boolean
	 */
	public boolean updateTodo(TodoDTO todoDTO) {
		return false;
	}

	/**
	 * Delete the Todo
	 * @param int no
	 * @return boolean
	 */
	public boolean deleteTodo(int no) {
		return false;
	}

}

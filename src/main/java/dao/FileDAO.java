package dao;

import java.util.ArrayList;


import dto.FileDTO;
/**
 * FILE DAO
 * @author nsr
 * @since 2024-04-08
 * **/
public class FileDAO {

	
	public FileDAO() {
	}
	
	/**
	 * Get file List of todo
	 * @param int todo_no
	 * @return ArrayList<FileDTO>
	 */
	public ArrayList<FileDTO> fileList(int todo_no) {
		return null;
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
	 * @param FileDTO file
	 * @return boolean
	 */
	public boolean insertFiles(FileDTO file) {
		return false;
	}
	
	/**
	 * Delete file
	 * @param int no
	 * @return boolean
	 */
	public boolean deleteFiles(int no) {
		return false;
	}
}

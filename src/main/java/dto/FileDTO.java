package dto;

import java.util.ArrayList;

/**
 * File DTO
 * 
 * @author nsr
 * @since 2024-04-08
 **/
public class FileDTO {
	private int no;
	private int todoNo;
	private String name;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getTodoNo() {
		return todoNo;
	}

	public void setTodoNo(int todoNo) {
		this.todoNo = todoNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FileDTO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "FileDTO [no=" + no + ", todoNo=" + todoNo + ", name=" + name + "]";
	}

}

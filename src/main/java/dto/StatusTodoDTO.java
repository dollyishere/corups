package dto;
/**
 * Status-Todo DTO
 * 
 * @author nsr
 * @since 2024-04-08
 **/
public class StatusTodoDTO {
	private String	member_id;
	private int	todo_no;
	private int	status_no;

	public StatusTodoDTO() {
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public int getTodo_no() {
		return todo_no;
	}

	public void setTodo_no(int todo_no) {
		this.todo_no = todo_no;
	}

	public int getStatus_no() {
		return status_no;
	}

	public void setStatus_no(int status_no) {
		this.status_no = status_no;
	}

	@Override
	public String toString() {
		return "StatusTodoDTO [member_id=" + member_id + ", todo_no=" + todo_no + ", status_no=" + status_no + "]";
	}

	
}

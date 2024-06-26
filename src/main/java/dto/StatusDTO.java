package dto;
/**
 * Status-Todo DTO
 * 
 * @author nsr
 * @since 2024-04-08
 **/
public class StatusDTO {
	private String	memberId;
	private int	todoNo;
	private String	status;

	public StatusDTO() {
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getTodoNo() {
		return todoNo;
	}

	public void setTodoNo(int todoNo) {
		this.todoNo = todoNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "StatusDTO [memberId=" + memberId + ", todoNo=" + todoNo + ", status=" + status + "]";
	}


	
}

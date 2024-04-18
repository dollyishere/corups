package dto;

/**
 * admin DTO
 * @author ljy
 * @since 2024-04-08
 * **/
public class AdminDTO {
	private int no; // 관리자 번호
	private String	adminId;	// 관리자 아이디
	private String	adminPwd;	// 관리자 비밀번호
	
	public AdminDTO() {
		
	} // 생성자 END
	
	// getters, setters
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}


	// toString override
	@Override
	public String toString() {
		return "AdminDTO [no=" + no + ", adminId=" + adminId + ", adminPwd=" + adminPwd + "]";
	}

	
}

package dto;

/**
 * member & study DTO
 * @author ljy
 * @since 2024-04-08
 * **/
public class MemberStudyDTO {
	private String	memberId;	//회원 id
	private int	studyNo;	//스터디 번호
	
	public MemberStudyDTO() {

	} // MemberStudyDTO() END

	// getters & setters
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getStudyNo() {
		return studyNo;
	}

	public void setStudyNo(int studyNo) {
		this.studyNo = studyNo;
	}

	// toString Override
	@Override
	public String toString() {
		return "MemberStudyDTO [memberId=" + memberId + ", studyNo=" + studyNo + "]";
	}

	
}

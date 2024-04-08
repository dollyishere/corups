package dto;

import java.sql.Date;

/**
 * member DTO
 * @author ljy
 * @since 2024-04-08
 * **/
public class MemberDTO {
	private int	no;	// 회원 넘버
	private String	id; // 회원 아이디
	private String	pwd; 	// 회원 비밀번호
	private String	name;	// 회원 이름
	private Date	joinDate;	// 회원 가입 날짜
	private Date	birthday;	// 회원 생일
	private String	email; 	// 회원 이메일 주소
	private String	job; 	// 회원 직업
	private String	interest;	// 회원 관심사
	private String	image;	// 회원 프로필 사진
	
	public MemberDTO() {
		
	} // 생성자 END
	
	// getters, setters
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	// toString override
	@Override
	public String toString() {
		return "MemberDTO [no=" + no + ", id=" + id + ", pwd=" + pwd + ", name=" + name + ", joinDate=" + joinDate
				+ ", birthday=" + birthday + ", email=" + email + ", job=" + job + ", interest=" + interest + ", image="
				+ image + "]";
	}
	
}

package dto;

import java.sql.Date;
/**
 * Todo DTO
 * 
 * @author nsr
 * @since 2024-04-08
 **/
public class TodoDTO {
	private int no;
	private int chapterNo;
	private String name;
	private String detail;
	private Date createdDate;
	private Date updateDate;
	private Date startDate;
	private Date endDate;

	public TodoDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getChapterNo() {
		return chapterNo;
	}

	public void setChapterNo(int chapterNo) {
		this.chapterNo = chapterNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	@Override
	public String toString() {
		return "TodoDTO [no=" + no + ", chapterNo=" + chapterNo + ", name=" + name
				+ ", detail=" + detail + ", createdDate=" + createdDate + ", updateDate=" + updateDate + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}

}

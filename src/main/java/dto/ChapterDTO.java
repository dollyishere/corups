package dto;
/**
 * Chapter DTO
 * @author cyb
 * @since 2024-04-08
 * **/
import java.sql.Date;

public class ChapterDTO {
	int	no;
	int	studyNo;
	private String name;
	private Date createdDate;
	private Date updateDate;
	private Date startDate;
	private Date endDate;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getStudyNo() {
		return studyNo;
	}

	public void setStudyNo(int studyNo) {
		this.studyNo = studyNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return "ChapterDTO [no=" + no + ", studyNo=" + studyNo + ", name=" + name + ", createdDate=" + createdDate
				+ ", updateDate=" + updateDate + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	public ChapterDTO() {
	}

}

package dto;
/**
 * Chapter DTO
 * @author cyb
 * @since 2024-04-08
 * **/
import java.sql.Date;

public class ChapterDTO {
	
	/** 챕터 번호*/
	int	no;
	/** 스터디 번호*/
	int	studyNo;
	/** 챕터 이름*/
	private String name;
	/** 챕터 생성날짜*/
	private Date createdDate;
	/** 챕터 수정날짜*/
	private Date updateDate;
	/** 챕터 시작날짜*/
	private Date startDate;
	/** 챕터 마감날짜*/
	private Date endDate;
	/** 챕터 페이지 번호*/
	private String pageNum = "1";
	/** 챕터 목록 페이지 노출 수 */
	private int listCount = 10;
	/** 챕터 목록 페이지 네비게이터 블록 수*/
	private int pagePerBlock = 10;

	/**
	 * 생성자
	 * */
	public int getNo() {
		return no;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getPagePerBlock() {
		return pagePerBlock;
	}

	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
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
				+ ", updateDate=" + updateDate + ", startDate=" + startDate + ", endDate=" + endDate + ", pageNum="
				+ pageNum + ", listCount=" + listCount + ", pagePerBlock=" + pagePerBlock + "]";
	}
	public ChapterDTO() {
	}

}

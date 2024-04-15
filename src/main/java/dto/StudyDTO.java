package dto;

import java.sql.Date;

/**
 * study DTO
 * @author 황인환
 * @since 2024-04-08
 * **/
public class StudyDTO {
	/** 스터디 번호 */
	private int	no;
	/** 스터디 이름 */
	private String name;
	/** 스터디 설명 */
	private String detail;
	/** 스터디 비밀번호 */
	private String studyPwd;
	/** 스터디 최대 인원 수 */
	private int maxNum;
	/** 스터디 생성 날짜 */
	private Date createdDate;
	/** 스터디 최근 수정 날짜 */
	private Date updatedDate;
	/** 스터디 생성 id 기록 */
	private String createUserId;
	/** 스터디 카테고리 */
	private String category;
	
	/** 페이징 **/
	/** 스터디 페이지 번호 */
	private String pageNum = "1";
	/** 검색 항목 */
	private String searchType = "";
	/** 검색어 */
	private String searchText = "";
	/** 스터디 목록 페이지 게시물 노출 수 */
	private int listCount = 9;
	/** 목록 페이지 네비게이터 블록 수 */
	private int pagePerBlock = 10;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public String getStudyPwd() {
		return studyPwd;
	}
	public void setStudyPwd(String studyPwd) {
		this.studyPwd = studyPwd;
	}
	public int getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
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
	
	@Override
	public String toString() {
		return "StudyDTO [no=" + no + ", name=" + name + ", detail=" + detail + ", studyPwd=" + studyPwd + ", maxNum="
				+ maxNum + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", createUserId="
				+ createUserId + ", category=" + category + ", pageNum=" + pageNum + ", searchType=" + searchType
				+ ", searchText=" + searchText + ", listCount=" + listCount + ", pagePerBlock=" + pagePerBlock + "]";
	}
	
	
	
}

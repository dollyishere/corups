package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.MySQLConnector;
import dto.StudyDTO;

public class StudyDAO {
	private Connection connect = null;
	private PreparedStatement pstmt = null;
	private MySQLConnector dataFactory = null;
	private ResultSet rs = null;
	
	public StudyDAO() {
		dataFactory = new MySQLConnector();
	}
	
	
	/**
	 * 스터디 목록 조회
	 * 
	 * @param 
	 * @return ArrayList<StudyDTO>
	 * **/
	
	public ArrayList<StudyDTO> studyList() {
		ArrayList<StudyDTO> studyList = new ArrayList<StudyDTO>();
		try {
			connect = dataFactory.connection();
			String query = "select * from study order by no desc";
			System.out.println(query);
			
			pstmt = connect.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			StudyDTO study = null;
			while (rs.next()) {
				study = new StudyDTO();
				
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String detail = rs.getString("detail");
				String study_pwd = rs.getString("study_pwd");
				int max_num = rs.getInt("max_num");
				Date created_date = rs.getDate("created_date");
				Date update_date = rs.getDate("update_date");
				String create_user_id = rs.getString("create_user_id");
				String category = rs.getString("category");
				
				study.setNo(no);
				study.setName(name);
				study.setDetail(detail);
				study.setStudyPwd(study_pwd);
				study.setMaxNum(max_num);
				study.setCreatedDate(created_date);
				study.setUpdatedDate(update_date);
				study.setCreateUserId(create_user_id);
				study.setCategory(category);
				
				studyList.add(study);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			dataFactory.close(connect, pstmt, rs);
		}
		return studyList;
	}
	
	
	
	
	
	
	
	/**
	 * 내 스터디 목록 조회
	 * 
	 * @param String id(member)
	 * @return ArrayList<StudyDTO>
	 * **/
	
	public ArrayList<StudyDTO> myStudyList(String id) {
		ArrayList<StudyDTO> myStudyList = new ArrayList<StudyDTO>();
		try {
			connect = dataFactory.connection();
			String query = "SELECT * FROM study INNER JOIN member_study ON study.no = member_study.study_no ORDER BY study.no DESC";
			System.out.println(query);
			
			pstmt = connect.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			StudyDTO study = null;
			while (rs.next()) {
				study = new StudyDTO();
				
				study.setNo(rs.getInt("no"));
				study.setName(rs.getString("name"));
				study.setDetail(rs.getString("detail"));
				study.setStudyPwd(rs.getString("study_pwd"));
				study.setMaxNum(rs.getInt("max_num"));
				study.setCreatedDate(rs.getDate("created_date"));
				study.setUpdatedDate(rs.getDate("update_date"));
				study.setCreateUserId(rs.getString("create_user_id"));
				study.setCategory(rs.getString("category"));
				
				myStudyList.add(study);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			dataFactory.close(connect, pstmt, rs);
		}
		return myStudyList;
	}
	
	
	/**
	 * 스터디 검색
	 * 
	 * @param String searchText, String category
	 * @return ArrayList<StudyDTO>
	 * **/
	public ArrayList<StudyDTO> searchStudy(String searchText, String searchCategory) {
		ArrayList<StudyDTO> studySearch = new ArrayList<StudyDTO>();
		System.out.println(searchText + " " +  searchCategory);
		
		studySearch = new ArrayList<StudyDTO>();
		
		try {
			connect = dataFactory.connection();
			String query = "select * from study where name LIKE ? OR detail LIKE ? ";
			if(searchCategory != "ALL")
				query += "AND category = ? ";
			query += "order by no desc";
					
			System.out.println(query);
			
			pstmt = connect.prepareStatement(query);
			pstmt.setString(1, "%"+searchText+"%");
			pstmt.setString(2, "%"+searchText+"%");
			if(searchCategory != "ALL") {
				pstmt.setString(3, searchCategory);
			}
			rs = pstmt.executeQuery();
			
			StudyDTO study = null;
			while (rs.next()) {
				study = new StudyDTO();
				
				study.setNo(rs.getInt("no"));
				study.setName(rs.getString("name"));
				study.setDetail(rs.getString("detail"));
				study.setStudyPwd(rs.getString("study_pwd"));
				study.setMaxNum(rs.getInt("max_num"));
				study.setCreatedDate(rs.getDate("created_date"));
				study.setUpdatedDate(rs.getDate("update_date"));
				study.setCreateUserId(rs.getString("create_user_id"));
				study.setCategory(rs.getString("category"));
				
				studySearch.add(study);
			}
			
		} catch (SQLException e) {
			System.err.println("searchStudy() ERR : " + e.getMessage());
		}
		finally {
			dataFactory.close(connect, pstmt, rs);
		}
		
		return studySearch;

	}
	
	/**
	 * 스터디 상세 조회
	 * 
	 * @param  int no
	 * @return StudyDTO
	 * **/
	public StudyDTO studyDetail(int no) {
		StudyDTO studyDetail = new StudyDTO();
		
		connect = dataFactory.connection();
		String query = "select * from study where no = ?";
		try {
			pstmt = connect.prepareStatement(query);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				studyDetail.setNo(rs.getInt("no"));
				studyDetail.setName(rs.getString("name"));
				studyDetail.setDetail(rs.getString("detail"));
				studyDetail.setStudyPwd(rs.getString("study_pwd"));
				studyDetail.setMaxNum(rs.getInt("max_num"));
				studyDetail.setCreatedDate(rs.getDate("created_date"));
				studyDetail.setUpdatedDate(rs.getDate("update_date"));
				studyDetail.setCreateUserId(rs.getString("create_user_id"));
				studyDetail.setCategory(rs.getString("category"));
			}
			
		} catch (SQLException e) {
			System.err.println("studyDetail ERR : " + e.getMessage());
		}
		finally {
			dataFactory.close(connect, pstmt, null);
		}
		
		return studyDetail;
	}
	
	/**
	 * 스터디 등록
	 * 
	 * @param  StudyDTO
	 * @return boolean
	 * **/
	public boolean insertStudy(StudyDTO s) {
		boolean state = false;
		try {
			connect = dataFactory.connection();

			String query = "INSERT INTO study (name, detail, study_pwd, max_num, category, created_date, update_date, create_user_id)" + " VALUES (?, ?, ?, ?, ?, now(), now(), ?)";
			
			
			String name = s.getName();
			String detail = s.getDetail();
			String studyPwd = s.getStudyPwd();
			int maxNum = s.getMaxNum();
			String category = s.getCategory();
//			System.out.println(query);
			
			pstmt = connect.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, detail);
			pstmt.setString(3, studyPwd);
			pstmt.setInt(4, maxNum);
			pstmt.setString(5, category);
			pstmt.setString(6, s.getCreateUserId());
//			System.out.println(pstmt);
			int rowsAffected = pstmt.executeUpdate();
			
			if(rowsAffected == 1) {
				state = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			dataFactory.close(connect, pstmt, null);
		} 
		return state;
	}	
	
	/**
	 * 스터디 수정
	 * 
	 * @param  StudyDTO
	 * @return boolean
	 * **/
	public boolean updateStudy(StudyDTO studyDTO) {
		boolean state = false;
		
		connect = dataFactory.connection();
		String query = "UPDATE study " +
                "SET name = ?, detail = ?, study_pwd = ?, max_num = ?, category = ?, update_date = NOW() " +
                "WHERE no = ?";
		try {
			pstmt = connect.prepareStatement(query);
			pstmt.setString(1, studyDTO.getName());
			pstmt.setString(2, studyDTO.getDetail());
			pstmt.setString(3, studyDTO.getStudyPwd());
			pstmt.setInt(4, studyDTO.getMaxNum());
			pstmt.setString(5, studyDTO.getCategory());
			pstmt.setInt(6, studyDTO.getNo());			
			int n = pstmt.executeUpdate();
			
			if(n > 0) {
				state = true;
			}
			
		} catch (SQLException e) {
			System.err.println("updateStudy ERR : " + e.getMessage());
		}
		finally {
			dataFactory.close(connect, pstmt, null);
		}
		return state;
	}
	
	/**
	 * 스터디 삭제
	 * 
	 * @param  int no
	 * @return boolean
	 * **/
	public boolean deleteStudy(int no) {
		boolean state = false;
		connect = dataFactory.connection();
		String query = "DELETE FROM study WHERE no = ?";
		
		try {
			pstmt = connect.prepareStatement(query);
			pstmt.setInt(1, no);
			int n = pstmt.executeUpdate();
			
			if(n > 0) {
				state = true;
			}
			
		} catch (SQLException e) {
			System.err.println("deleteStudy ERR : " + e.getMessage());
		}
		finally {
			dataFactory.close(connect, pstmt, null);
		}
		
		return state;
	}
	

}
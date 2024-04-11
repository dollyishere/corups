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
	
//	public ArrayList<StudyDTO> studyList() {
//		ArrayList<StudyDTO> studyList = new ArrayList<StudyDTO>();
//		try {
//			connect = dataFactory.connection();
//			String query = "select * from study order by no desc";
//			System.out.println(query);
//			
//			pstmt = connect.prepareStatement(query);
//			rs = pstmt.executeQuery();
//			
//			while (rs.next()) {
//				int no = rs.getInt("no");
//				String name = rs.getString("name");
//				String detail = rs.getString("detail");
//				String study_pwd = rs.getString("study_pwd");
//				int max_num = rs.getInt("max_num");
//				Date created_date = rs.getDate("created_date");
//				Date update_date = rs.getDate("updated_date");
//				String create_user_id = rs.getString("create_user_id");
//				String category = rs.getString("category");
//				
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return studyList;
//	}
//	
	
	
	
	
	
	
	/**
	 * 내 스터디 목록 조회
	 * 
	 * @param int no(member)
	 * @return ArrayList<StudyDTO>
	 * **/
	
//	public ArrayList<studyDTO> myStudyList(memberDTO no) {
//		ArrayList<StudyDTO> myStudyList = new ArrayList<StudyDTO>();
//		return myStudyList;
//		
//	}
	
	/**
	 * 스터디 검색
	 * 
	 * @param String searchText, String category
	 * @return ArrayList<StudyDTO>
	 * **/
	public ArrayList<StudyDTO> searchStudy(String searchText, String category) {
		ArrayList<StudyDTO> studySearch = new ArrayList<StudyDTO>();
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
			String name = s.getName();
			String detail = s.getDetail();
			String studyPwd = s.getStudyPwd();
			int maxNum = s.getMaxNum();
			String category = s.getCategory();
			String query = "INSERT INTO study (name, detail, study_pwd, max_num, category)" + " VALUES (?, ?, ?, ?, ?	)";
			System.out.println(query);
			
			pstmt = connect.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, detail);
			pstmt.setString(3, studyPwd);
			pstmt.setInt(4, maxNum);
			pstmt.setString(5, category);
			int rowsAffected = pstmt.executeUpdate();
			
			if(rowsAffected == 1) {
				state = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
		return state;
	}
	

}

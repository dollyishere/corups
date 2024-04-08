package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.StudyDTO;

public class StudyDAO {
	private Connection connector = null;
//	private MySQLConnector datasource = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
//	public StudyDAO {
//		datasource = new MySQLConnector();
//	}

	/**
	 * 스터디 목록 조회
	 * 
	 * @param 
	 * @return ArrayList<StudyDTO>
	 * **/
	public ArrayList<StudyDTO> studyList() {
		ArrayList<StudyDTO> studyList = new ArrayList<StudyDTO>();
		return studyList;
	}
	
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
	public boolean insertStudy(StudyDTO studydto) {
		boolean state = false;
		return state;
	}
	
	/**
	 * 스터디 수정
	 * 
	 * @param  StudyDTO
	 * @return boolean
	 * **/
	public boolean updateStudy(StudyDTO studydto) {
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

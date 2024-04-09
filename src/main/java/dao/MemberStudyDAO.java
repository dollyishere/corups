package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.MySQLConnector;
import dto.MemberStudyDTO;

/**
 * member & study DAO
 * @author ljy
 * @since 2024-04-08
 * **/
public class MemberStudyDAO {
	private Connection connector = null;
	private MySQLConnector datasource = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public MemberStudyDAO() {
		datasource = new MySQLConnector();
	} // MemberStudyDAO() END
	
	/**
	 * get member's ids from studyNo
	 * @param int studyNo
	 * @return ArrayList<String>
	 * **/
	public List<String> memberIdList(int studyNo) {
		List<String> memberIdList = new ArrayList<String>();
		
		try {
			connector = datasource.connection();
			String query = "select * from member_study where study_no = ? order by join_date";
			
			pstmt = connector.prepareStatement(query);
			pstmt.setInt(1, studyNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memberIdList.add(rs.getString("member_id"));
			}
			
		} catch (SQLException e) {
			System.err.println("memberIdList(): " + e.getMessage());
		} finally {
			datasource.close(connector, pstmt, rs);
		}
		
		return memberIdList;
	} // memberIdList() END

	/**
	 * get study numbers list from memberId
	 * @param String memberId
	 * @return ArrayList<Integer>
	 * **/
	public List<Integer> studyNoList(String memberId) {
		List<Integer> studyNoList = new ArrayList<Integer>();
		
		try {
			connector = datasource.connection();
			String query = "select * from member_study where member_id = ? order by join_date";
			
			pstmt = connector.prepareStatement(query);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int studyNo = rs.getInt("study_no");
			    if (!rs.wasNull()) {
			        studyNoList.add(studyNo);
			    }
			}
			
		} catch (SQLException e) {
			System.err.println("memberIdList(): " + e.getMessage());
		} finally {
			datasource.close(connector, pstmt, rs);
		}
		
		return studyNoList;
	} // studyNoList() END
}

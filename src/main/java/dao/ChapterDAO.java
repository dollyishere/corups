package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.MySQLConnector;
import dto.ChapterDTO;

/**
 * Chapter DAO
 * 
 * @author cyb
 * @since 2024-04-08
 **/
public class ChapterDAO {
	private Connection conn = null;
	private MySQLConnector datasource = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public ChapterDAO() {
		datasource = new MySQLConnector();
	} // 기본 생성자

	/**
	 * chapter 목록보기 List chapter
	 * @param int study_no
	 * @return ArrayList<ChapterDTO>
	 */
	public ArrayList<ChapterDTO> chapterList(int studyNo) {
		ArrayList<ChapterDTO> chapterList = new ArrayList<ChapterDTO>();

		try {
			conn = datasource.connection();
			String query = "select * from chapter order by no";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			ChapterDTO chapter = null;
			while (rs.next()) {
				chapter = new ChapterDTO();
				chapter.setNo(rs.getInt("no"));
				chapter.setStudyNo(rs.getInt("study_no"));
				chapter.setName(rs.getString("name"));
				chapter.setCreatedDate(rs.getDate("created_date"));
				chapter.setUpdateDate(rs.getDate("start_date"));
				chapter.setStartDate(rs.getDate("start_date"));
				chapter.setEndDate(rs.getDate("end_date"));

				chapterList.add(chapter);

			}

		} catch (SQLException e) {
			System.err.println("chapterList():" + e.getMessage());
		}
		return chapterList;
	}

	/**
	 * chapter 상세보기 Insert chapter
	 * 
	 * @param int no
	 * @return ChapterDTO chapter
	 */
	public ChapterDTO chapterDetail(int no) {
		ChapterDTO chapter = new ChapterDTO();

		try {
			conn = datasource.connection();
			String query = "select * from chapter where no=? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chapter = new ChapterDTO();
				chapter.setNo(rs.getInt("no"));
				chapter.setStudyNo(rs.getInt("study_no"));
				chapter.setName(rs.getString("name"));
				chapter.setCreatedDate(rs.getDate("created_date"));
				chapter.setUpdateDate(rs.getDate("start_date"));
				chapter.setStartDate(rs.getDate("start_date"));
				chapter.setEndDate(rs.getDate("end_date"));
			}

		} catch (SQLException e) {
			System.err.println("chapterDetail():" + e.getMessage());
		}
		return chapter;
	}

	/**
	 * chapter 생성하기 Insert chapter
	 * 
	 * @param ChapterDTO chapter
	 * @return boolean
	 */
	public boolean insertChapter(ChapterDTO chapter) {
		boolean state = false;
		try {
			conn = datasource.connection();
			String query = "insert into chapter where no=?, study_no=? , name=?, created_date=now(), update_date=now(), start_date=?, end_date=? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, chapter.getNo());
			pstmt.setInt(2, chapter.getStudyNo());
			pstmt.setString(3, chapter.getName());
			pstmt.setDate(4, chapter.getCreatedDate());
			pstmt.setDate(5, chapter.getUpdateDate());
			pstmt.setDate(6, chapter.getStartDate());
			pstmt.setDate(7, chapter.getEndDate());

			rs = pstmt.executeQuery();

			int n = pstmt.executeUpdate();
			if (n > 1) {
				state = true;
			}
		} catch (Exception e) {
			System.err.println("insertChapter():" + e.getMessage());
		}
		return state;
	}

	/**
	 * chapter 수정하기 Update chapter
	 * 
	 * @param ChapterDTO chapter
	 * @return boolean
	 */
	public boolean updateChapter(int no) {
		ChapterDTO chapter = new ChapterDTO();
		boolean state = false;
		try {
			conn = datasource.connection();
			String query = "update chapter set name=?, start_date=?, end_date=? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, chapter.getNo());
			pstmt.setInt(2, chapter.getStudyNo());
			pstmt.setString(3, chapter.getName());
			pstmt.setDate(4, chapter.getCreatedDate());
			pstmt.setDate(5, chapter.getUpdateDate());
			pstmt.setDate(6, chapter.getStartDate());
			pstmt.setDate(7, chapter.getEndDate());

			int n = pstmt.executeUpdate();
			if (n > 1) {
				state = true;
			}
		} catch (Exception e) {
			System.err.println("updateChapter():" + e.getMessage());
		} finally {

		}
		return state;

	}

	/**
	 * chapter 삭제하기 Delete chapter
	 * @param int no
	 * @return boolean
	 */
	public boolean deleteChapter(int no) {
		ChapterDTO chapter = new ChapterDTO();
		boolean state = false;

		try {
			conn = datasource.connection();
			String query = "delete from chapter where no=?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, chapter.getNo());

			int n = pstmt.executeUpdate();
			if (n > 0) {
				state = true;
			}

		} catch (Exception e) {
			System.err.println("deleteChapter():" + e.getMessage());
		}
		return state;

	}
	/**
	 * 사용한 객체 닫음
	 * @param rs, pstmt, conn
	 */
	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

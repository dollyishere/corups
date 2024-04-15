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
	        String query = "SELECT * FROM chapter WHERE study_no = ?";

	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, studyNo);
//	        pstmt.setInt(2, (pageInfo.getPageNum() - 1) * pageInfo.getListCount());
//	        pstmt.setInt(3, pageInfo.getListCount());
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
	    } finally {
	        close(rs, pstmt, conn);
	    }
	    return chapterList;
	    
	    
	}
	/**
	 * chapter 목록-studyName
	 * @param int study_no
	 * @return ArrayList<ChapterDTO>
	 */
	public ArrayList<ChapterDTO> chapterListWithStudyName(int studyNo) {
	    ArrayList<ChapterDTO> chapterList = new ArrayList<ChapterDTO>();

	    try {
	        conn = datasource.connection();
	        String query = "SELECT c.*, s.name AS study_name " +
	                       "FROM chapter c " +
	                       "JOIN study s ON c.study_no = s.no " +
	                       "WHERE c.study_no = ?";

	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, studyNo);
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            ChapterDTO chapter = new ChapterDTO();
	            chapter.setNo(rs.getInt("no"));
	            chapter.setStudyNo(rs.getInt("study_no"));
	            chapter.setName(rs.getString("name"));
	            chapter.setCreatedDate(rs.getDate("created_date"));
	            chapter.setUpdateDate(rs.getDate("start_date"));
	            chapter.setStartDate(rs.getDate("start_date"));
	            chapter.setEndDate(rs.getDate("end_date"));
	            chapter.setStudyName(rs.getString("study_name")); // 스터디 이름 설정
	            System.out.println(rs.getString("study_name"));

	            chapterList.add(chapter);
	        }

	    } catch (SQLException e) {
	        System.err.println("chapterListWithStudyName():" + e.getMessage());
	    } finally {
	        close(rs, pstmt, conn);
	    }
	    return chapterList;
	}


	/**
	 * chapter 총 갯수 조회 chapterCount
	 * @param ChapterDTO chapter
	 * @return 
	 */
	public int chapterCount(int studyNo) {
	    int chapterCount = 0;
	    try {
	        conn = datasource.connection();
	        String query = "SELECT COUNT(no) AS total FROM chapter WHERE study_no = ?";

	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, studyNo);
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            chapterCount = rs.getInt("total");
	        }

	    } catch (SQLException e) {
	        System.err.println("chapterCount():" + e.getMessage());
	    } finally {
	        close(rs, pstmt, conn);
	    }
	    return chapterCount; //결과 값 반환 (chapterListServlet의 doGet()에게	
	}

	/**
	 * chapter 상세보기 chapterDetail	 * 
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
				chapter.setUpdateDate(rs.getDate("update_date"));
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
	 * @param ChapterDTO chapter
	 * @return boolean
	 */
	public boolean insertChapter(ChapterDTO chapter) {
		boolean state = false;
		try {
			conn = datasource.connection();
			String query = "insert into chapter (study_no, name, created_date, update_date, start_date, end_date) values(?,?,now(),now(),?,?)";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, chapter.getStudyNo());
			pstmt.setString(2, chapter.getName());
			pstmt.setDate(3, chapter.getStartDate());
			pstmt.setDate(4, chapter.getEndDate());

			int n = pstmt.executeUpdate();
			System.out.println(n);
			if (n > 0) {
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
	public boolean updateChapter(ChapterDTO chapter) {
		boolean state = false;
		try {
			conn = datasource.connection();
			String query = "update chapter set name=?, start_date=?, end_date=? update_date=now() where no=?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, chapter.getName());
			pstmt.setDate(2, chapter.getStartDate());
			pstmt.setDate(3, chapter.getEndDate());


			int n = pstmt.executeUpdate();
			if (n > 1) {
				state = true;
			}
		} catch (Exception e) {
			System.err.println("updateChapter():" + e.getMessage());
		} finally {
			close(rs, pstmt, conn);
		}
		return state;

	}

	/**
	 * chapter 삭제하기 Delete chapter
	 * @param int no
	 * @return boolean
	 */
	public boolean deleteChapter(int no) {
		boolean state = false;

		try {
			conn = datasource.connection();
			String query = "delete from chapter where no=?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);

			int n = pstmt.executeUpdate();
			if (n > 0) {
				state = true;
			}

		} catch (Exception e) {
			System.err.println("deleteChapter():" + e.getMessage());
		} finally {
			
		}
		return state;

	}
	/**
	 * 사용한 객체 닫음
	 * @param rs, pstmt, conn
	 */
	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
	    try {
	        if (rs != null) {
	            rs.close();
	        }
	        if (pstmt != null) {
	            pstmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    } catch (SQLException e) {
	        System.err.println("close():" + e.getMessage());
	    }
	}


}

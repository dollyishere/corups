package dao;

/**
 * Chapter DAO
 * @author cyb
 * @since 2024-04-08
 * **/
import java.util.ArrayList;

import dto.ChapterDTO;

public class ChapterDAO {

	public ChapterDAO() {
	} // 기본 생성자

	/**
	 * chapter 목록보기
	 * List chapter
	 * 
	 * @param int study_no
	 * @return ArrayList<ChapterDTO>
	 */
	public ArrayList<ChapterDTO> chapterList(int study_no) {
		ArrayList<ChapterDTO> chapterArray = null;

		return chapterArray;
	}

	/**
	 * chapter 상세보기 
	 * Insert chapter
	 * 
	 * @param int no
	 * @return ChapterDTO chapter
	 */
	public ChapterDTO chapterDetail(int no) {
		ChapterDTO chapter = null;

		return chapter;
	}

	/**
	 * chapter 생성하기 
	 * Insert chapter
	 * 
	 * @param ChapterDTO chapter
	 * @return boolean
	 */
	public boolean insertChapter(ChapterDTO chapter) {
		try {
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * chapter 수정하기 
	 * Update chapter
	 * 
	 * @param ChapterDTO chapter
	 * @return boolean
	 */
	public boolean updateChapter(int no) {

		try {
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * chapter 삭제하기
	 * Delete chapter
	 * 
	 * @param int no
	 * @return boolean
	 */
	public boolean deleteChapter(int no) {

		try {
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}

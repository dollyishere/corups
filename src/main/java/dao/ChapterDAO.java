package dao;
/**
 * Chapter DAO
 * @author cyb
 * @since 2024-04-08
 * **/
import java.util.ArrayList;

import dto.ChapterDTO;

public class ChapterDAO {

	public ChapterDAO() {} // 기본 생성자

	/** chapter 목록보기 **/
	public ArrayList<ChapterDTO> chapterList(int study_no) {
		ArrayList<ChapterDTO> chapterArray = null;

		return chapterArray;
	}
	
	/** chapter 상세보기 **/
	public ChapterDTO chapterDetail(int no) {
		ChapterDTO chapter = null;

		return chapter;
	}

	/** chapter 생성하기 **/
	public boolean insertChapter(ChapterDTO chapter) {
		try {
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	/** chapter 수정하기 **/
	public boolean updateChapter(int no) {
	
		try {
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	
	/** chapter 삭제하기 **/
	public boolean deleteChapter(int no) {

		try {
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}

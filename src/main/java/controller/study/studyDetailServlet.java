package controller.study;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChapterDAO;
import dto.ChapterDTO;

/**
 * /chapter/ChapterListServlet
 * @since 2024.04.09
 * @author cyb
 */
@WebServlet("/chapter/Servlet")
public class studyDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ChapterDAO chapterDAO = null;
    
    public studyDetailServlet() {
        super();
    }

	/** chapterDAO에서 해당 스터디 귀속 챕터 전체 목록 조회 =>
	 * studyList.jsp로 이동 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String pageNum = request.getParameter("pageNum");
//	if(pageNum == null) {
//		pageNum ="1";
//	}
	
	// 모델 (ChapterDAO의 메서드에게 전달할 데이터를 하나의 객체로 )
	ChapterDTO chapter = new ChapterDTO();
	chapter.setPageNum(pageNum);
	
	// DAO (DB의 테이블에 접속하여 쿼리를 실행할 수 있는 메서드 보유 객체 생성) 
	chapterDAO = new ChapterDAO();
	
//	// 챕터 총 수(목록 아래에 페이지 번호를 계산하기 위한 메서드 호출)
//	int chapterCount = chapterDAO.chapterList();
	
	// 챕터 목록을 얻는 쿼리 실행 (결과값을 ArrayList<ChapterDTO>로 반환 받음)

	
	int studyNo = 0;
	if (request.getParameter("studyNo") != null) {
		 studyNo = Integer.parseInt(request.getParameter("studyNo"));
	}
	
	ArrayList<ChapterDTO> chapterList = chapterDAO.chapterList(studyNo);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

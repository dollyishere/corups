package controller.chapter;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChapterDAO;
import dto.ChapterDTO;

/**
 * /chapter/ChapterDetailServlet
 * 
 * @since 2024.04.09
 * @author cyb
 */

@WebServlet("/chapter/chapterDetailServlet")
public class chapterDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ChapterDAO chapterDAO = null;

	public chapterDetailServlet() {
		super();

	}

	/**
	 * chapterDAO에서 해당 no가진 챕터 상세 정보 조회 => chapterDetail.jsp로 이동
	 * 
	 * study_no 파라미터 받기 study_no로 chapter 상세정보 조회 세션에 id가져오기 create_user_id가져오기 id와
	 * chapter.create_user_id가 같으면 /mgr/chapterDetail.jsp로 이동 아니면
	 * /chapterDetail.jsp로 이동
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 파라미터
		int no = Integer.parseInt(request.getParameter("no"));
		int studyNo = Integer.parseInt(request.getParameter("studyNo"));
		String pageNum = request.getParameter("pageNum");
		ChapterDTO chapter = new ChapterDTO();
		chapter.setNo(no);
		chapter.setPageNum(pageNum); // pageNum 변수 수정 필요
		chapter.setStudyNo(studyNo);

		// 챕터 상세 조회
		chapterDAO = new ChapterDAO();
		chapter = chapterDAO.chapterDetail(no); 

		request.setAttribute("chapter", chapter);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("./mgr/chapterDetail.jsp");
		requestDispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// x
	}

}

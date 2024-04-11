package controller.chapter;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChapterDAO;
import dto.ChapterDTO;

/**
 * /chapter/ChapterRegisterServlet
 * 
 * @since 2024.04.09
 * @author cyb
 */
@WebServlet("/chapter/chapterRegisterServlet")
public class chapterRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ChapterDAO chapterDAO = null;

	public chapterRegisterServlet() {
		super();

	}

	/** chapterRegister.jsp로 이동 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/mgr/chapterRegister.jsp");
		requestDispatcher.forward(request, response);
	}

	/** chapterDAO에서 챕터 등록 => error.jsp나 studyDetail.jsp로 이동 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// POST 한글 파라미터 깨짐 처리
		request.setCharacterEncoding("UTF-8");

		// 파라미터
		//int studyNo = 1;// 임시로 넣어줌 나중에 삭제~!~!~!
		String studyNoParam ="1";
		//String studyNoParam = request.getParameter("studyNo");
		int studyNo = 0;
		if (studyNoParam != null && !studyNoParam.isEmpty()) {
		    studyNo = Integer.parseInt(studyNoParam);
		} else {
		    // 스터디 번호가 없는 경우에 대한 에러 처리
		    response.sendRedirect(request.getContextPath() + "/errorLog.jsp");
		    System.err.println("등록error!!");
		    return;
		}
		//int studyNo = Integer.parseInt(request.getParameter("studyNo"));
		String name = request.getParameter("chapterName");
		Date startDate = Date.valueOf(request.getParameter("startDate")); // 사용자가 입력한 시작일
		Date endDate = Date.valueOf(request.getParameter("endDate")); // 사용자가 입력한 시작일

		// Chapter객체 생성
		ChapterDTO chapter = new ChapterDTO();
		chapter.setStudyNo(studyNo);
		chapter.setName(name);

		chapter.setStartDate(startDate);
		chapter.setEndDate(endDate);

		// 챕터 추가
		chapterDAO = new ChapterDAO();
		boolean complete = chapterDAO.insertChapter(chapter);
		request.setAttribute("bool", complete);

		// 챕터 등록 성공 시 챕터 목록 페이지로 이동
		if (complete) {
			// 등록 성공 시 챕터 목록 페이지로 이동
			System.out.println(complete);
			response.sendRedirect("chapterListServlet?studyNo=" + studyNo);
		} else {
		    response.sendRedirect(request.getContextPath() + "/errorLog.jsp");
			System.err.println("등록error!!");

		}

	}
}

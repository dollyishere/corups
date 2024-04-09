package controller.chapter;

import java.io.IOException;
import java.util.Date;

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
 * @since 2024.04.09
 * @author cyb
 */
@WebServlet("/chapter/ChapterRegisterServlet")
public class chapterRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ChapterDAO chapterDAO = null;

	public chapterRegisterServlet() {
		super();

	}

	/** chapterRegister.jsp로 이동 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/mgr/chapterRegiser.jsp");
		requestDispatcher.forward(request, response);
	}

	/**chapterDAO에서 챕터 등록 => error.jsp나 studyDetail.jsp로 이동  */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// POST 한글 파라미터 깨짐 처리
		request.setCharacterEncoding("UTF-8");

		// 파라미터
		int no = Integer.parseInt(request.getParameter("no"));
		int studyNo = Integer.parseInt(request.getParameter("studyNo"));
		String name = request.getParameter("name");
		Date createdDate = new Date();
		Date updateDate = new Date();
		Date startDate = new Date();
		Date endDate = new Date();

		// Chapter객체 생성
		ChapterDTO chapter = new ChapterDTO();
		chapter.setNo(no);
		chapter.setStudyNo(studyNo);


		// 챕터 추가
		boolean complete = chapterDAO.insertChapter(chapter);
		request.setAttribute("bool", complete);

		// chapterRegister에서 추가 버튼 클릭시
		response.sendRedirect("/mgr/studyDetail.jsp");

	}

}

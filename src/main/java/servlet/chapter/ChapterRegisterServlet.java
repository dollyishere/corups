package servlet.chapter;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
public class ChapterRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ChapterDAO chapterDAO = null;

	public ChapterRegisterServlet() {
		super();

	}

	/** chapterRegister.jsp로 이동 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String studyNo = request.getParameter("studyNo");
		request.setAttribute("studyNo", studyNo);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/mgr/chapterRegister.jsp");
		requestDispatcher.forward(request, response);
	}

	/** chapterDAO에서 챕터 등록 => error.jsp나 studyDetail.jsp로 이동 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// POST 한글 파라미터 깨짐 처리
		request.setCharacterEncoding("UTF-8");

		// 파라미터
//		String studyNoParam ="1";
		String studyNoParam = request.getParameter("studyNo");
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
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date startUtilDate = new Date(System.currentTimeMillis());
        java.util.Date endUtilDate = new Date(System.currentTimeMillis());
        try {
        	startUtilDate = format.parse(request.getParameter("startDate"));
        	endUtilDate = format.parse(request.getParameter("endDate"));
        } catch (ParseException e) {
           System.out.println(e.getMessage());
        }

		// Chapter객체 생성
		ChapterDTO chapter = new ChapterDTO();
		chapter.setStudyNo(studyNo);
		chapter.setName(name);
		chapter.setStartDate((java.sql.Date) startDate);
		chapter.setEndDate((java.sql.Date) endDate);
		
		// session에 챕터 정보 저장
		request.getSession().setAttribute("chapter", chapter);

		// chapterNo도 hidden input으로 추가
		request.setAttribute("chapterNo", chapter.getNo());

		// 챕터 추가
		chapterDAO = new ChapterDAO();
		boolean complete = chapterDAO.insertChapter(chapter);
		request.setAttribute("bool", complete);

		// 챕터 등록 성공 시 챕터 목록 페이지로 이동
//		response.setContentType("text/plain");
		if (complete) {
			// 등록 성공 시 챕터 목록 페이지로 이동
			System.out.println(complete);
			   response.sendRedirect(request.getContextPath() + "/study/studyDetailServlet?studyNo=" + studyNo);

		} else {
		    response.sendRedirect(request.getContextPath() + "/errorLog.jsp");
			System.err.println("등록error!!");

		}
		// 서버에서 응답 데이터에 studyNo를 포함하여 전송
		// 등록 성공 시 "success" 문자열을, 실패 시 "fail" 문자열을 응답으로 전송한다고 가정합니다.
	

	}
}

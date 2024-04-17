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
import dao.StudyDAO;
import dto.ChapterDTO;
import dto.StudyDTO;

/**
 * /chapter/chapterUpdateServlet
 * 
 * @since 2024.04.09
 * @author cyb
 */
@WebServlet("/chapter/chapterUpdateServlet")
public class ChapterUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/** ChapterDAO */
	private ChapterDAO chapterDAO = null;
	private StudyDAO studyDAO = new StudyDAO();

	public ChapterUpdateServlet() {
		super();
		chapterDAO = new ChapterDAO(); // ChapterDAO 객체 초기화

	}

	/** chapterDAO에서 해당 no가진 챕터 상세 정보 조회 => chapterUpdate.jsp로 이동 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 수정할 챕터 정보 가져오기
		String chapterNoParam = request.getParameter("chapterNo");
		if (chapterNoParam == null || chapterNoParam.isEmpty()) {
			System.err.println("chapterNo NO!!");
			return;
		}
		
		int chapterNo = Integer.parseInt(chapterNoParam);
		ChapterDTO chapter = chapterDAO.chapterDetail(chapterNo);
		
//		chapter.setNo(no);
		
		//study 정보 가져오기
		int studyNo = chapter.getStudyNo();
		StudyDTO study = studyDAO.studyDetail(studyNo);
		
		// View 사용될 객체 설정
		request.setAttribute("chapter", chapter);
		request.setAttribute("study", study);
		

		// View로 보내기
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/mgr/chapterUpdate.jsp");
		requestDispatcher.forward(request, response);
	}

	/** chapterDAO에서 챕터 수정 => error.jsp나 chapterDetail.jsp로 이동 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

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
		int chapterNo = Integer.parseInt(request.getParameter("chapterNo"));
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

		// Chapter객체 업데이트
		ChapterDTO chapter = new ChapterDTO();
		chapter.setNo(chapterNo);
		chapter.setName(name);
		chapter.setStartDate(startDate);
		chapter.setEndDate(endDate);
		chapter.setStudyNo(studyNo);

//			chapter.setStartDate((java.sql.Date) startDate);
//			chapter.setEndDate((java.sql.Date) endDate);

		// 챕터 update
		chapterDAO = new ChapterDAO();
		boolean complete = chapterDAO.updateChapter(chapter);
		request.setAttribute("bool", complete);

		// 챕터 등록 성공 시 챕터 목록 페이지로 이동
//			response.setContentType("text/plain");
		if (complete) {
			// 등록 성공 시 챕터 목록 페이지로 이동
			System.out.println(complete);
			response.sendRedirect(request.getContextPath() + "/study/studyDetailServlet?studyNo=" + studyNo);

		} else {
			response.sendRedirect(request.getContextPath() + "/errorLog.jsp");
			System.err.println("등록error!!");

			// error.jsp 또는 chapterDetailServlet으로 이동
			response.sendRedirect("/mgr/chapterDetailServlet");

		}

	}
}

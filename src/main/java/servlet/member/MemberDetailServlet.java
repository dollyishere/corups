package servlet.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ChapterDAO;
import dao.StatusDAO;
import dao.StudyDAO;
import dao.TodoDAO;
import dto.ChapterDTO;
import dto.StatusDTO;
import dto.StudyDTO;
import dto.TodoDTO;

/**
 * 회원 상세 조회(main.jsp) controller
 * @author 임주연
 * @since 2024-04-09
 */
@WebServlet("/member/memberDetailServlet")
public class MemberDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudyDAO studyDAO = null;
	private TodoDAO todoDAO = null;
	private StatusDAO statusDAO = null;
	private ChapterDAO chapterDAO = null;
	
    public MemberDetailServlet() {
        super();
    } // 생성자 END

	/**
	 * GET 요청 수행(메인 화면으로 이동)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 1. 내 스터디 목록 받아오기
		 * 2. 내 todoList 받아오기
		 * **/
		
		HttpSession session = request.getSession(false);
		String id = (String) session.getAttribute("id");
		
		studyDAO = new StudyDAO();
		todoDAO = new TodoDAO();
		
		List<StudyDTO> myStudyList = studyDAO.myStudyList(id);
		
		statusDAO = new StatusDAO();
		List<StatusDTO> statusList = statusDAO.todoNoList(id);
		
		todoDAO = new TodoDAO();
		List<TodoDTO> todoList = new ArrayList<TodoDTO>();
		for(int i = 0; i < statusList.size(); i++) {
			TodoDTO todo = todoDAO.todoDetail(statusList.get(i).getTodoNo());
			todoList.add(todo);
		}
		
		chapterDAO = new ChapterDAO();
		List<ChapterDTO> chapterList = new ArrayList<ChapterDTO>();
		for(int i = 0; i < todoList.size(); i++) {
			ChapterDTO chapter =chapterDAO.chapterDetail(todoList.get(i).getChapterNo());
			chapterList.add(chapter);
		}
		
		List<StudyDTO> todoStudyList = new ArrayList<StudyDTO>();
		for(int i = 0; i < chapterList.size(); i++) {
			StudyDTO study = studyDAO.studyDetail(chapterList.get(i).getStudyNo());
			todoStudyList.add(study);
		}
		
		request.setAttribute("myStudyList", myStudyList);
		request.setAttribute("statusList", statusList);
		request.setAttribute("todoList", todoList);
		request.setAttribute("todoStudyList", todoStudyList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/mem/main.jsp");
		requestDispatcher.forward(request, response);
	} // doGET() END

	/**
	 * POST 요청 수행
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	} // doPOST() END

}

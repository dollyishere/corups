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

import dao.StatusDAO;
import dao.StudyDAO;
import dao.TodoDAO;
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
	
    public MemberDetailServlet() {
        super();
    } // 생성자 END

	/**
	 * GET 요청 수행(메인 화면으로 이동)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String id = (String) session.getAttribute("id");
		
		studyDAO = new StudyDAO();
		todoDAO = new TodoDAO();
		
		List<StudyDTO> myStudyList = studyDAO.myStudyList(id);
		statusDAO = new StatusDAO();
		List<StatusDTO> statusList = statusDAO.todoNoList(id);
		for(int i = 0; i < statusList.size(); i++) {
			System.out.println(statusList.get(i));
		}
		
		todoDAO = new TodoDAO();
		List<TodoDTO> todoList = new ArrayList<TodoDTO>();
		for(int i = 0; i < statusList.size(); i++) {
			TodoDTO todo = todoDAO.todoDetail(statusList.get(i).getTodoNo());
			todoList.add(todo);
			System.out.println(todo);
		}
		
		request.setAttribute("myStudyList", myStudyList);
		request.setAttribute("todoList", todoList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/mem/main.jsp");
		requestDispatcher.forward(request, response);
	} // doGET() END

	/**
	 * POST 요청 수행
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	} // doPOST() END

}

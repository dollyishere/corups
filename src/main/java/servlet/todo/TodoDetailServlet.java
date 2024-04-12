package servlet.todo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StatusDAO;
import dao.TodoDAO;
import dto.StatusDTO;
import dto.TodoDTO;
import utils.SessionUtil;

/**
 * /todo/TodoDetailServlet
 * 
 * @author nsr
 * @since 2024-04-08
 **/
@WebServlet("/todoDetailServlet")
public class TodoDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TodoDetailServlet() {
        super();
    }

    /**
     * 1. todo_no 파라미터 받기
     * 2. todo_no으로 todo 상세정보 조회
     * 3. 세션에 id 가져오기
     * 4. create_user_id 가져오기
     * 5. id 와 todo.create_user_id 가 같다면
     * 	 -> mgr/todoDetail.jsp 로 이동
     * 6. 아니면 -> todoDetail.jsp 로 이동
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		//  인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// 1. todo_no 파라미터 받기
		String todo_no_str = request.getParameter("todo_no");
		if(todo_no_str == null || todo_no_str == "")
			System.err.println("todo 너어어얼");
		int todo_no = Integer.parseInt(todo_no_str);
		
		// 2. todo_no으로 todo 상세정보 조회
		TodoDAO todoDAO = new TodoDAO();
		TodoDTO todo = todoDAO.todoDetail(todo_no);
		System.out.println(todo);
		
		// 3. 세션에 id 가져오기
		String id = SessionUtil.getID(request, response);
		
//		// Chapter 와 Study 정보 조회
//		ChapterDAO chapterDAO = new ChapterDAO();
//		ChapterDTO chapter = chapterDAO.chapterDetail(todo.getChapterNo());
//		
//		StudyDAO studyDAO = new StudyDAO();
//		StudyDTO study = studyDAO.studyDetail(chapter.getStudyNo());
		
		StatusDAO statusTodoDao = new StatusDAO();
		StatusDTO status = statusTodoDao.getStatus(id, todo.getNo());		
		
//		// 5. id 와 create_user_id 가 같다면
//		//		-> mgr/todoDetail.jsp 로 이동
		boolean mgr = false;
//		if(id.equals(study.getCreateUserId()))
			mgr = true;
		
//		request.setAttribute("study", study);
//		request.setAttribute("chapter", chapter);
		request.setAttribute("mgr", mgr);
		request.setAttribute("todo", todo);
		request.setAttribute("status", status.getStatus());
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("study/todoDetail.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	}

}

package servlet.todo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChapterDAO;
import dao.FileDAO;
import dao.StatusDAO;
import dao.StudyDAO;
import dao.TodoDAO;
import dto.ChapterDTO;
import dto.FileDTO;
import dto.StatusDTO;
import dto.StudyDTO;
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
		String todoNoStr = request.getParameter("todoNo");
		if(todoNoStr == null || todoNoStr.isEmpty())
			System.err.println("todo 너어어얼");
		int todoNo = Integer.parseInt(todoNoStr);
		
		// 2. todo_no으로 todo 상세정보 조회
		TodoDAO todoDAO = new TodoDAO();
		TodoDTO todo = todoDAO.todoDetail(todoNo);
		System.out.println("todo ==> ");
		System.out.println(todo);
		// 3. 세션에 id 가져오기
		String id = SessionUtil.getID(request, response);
		
		// Chapter 와 Study 정보 조회
		ChapterDAO chapterDAO = new ChapterDAO();
		ChapterDTO chapter = chapterDAO.chapterDetail(todo.getChapterNo());
		
		StudyDAO studyDAO = new StudyDAO();
		StudyDTO study = studyDAO.studyDetail(chapter.getStudyNo());
		
		StatusDAO statusTodoDao = new StatusDAO();
		StatusDTO status = statusTodoDao.getStatus(id, todo.getNo());		
		
		// todoNo 으로 fileDTO 배열 가져오기
		FileDAO fileDAO = new FileDAO();
		ArrayList<FileDTO> files = fileDAO.fileList(todoNo);		
		
		// 5. id 와 create_user_id 가 같다면
		//		-> mgr/todoDetail.jsp 로 이동
		String myTodoPage = request.getParameter("myTodoPage");
		
		boolean mgr = false;
		if(id.equals(study.getCreateUserId()) && myTodoPage == null)
			mgr = true;
		
		request.setAttribute("study", study);
		request.setAttribute("chapter", chapter);
		request.setAttribute("mgr", mgr);
		request.setAttribute("todo", todo);
		request.setAttribute("files", files);
		request.setAttribute("status", status.getStatus());
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("study/todoDetail.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	}

}

package servlet.todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChapterDAO;
import dao.MemberStudyDAO;
import dao.StatusDAO;
import dao.StudyDAO;
import dao.TodoDAO;
import dto.ChapterDTO;
import dto.StatusDTO;
import dto.StudyDTO;
import dto.TodoDTO;
import utils.SessionUtil;

/**
 * /todo/TodoSearchServlet
 * 
 * @author nsr
 * @since 2024-04-08
 **/
@WebServlet("/todoSearchServlet")
public class TodoSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TodoSearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	}

	/**
	 * doPost()
	 * 인코딩
	 * status, searchText 가져오기
	 * id 가져오기
	 * todoDAO.searchTodo(status, searchText, id);
	 * -> study/myTodoList.jsp
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		//  인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// status, searchText 가져오기
		String searchStatus = request.getParameter("searchStatus");
		if(searchStatus =="" || searchStatus == null)
			searchStatus = "ALL";
		String searchText = request.getParameter("searchText");

		String id = SessionUtil.getID(request, response);
		StatusDAO statusDAO = new StatusDAO();
		ArrayList<StatusDTO> statusArray = statusDAO.todoNoList(id);
		ArrayList<StatusDTO> statusArrayTmp = new ArrayList<StatusDTO>();  
		// todoDAO.searchTodo(status, searchText);
		TodoDAO todoDAO = new TodoDAO();
		ArrayList<TodoDTO> todoArray = todoDAO.searchTodo(searchText, statusArray);
		ArrayList<TodoDTO> todoArrayTmp = new ArrayList<TodoDTO>(); 
		

		StudyDAO studyDAO = new StudyDAO();
		ChapterDAO chapterDAO = new ChapterDAO();
		MemberStudyDAO memberStudyDAO = new MemberStudyDAO();
		
		for(int i = 0; i < todoArray.size(); i++) {
			TodoDTO todo = todoArray.get(i);
			ChapterDTO chapter = chapterDAO.chapterDetail(todo.getChapterNo());
			StudyDTO study = studyDAO.studyDetail(chapter.getStudyNo());
			List<String> memberIdList = memberStudyDAO.memberIdList(study.getNo());
			boolean myStudy = false;
			System.out.println("memberIdList ===> ");
			for(int j = 0; j < memberIdList.size(); j++) {
				System.out.println(memberIdList.get(j));
				if(memberIdList.get(j).equals(id)) {
					myStudy = true;
					break;
				}
			}
			if(myStudy) {
				statusArrayTmp.add(statusArray.get(i));
				todoArrayTmp.add(todo);
			}
		}
		
		
		
		//-> study/myTodoList.jsp
		request.setAttribute("searchStatus", searchStatus);
		request.setAttribute("searchText", searchText);
		request.setAttribute("todoArray", todoArrayTmp);
		request.setAttribute("statusArray", statusArrayTmp);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/study/myTodoList.jsp");
		requestDispatcher.forward(request, response);
	}

}

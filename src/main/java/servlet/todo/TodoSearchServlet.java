package servlet.todo;

import java.io.IOException;
import java.util.ArrayList;

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
		
		// todoDAO.searchTodo(status, searchText);
		TodoDAO todoDAO = new TodoDAO();
		ArrayList<TodoDTO> todoArray = todoDAO.searchTodo(searchText, statusArray);
		
		//-> study/myTodoList.jsp
		request.setAttribute("searchStatus", searchStatus);
		request.setAttribute("searchText", searchText);
		request.setAttribute("todoArray", todoArray);
		request.setAttribute("statusArray", statusArray);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/study/myTodoList.jsp");
		requestDispatcher.forward(request, response);
	}

}

package servlet.todo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StatusDAO;
import dao.TodoDAO;
import utils.SessionUtil;

/**
 * /todo/TodoDeleteServlet
 * 
 * @author nsr
 * @since 2024-04-08
 **/
@WebServlet("/todoDeleteServlet")
public class TodoDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TodoDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	}

	/**
	 * 인코딩
	 * todoNo 가져오기
	 * statusDAO.deleteStatus();
	 * todoDAO.deleteTodo();
	 * response.getWriter().write(result);
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		// 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// todoNo 가져오기
		String todoNoStr = request.getParameter("todoNo");
		int todoNo = Integer.parseInt(todoNoStr);
		
		// statusDAO.deleteStatus();
		StatusDAO statusDAO = new StatusDAO();
		boolean success = statusDAO.deleteStatus(todoNo, SessionUtil.getID(request, response));
		
		// todoDAO.deleteTodo();
		if(success) {			
			TodoDAO todoDAO = new TodoDAO();
			success = todoDAO.deleteTodo(todoNo);
		}
		
		// response.getWriter().write(result);
		String result = "실패";
		if(success)
			result = "성공";
		response.getWriter().write(result);
		
	}

}

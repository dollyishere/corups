package servlet.todo;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

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
 * /todo/TodoRegisterServlet
 * 
 * @author nsr
 * @since 2024-04-08
 **/
@WebServlet("/todoRegisterServlet")
public class TodoRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TodoRegisterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("mgr/todoRegister.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		// 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// get Parameter
		String name = request.getParameter("name");
		Date startDate = parseDate(request.getParameter("startDate"));
		Date  endDate = parseDate(request.getParameter("endDate"));
		String content = request.getParameter("content");
		
		// TodoDTO
		TodoDTO todo = new TodoDTO();
		todo.setChapterNo(1);
		todo.setDetail(content);
		todo.setEndDate(endDate);
		todo.setName(name);
		todo.setStartDate(startDate);
		
		// Insert todo
		TodoDAO todoDAO = new TodoDAO();
		int todoNo = todoDAO.insertTodo(todo);
		boolean success = (todoNo > 0);
		if(success) {
			// StatusDTO
			StatusDTO status = new StatusDTO();
			status.setMemberId(SessionUtil.getID(request, response));
			status.setStatus("P");
			status.setTodoNo(todoNo);
			
			// Insert status
			StatusDAO statusDAO = new StatusDAO();
			success = statusDAO.insertStatus(status);			
		}
		
		String result = "실패";
		if(success) {
			result = "성공";
			System.out.println("성공");
		}

		response.getWriter().write(result);
	}
	
	private Date parseDate(String dateStr) {
		Date date = null;
		if (dateStr != null && !dateStr.isEmpty()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 형식에 맞게 수정 가능
				java.util.Date utilDate = sdf.parse(dateStr);
				date = new Date(utilDate.getTime());
				System.out.println(date);
			} catch (Exception e) {
				e.printStackTrace();
				// 오류 처리를 원하는 방식으로 진행
			}
		}
		return date;
	}

}

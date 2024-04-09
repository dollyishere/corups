package servlet.todo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StatusTodoDAO;
import dao.TodoDAO;
import dto.StatusTodoDTO;
import dto.TodoDTO;

/**
 * /todo/todoListServlet
 * 
 * @author nsr
 * @since 2024-04-08
 **/
@WebServlet("/todoListServlet")
public class TodoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TodoListServlet() {
        super();
    }

	/** doGet()
	 * 1. 세션에서 아이디 받기
	 * 2. 2. id로 나의 status_todo 조회
	 * 3. 나의 status_todo의 todo_no 으로 나의 todo 리스트 만들기
	 * 4. 나의 statusTodo, 나의 todo 리스트 보내기 -> myTodoList.jsp
	**/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//  인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		/** 삭제해야할 코드 **/
		testSession(request, response);
		
		// 1. 세션에서 아이디 받기
		String id = getID(request, response);

		// 2. id로 나의 status_todo 조회
		StatusTodoDAO statusTodoDAO = new StatusTodoDAO();
		ArrayList<StatusTodoDTO> statusTodoArray = statusTodoDAO.todoNoList(id);
//		System.out.println("statusTodoArray ===> ");
//		for(int i = 0; i < statusTodoArray.size(); i++) {
//			System.out.println(statusTodoArray.get(i));
//		}
		
		// 3. 나의 status_todo의 todo_no 으로 나의 todo 리스트 만들기
		TodoDAO todoDAO = new TodoDAO();
		ArrayList<TodoDTO> todoArray = new ArrayList<TodoDTO>();
//		System.out.println("todoArray ===> ");
		for(int i = 0; i < statusTodoArray.size(); i++) {
			TodoDTO todo = todoDAO.todoDetail(statusTodoArray.get(i).getTodoNo());
			todoArray.add(todo);
//			System.out.println(todo);
		}
		
		// 4. 나의 statusTodo, 나의 todo 리스트 보내기 -> myTodoList.jsp
		request.setAttribute("statusTodoArray", statusTodoArray);
		request.setAttribute("todoArray", todoArray);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/study/myTodoList.jsp");
		requestDispatcher.forward(request, response);
	}
	
	private String getID(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 // 세션을 가져옵니다. 없으면 null을 반환합니다.
        HttpSession session = request.getSession(false);
        String id = "";
        
        if (session == null) {
        	response.getWriter().append("Session not found");
        } else {
        	id = (String) session.getAttribute("id");
        	if (id == null) 
        		response.getWriter().append("Username attribute not found in session");
        }
        
        return id;
        
	}
	
	
	/** 삭제해야할 코드!! **/
	private void testSession(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        // 세션을 가져옵니다. 없으면 새로 생성합니다.
        HttpSession session = request.getSession(true);
        // 세션에 데이터를 추가합니다.
        session.setAttribute("id", "lasolim");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	}

}

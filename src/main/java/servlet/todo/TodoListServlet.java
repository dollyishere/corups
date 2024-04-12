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

import dao.FileDAO;
import dao.StatusDAO;
import dao.TodoDAO;
import dto.StatusDTO;
import dto.TodoDTO;
import utils.SessionUtil;

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
	 * 3. 나의 status의 todo_no 으로 나의 todo 리스트 만들기
	 * 4. 나의 status, 나의 todo 리스트 보내기 -> myTodoList.jsp
	**/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
//		/** 삭제해야할 코드 **/
		testSession(request, response);
		FileDAO fileDAO = new FileDAO();
		
		//  인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 1. 세션에서 아이디 받기
		String id = SessionUtil.getID(request, response);

		// 2. id로 나의 status 조회
		StatusDAO statusDAO = new StatusDAO();
		ArrayList<StatusDTO> statusArray = statusDAO.todoNoList(id);
		System.out.println("statusArray ===> ");
		for(int i = 0; i < statusArray.size(); i++) {
			System.out.println(statusArray.get(i));
		}
		
		// 3. 나의 status의 todo_no 으로 나의 todo 리스트 만들기
		TodoDAO todoDAO = new TodoDAO();
		ArrayList<TodoDTO> todoArray = new ArrayList<TodoDTO>();
		System.out.println("todoArray ===> ");
		for(int i = 0; i < statusArray.size(); i++) {
			TodoDTO todo = todoDAO.todoDetail(statusArray.get(i).getTodoNo());
			todoArray.add(todo);
			System.out.println(todo);
		}
		
		// 4. 나의 statusTodo, 나의 todo 리스트 보내기 -> myTodoList.jsp
		request.setAttribute("statusArray", statusArray);
		request.setAttribute("todoArray", todoArray);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/study/myTodoList.jsp");
		requestDispatcher.forward(request, response);
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

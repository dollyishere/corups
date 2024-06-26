package servlet.todo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChapterDAO;
import dao.FileDAO;
import dao.StudyDAO;
import dao.TodoDAO;
import dto.ChapterDTO;
import dto.FileDTO;
import dto.StudyDTO;
import dto.TodoDTO;

/**
 * /todo/TodoUpdateServlet
 * 
 * @author nsr
 * @since 2024-04-08
 **/
@WebServlet("/todoUpdateServlet")
public class TodoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TodoUpdateServlet() {
		super();
	}

	/**
	 * doGet() todoNo 파라미터 가져오기 todoNo 으로 todoDTO 생성 todoDTO 건네주기 mgr/todoUpdate.jsp
	 * 으로 이동
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String todoNo_str = request.getParameter("todoNo");
		if (todoNo_str == null || todoNo_str == "")
			System.err.println("UpdateServlet todoNo_str is Nullll!!!!!");
		int todoNo = Integer.parseInt(todoNo_str);
		TodoDAO todoDAO = new TodoDAO();
		TodoDTO todo = todoDAO.todoDetail(todoNo);

		// todoNo 으로 fileDTO 배열 가져오기
		FileDAO fileDAO = new FileDAO();
		ArrayList<FileDTO> files = fileDAO.fileList(todoNo);
		
		// Chapter 와 Study 정보 조회
		ChapterDAO chapterDAO = new ChapterDAO();
		ChapterDTO chapter = chapterDAO.chapterDetail(todo.getChapterNo());
		
		StudyDAO studyDAO = new StudyDAO();
		StudyDTO study = studyDAO.studyDetail(chapter.getStudyNo());

		request.setAttribute("study", study);
		request.setAttribute("chapter", chapter);
		request.setAttribute("todo", todo);
		request.setAttribute("files", files);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/mgr/todoUpdate.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// get parameter
		String name = request.getParameter("name");
		String todoNo_str = request.getParameter("todoNo");
		if (todoNo_str == null || todoNo_str == "")
			System.err.println("UpdateServlet todoNo_str is Nullll!!!!!");
		int todoNo = Integer.parseInt(todoNo_str);
		Date startDate = parseDate(request.getParameter("startDate"));
		Date endDate = parseDate(request.getParameter("endDate"));
		String detail = request.getParameter("content");
		

		// TodoDTO
		TodoDTO todo = new TodoDTO();
		todo.setNo(todoNo);
		todo.setDetail(detail);
		todo.setEndDate(endDate);
		todo.setName(name);
		todo.setStartDate(startDate);

		// 데이터 업데이트
		TodoDAO todoDAO = new TodoDAO();
		boolean isSuccess = todoDAO.updateTodo(todo);
		
		
		if(isSuccess)
			response.getWriter().write("성공");
		else
			response.getWriter().write("실패");
		
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

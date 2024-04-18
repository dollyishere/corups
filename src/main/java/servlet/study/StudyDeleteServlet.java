package servlet.study;

import java.io.IOException;
import java.util.ArrayList;

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
import dto.TodoDTO;


@WebServlet("/study/studyDeleteServlet")
public class StudyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public StudyDeleteServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 파라미터
		int no = Integer.parseInt(request.getParameter("no"));
		StudyDAO studyDAO = new StudyDAO();
		boolean state = studyDAO.deleteStudy(no);
		
		TodoDAO todoDAO = new TodoDAO();
		StatusDAO statusDAO = new StatusDAO();
		ChapterDAO chapterDAO = new ChapterDAO();
		ArrayList<ChapterDTO> chapterList = chapterDAO.chapterList(no);
		for(int j = 0; j < chapterList.size(); j++) {
			ChapterDTO chapter = chapterList.get(j);
			chapterDAO.deleteChapter(chapter.getNo());
			
	        ArrayList<TodoDTO> todoList = todoDAO.chapter_todoList(chapter.getNo());
	        
	        FileDAO fileDAO = new FileDAO();
	        for(int i = 0; i < todoList.size(); i++) {
	        	TodoDTO todo = todoList.get(i);
	        	statusDAO.deleteStatus(todo.getNo());
	        	fileDAO.deleteFiles(todo.getNo());
	        	
	        	// todoDAO.deleteTodo();
	        	if(state) {			
	        		state = todoDAO.deleteTodo(todo.getNo());
	        	}
	        	
	        }
			
		}
		
		
		if(state == true) {
			response.getWriter().write("성공");
		}else {
			response.getWriter().write("실패");
		}
	}

}
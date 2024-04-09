package controller.study;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudyDAO;

/**
 * 게시판 등록폼, 등록처리 서블릿 클래스
 * @since 2024.04.09
 * @author 황인환
 *
 */

@WebServlet("/studyRegisterServlet")
public class StudyRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/** Study DAO */
	private StudyDAO studyDAO = null;
	
    public StudyRegisterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		// View 보내기
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/study/studyRegister.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST 한글 파라미터 깨짐 처리
		request.setCharacterEncoding("UTF-8");
		
		// 파라미터
		String subject = request.getParameter("subject");
	}

}

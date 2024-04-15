package controller.study;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudyDAO;
import dto.StudyDTO;


@WebServlet("/study/studyListServlet")
public class StudyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/** Study DAO */
	private StudyDAO studyDAO = null;
	
    public StudyListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		studyDAO = new StudyDAO();
		
		request.setCharacterEncoding("utf-8");
		
		ArrayList<StudyDTO> studyList = studyDAO.studyList();
		request.setAttribute("studyList", studyList);
			
		// View 보내기
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/study/studyList.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

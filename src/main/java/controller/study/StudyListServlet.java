package controller.study;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();
//		boolean isAdmin = (boolean)session.getAttribute("isAdmin");
		boolean  isAdmin = false; // << 이 코드 나중에 삭제(관리자 로그인 연결되면)
	if( isAdmin ) {	
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/studyMgr.jsp");
		requestDispatcher.forward(request, response);
	}else {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/study/studyList.jsp");
		requestDispatcher.forward(request, response);
	}
		
	

		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
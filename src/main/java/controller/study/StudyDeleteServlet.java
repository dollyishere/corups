package controller.study;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudyDAO;


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
	System.out.println("noooooo ==== > " + no);
		StudyDAO studyDAO = new StudyDAO();
		boolean state = studyDAO.deleteStudy(no);
		
		if(state == true) {
			response.getWriter().write("성공");
		}else {
			response.getWriter().write("실패");
		}
	}

}
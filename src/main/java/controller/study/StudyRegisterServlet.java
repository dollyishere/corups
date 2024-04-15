package controller.study;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudyDAO;
import dto.StudyDTO;

/**
 * 게시판 등록폼, 등록처리 서블릿 클래스
 * @since 2024.04.09
 * @author 황인환
 *
 */

@WebServlet("/study/studyRegisterServlet")
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
		String name = request.getParameter("name");
		String detail = request.getParameter("detail");
		String studyPwd = request.getParameter("studyPwd");
		int maxNum = Integer.parseInt(request.getParameter("maxNum"));
		String category = request.getParameter("category");

		// 모델
		StudyDTO studyDTO = new StudyDTO();
		studyDTO.setName(name);
		studyDTO.setDetail(detail);
		studyDTO.setStudyPwd(studyPwd);
		studyDTO.setMaxNum(maxNum);
		studyDTO.setCategory(category);
		
		// 게시물 등록
		this.studyDAO = new StudyDAO();
		this.studyDAO.insertStudy(studyDTO);
		
		// 페이지 이동
		response.sendRedirect("studyListServlet");
	}

}

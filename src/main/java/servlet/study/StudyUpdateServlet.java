package servlet.study;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StudyDAO;
import dto.StudyDTO;

/**
 * 게시판 수정폼, 수정처리 서블릿 클래스
 * @since 2024.04.15
 * @author 황인환
 *
 */

@WebServlet("/study/studyUpdateServlet")
public class StudyUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/** Study DAO */
	private StudyDAO studyDAO = null;
	
    public StudyUpdateServlet() {
        super();
    }

/**
 * doGet()
 * 1. 인코딩
 * 2. studyNum 가져오기
 * 3. 해당 study 불러오기
 * 4. view로 보내주기
 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// dto
		StudyDTO studyDTO = new StudyDTO();
		int n = Integer.parseInt(request.getParameter("studyNo"));
		System.out.println("studyNo =====> " + request.getParameter("studyNo"));
		studyDTO.setNo(n);
		
		// 게시물 상세 조회
		studyDAO = new StudyDAO();
		StudyDTO studyOne = studyDAO.studyDetail(studyDTO.getNo());
		
		// View 사용될 객체 설정
		request.setAttribute("studyDTO", studyOne);
		
//		// View로 보내기
//		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/mgr/studyUpdate.jsp");
//		requestDispatcher.forward(request, response);
		
		// View 보내기
		HttpSession session = request.getSession();
		boolean isAdmin = (boolean)session.getAttribute("isAdmin");
		if( isAdmin ) {	
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/studyUpdate.jsp");
			requestDispatcher.forward(request, response);
		} else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/mgr/studyUpdate.jsp");
			requestDispatcher.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
//		System.out.println("들어왔나");
		
		// 파라미터
		String name = request.getParameter("name");
		String detail = request.getParameter("detail");
		String studyPwd = request.getParameter("studyPwd");
		int maxNum = Integer.parseInt(request.getParameter("maxNum"));
		String category = request.getParameter("category");
		String studyNo = request.getParameter("no");
		
		// 모델
		StudyDTO studyDTO = new StudyDTO();
		studyDTO.setName(name);
		studyDTO.setDetail(detail);
		studyDTO.setStudyPwd(studyPwd);
		studyDTO.setMaxNum(maxNum);
		studyDTO.setCategory(category);
		studyDTO.setNo(Integer.parseInt(studyNo));
		System.out.println(studyDTO);
		
		// 게시물 등록
		this.studyDAO = new StudyDAO();
		boolean success = this.studyDAO.updateStudy(studyDTO);
		
		if (success == true) {
			response.getWriter().write("성공");
		}else {
			response.getWriter().write("실퍠");
		}
	}
	
}
package servlet.memberStudy;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberStudyDAO;

/**
 * 내 스터디 목록 조회 controller
 * @author 임주연
 * @since 2024-04-09
 */
@WebServlet("/memberStudy/myStudyListServlet")
public class MyStudyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberStudyDAO memberStudyDAO = null;
	HttpSession session = null;
       
    public MyStudyListServlet() {
        super();
    } // 생성자 END

	/**
	 * GET 요청 수행 (데이터 받아와서 내 스터디 페이지로 이동)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		
		if (id == null) {
			return;
		}
		
		memberStudyDAO = new MemberStudyDAO();
		List<Integer> studyNoList = memberStudyDAO.studyNoList(id);
		
		request.setAttribute("studyNoList", studyNoList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/study/myStudyList.jsp");
	    dispatcher.forward(request, response);
	} // doGET() END

	/**
	 * POST 요청 수행
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	} // doPOST() END

}

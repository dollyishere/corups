package servlet.memberStudy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberStudyDAO;
import dto.MemberStudyDTO;
import utils.SessionUtil;

@WebServlet("/studyMemberDeleteServlet")
public class StudyMemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StudyMemberDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	}
	/**
	 * 인코딩
	 * studyNo 값 받아오기
	 * 세션에서 아이디 가져오기
	 * studyDAO.deleteStudy()
	 * 응받 보내기
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// studyNo 값 받아오기
		int studyNo = Integer.parseInt(request.getParameter("studyNo"));
		
		// 세션에서 아이디 가져오기
		String id = SessionUtil.getID(request, response);
		
		// studyDAO.insertStudy()
		MemberStudyDAO memStudyDAO = new MemberStudyDAO();
		boolean success = memStudyDAO.deleteMemberStudy(id, studyNo);
		System.out.println("여기보세요~~~~");
		System.out.println(success);
		if(success) {
			request.setAttribute("join", false);
			response.getWriter().write("success");
		}
		else
			response.getWriter().write("fail");
	}

}

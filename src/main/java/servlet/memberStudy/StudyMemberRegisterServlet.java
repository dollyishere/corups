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

@WebServlet("/studyMemberRegisterServlet")
public class StudyMemberRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StudyMemberRegisterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	}
	
	/**
	 * 인코딩
	 * studyNo 값 받아오기
	 * 세션에서 아이디 가져오기
	 * memberStudyDTO 생성
	 * studyDAO.insertStudy()
	 * 응받 보내기
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("멤버스터디 등록 서블릿 들어옴");
		
		// 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// studyNo 값 받아오기
		int studyNo = Integer.parseInt(request.getParameter("studyNo"));
		
		// 세션에서 아이디 가져오기
		String id = SessionUtil.getID(request, response);
		
		// memberStudyDTO 생성
		MemberStudyDTO memberStudyDTO = new MemberStudyDTO();
		memberStudyDTO.setMemberId(id);
		memberStudyDTO.setStudyNo(studyNo);
		
		// studyDAO.insertStudy()
		MemberStudyDAO memStudyDAO = new MemberStudyDAO();
		boolean success = memStudyDAO.insertMemberStudy(memberStudyDTO);
		
		if(success) {
			request.setAttribute("join", true);
			response.getWriter().write("success");
//			response.sendRedirect("/mgr/chapterDetailServlet");
		}
		else
			response.getWriter().write("fail");
		
	}

}

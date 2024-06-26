package servlet.study;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberStudyDAO;
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
		
		// 스터디에 몇 명 참여하는지 가져오기
		MemberStudyDAO memberStudyDAO = new MemberStudyDAO();
		List<Integer> studyMemberNumList = new ArrayList<Integer>();
		List<ArrayList<String>> studyMemberList = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < studyList.size(); i++) {
			ArrayList<String> miniList = new ArrayList<String>();
			miniList = (ArrayList<String>) memberStudyDAO.memberIdList(studyList.get(i).getNo());
			int myStudyMemberNum = memberStudyDAO.memberIdList(studyList.get(i).getNo()).size();
			studyMemberList.add(miniList);
			studyMemberNumList.add(myStudyMemberNum);
		}
	
		request.setAttribute("studyMemberList", studyMemberList);
		request.setAttribute("studyMemberNumList", studyMemberNumList);
		// View 보내기
		HttpSession session = request.getSession();
		boolean isAdmin = (boolean)session.getAttribute("isAdmin");

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
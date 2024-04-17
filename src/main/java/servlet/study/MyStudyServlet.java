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

import dao.MemberStudyDAO;
import dao.StudyDAO;
import dto.StudyDTO;
import utils.SessionUtil;

/**
 * /study/myStudyListServlet
 * 
 * @author hih
 * @since 2024-04-15
 **/
@WebServlet("/study/myStudyServlet")
public class MyStudyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public MyStudyServlet() {
        super();
    }
    
    /** doGet()
     * 1. 세션에서 아이디 받기
     * 2. id로 나의 myStudyList 조회
     * 3. myStudyList 리스트 보내기 -> myStudyList.jsp 
     * */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 인코딩
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 1. 세션에서 아이디 받기
		String id = SessionUtil.getID(request, response);
		
		// 2. id로 나의 myStudyList 조회
		StudyDAO studyDAO = new StudyDAO();
		ArrayList<StudyDTO> myStudyList = studyDAO.myStudyList(id);
		System.out.println(id);
		
		// 스터디에 몇 명 참여하는지 가져오기
		MemberStudyDAO memberStudyDAO = new MemberStudyDAO();
		List<Integer> myStudyMemberNumList = new ArrayList<Integer>();
		for (int i = 0; i < myStudyList.size(); i++) {
			int myStudyMemberNum = memberStudyDAO.memberIdList(myStudyList.get(i).getNo()).size();
			myStudyMemberNumList.add(myStudyMemberNum);
		}
		
		// 3. myStudyList 리스트 보내기 -> myStudyList.jsp
		request.setAttribute("myStudyList", myStudyList);
		request.setAttribute("myStudyMemberNumList", myStudyMemberNumList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/study/myStudyList.jsp");
		requestDispatcher.forward(request, response);
		
				
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
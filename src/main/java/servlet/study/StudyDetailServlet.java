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

import dao.ChapterDAO;
import dao.MemberDAO;
import dao.MemberStudyDAO;
import dao.StudyDAO;
import dto.ChapterDTO;
import dto.MemberDTO;
import dto.StudyDTO;
import utils.SessionUtil;

/**
 * /chapter/ChapterListServlet
 * 
 * @since 2024.04.09
 * @author cyb
 */
@WebServlet("/study/studyDetailServlet")
public class StudyDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChapterDAO chapterDAO = null;

	public StudyDetailServlet() {
		super();
	}

	/**
	 * chapterDAO에서 해당 스터디 귀속 챕터 전체 목록 조회 => studyList.jsp로 이동
	 * 
	 * 세션에서 아이디 받기 id로 나의 챕터 조회 나의 나의 챕터 no로 챕터 리스트 만들기 나의 챕터,나의 챕터리스트보내기 ->
	 * studyDetail.jsp
	 * 
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		
		// studyNo 매개변수 가져오기
		 int studyNo = Integer.parseInt(request.getParameter("studyNo"));

//		int studyNo = 1;
		System.out.println("studyNo : =====>" + studyNo);

		// ChapterDAO 인스턴스 생성
		this.chapterDAO = new ChapterDAO();

		// 해당 스터디에 대한 챕터 목록과 스터디 이름 가져오기
		ArrayList<ChapterDTO> chapterList = this.chapterDAO.chapterListWithStudyName(studyNo);
//		chapterList.get(0).getStudyName();
//		System.out.println(chapterList.get(0).getStudyName());

		StudyDAO studyDAO = new StudyDAO();
		StudyDTO study = studyDAO.studyDetail(studyNo);

		// 세션에 id 가져오기
		 String id = SessionUtil.getID(request, response);
		 System.out.println(id);
		request.setAttribute("userid", id); // 여기서 1은 테스트용 사용자 ID

		// chapterListServlet으로 전달할 studyNo와 chapterList를 request 속성으로 설정
//		request.setAttribute("studyNo", studyNo);
		request.setAttribute("chapterList", chapterList);
		request.setAttribute("study", study);

		// request.getRequestDispatcher("/study/studyDetail.jsp").forward(request,
		// response);

		// member_study
		MemberStudyDAO memberStudyDAO = new MemberStudyDAO();
		List<String> memberIdList = memberStudyDAO.memberIdList(studyNo);
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		MemberDAO memberDAO = new MemberDAO();
		for (int i = 0; i < memberIdList.size(); i++) {
			memberList.add(memberDAO.detail(memberIdList.get(i)));
		}
		request.setAttribute("memberStudyList", memberList);
		int studyMemberCount = memberIdList.size();
		request.setAttribute("studyMemberCount", studyMemberCount);
		
		HttpSession session = request.getSession();
		boolean isAdmin = (boolean)session.getAttribute("isAdmin");
//		boolean isAdmin = false; // << 이 코드 나중에 삭제(관리자 로그인 연결되면)
		if (isAdmin) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/studyDetail.jsp");
			requestDispatcher.forward(request, response);
		} else {
			System.out.println("===================");
			System.out.println(study.getCreateUserId());
			System.out.println(id);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/study/studyDetail.jsp");
			requestDispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("post");
	}

}
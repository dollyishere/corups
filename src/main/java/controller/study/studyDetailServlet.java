package controller.study;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChapterDAO;
import dto.ChapterDTO;


/**
 * /chapter/ChapterListServlet
 * 
 * @since 2024.04.09
 * @author cyb
 */
@WebServlet("/study/studyDetailServlet")
public class studyDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChapterDAO chapterDAO = null;

	public studyDetailServlet() {
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
		System.out.println("studyNo : =====>" + studyNo);
//		int studyNo=1;
		/* pageNum 매개변수 가져오기
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
            pageNum = "1";
        }*/

		// ChapterDAO 인스턴스 생성
		this.chapterDAO = new ChapterDAO();
		
		/* 페이지 정보를 담고 있는 PageInfo 객체를 생성하고 초기화
		 PageInfo pageInfo = new PageInfo(Integer.parseInt(pageNum), 10, 10); // listCount와 pagePerBlock 값은 임의로 설정
		*/
		 // 해당 스터디에 대한 챕터 목록과 스터디 이름 가져오기
        ArrayList<ChapterDTO> chapterList = this.chapterDAO.chapterListWithStudyName(studyNo);
        chapterList.get(0).getStudyName();
        System.out.println("------");
        System.out.println(chapterList.get(0).getStudyName());


		// chapterListServlet으로 전달할 studyNo와 chapterList를 request 속성으로 설정
		request.setAttribute("studyNo", studyNo);
		request.setAttribute("chapterList", chapterList);
		request.setAttribute("studyName",chapterList.get(0).getStudyName());
		// chapterListServlet으로 포워딩
		request.getRequestDispatcher("/study/studyDetail.jsp").forward(request, response);
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("post");
	}

}

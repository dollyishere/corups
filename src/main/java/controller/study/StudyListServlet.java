package controller.study;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
		
//		/** 페이징 시작 **/
//		
//		// 전달된 파라미터를 이용하여 값 추출(처음에는 전달되는 값이 없음)
//		String pageNum = request.getParameter("pageNum");  // 페이지 번호
//		String searchType = request.getParameter("searchType"); // 검색 종류
//		String searchText = request.getParameter("searchText");  // 검색어
//		if (pageNum == null) {
//			pageNum = "1";			// 전달된 페이지 번호가 없으면 1
//		}
//		if (searchText == null) {
//			searchType = "";			// 전달된 검색 종류가 없으면 "" 를 이용하여 초기화
//			searchText = "";			// 전달된 검색어가 없으면  "" 를 이용하여 초기화
//		}
//
//		// 모델 (BoardDAO의 메서드에게 전달할 데이터를 하나의 객체로 )
//		StudyDTO studyDTO = new StudyDTO();
//		studyDTO.setPageNum(pageNum);
//		studyDTO.setSearchType(searchType);
//		studyDTO.setSearchText(searchText);
//		
//		// DAO (DB의 테이블에 접속하여 쿼리를 실행할 수 있는 메서드 보유 객체 생성) 
//		this.studyDAO = new StudyDAO();
//		
//		// 게시물 총 수 (목록 아래에 페이지 번호를 계산하기 위한 메서드 호출) 
//		int totalCount = this.studyDAO.selectCount(studyDAO);
//		
//		// 게시물 목록을 얻는 쿼리 실행 (결과값을 ArrayList<BoardModel> 로 반환 받음)
//		List<StudyDAO> boardList = this.studyDAO.selectList(studyDAO);
//		
//		// View 사용될 객체 설정
//		request.setAttribute("totalCount", totalCount);
//		
//		// 목록 하단 페이지 번호출력을 위한 객체 생성
//		PageNavigator pNavigator=new PageNavigator();
//		
//		// 페이지 번호들을 문자열로 반환하는 메서드 호출
//		/* 반환 결과의 예
//		 * 		 1  2  3  4  5  6  7  8  9  10  >  >>
//		 * << 1  2  3  4  5  6  7  8  9  10  >  >>
//		*/
//		String p_navi=pNavigator.getPageNavigator(totalCount, 
//													studyDTO.getListCount(), 
//													studyDTO.getPagePerBlock(), 
//													Integer.parseInt(pageNum), 
//													searchType, searchText);
//				
//		request.setAttribute("pageNavigator", p_navi);   	// 페이지 번호들
//		request.setAttribute("studyList", studyList);			// 조회 결과 리스트
//		request.setAttribute("studyModel", studyDTO);	// 모델
//		
		
		// View 보내기
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/study/studyList.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

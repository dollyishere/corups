package servlet.memberStudy;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChapterDAO;
import dao.MemberStudyDAO;
import dao.StatusDAO;
import dao.TodoDAO;
import dto.ChapterDTO;
import dto.MemberStudyDTO;
import dto.StatusDTO;
import dto.TodoDTO;
import utils.SessionUtil;

@WebServlet("/studyMemberRegisterServlet")
public class StudyMemberRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoDAO todoDAO = null;
	private StatusDAO statusDAO = null;
	private ChapterDAO chapterDAO = null;
       
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
		
		// studyNo 으로 챕터 챕터 리스트 가져오리
		this.chapterDAO = new ChapterDAO();
		ArrayList<ChapterDTO> chapterList = chapterDAO.chapterList(studyNo);
		// 반복문
		for(int i = 0; i < chapterList.size(); i++) {
			//todo List 가져오기
			this.todoDAO = new TodoDAO();
			ArrayList<TodoDTO> todoList = this.todoDAO.chapter_todoList(chapterList.get(i).getNo());
				for(int j = 0; j < todoList.size(); j++) {
					TodoDTO todo = todoList.get(j);
					// StatusDTO
					StatusDTO status = new StatusDTO();
					status.setMemberId(id);
					status.setTodoNo(todo.getNo());
					// 마감기한이 지났으면 상태 D로 저장
					Date endDate = todo.getEndDate();
					// 현재 시간
					java.sql.Date now = new java.sql.Date(System.currentTimeMillis());  
					if (endDate.before(now))
						status.setStatus("D");
					// 아니라면 P로 저장
					else
						status.setStatus("P");
					
					// Insert status
					StatusDAO statusDAO = new StatusDAO();
					success = statusDAO.insertStatus(status);
					
				}
			
		}
				
		
		if(success) {
			request.setAttribute("join", true);
			response.getWriter().write("success");
//			response.sendRedirect("/mgr/chapterDetailServlet");
		}
		else
			response.getWriter().write("fail");
		
	}

}

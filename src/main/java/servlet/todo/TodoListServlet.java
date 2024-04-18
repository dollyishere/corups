package servlet.todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChapterDAO;
import dao.MemberStudyDAO;
import dao.StatusDAO;
import dao.StudyDAO;
import dao.TodoDAO;
import dto.ChapterDTO;
import dto.StatusDTO;
import dto.StudyDTO;
import dto.TodoDTO;
import utils.SessionUtil;

/**
 * /todo/todoListServlet
 * 
 * @author nsr
 * @since 2024-04-08
 **/
@WebServlet("/todoListServlet")
public class TodoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TodoListServlet() {
        super();
    }

	/** doGet()
	 * 1. 세션에서 아이디 받기
	 * 2. 2. id로 나의 status_todo 조회
	 * 3. 나의 status의 todo_no 으로 나의 todo 리스트 만들기
	 * 4. 나의 status, 나의 todo 리스트 보내기 -> myTodoList.jsp
	**/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
//		/** 삭제해야할 코드 **/
//		testSession(request, response);
		
		//  인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 1. 세션에서 아이디 받기
		String id = SessionUtil.getID(request, response);

		// 2. id로 나의 status 조회
		StatusDAO statusDAO = new StatusDAO();
		ArrayList<StatusDTO> statusArray = statusDAO.todoNoList(id);
		ArrayList<StatusDTO> statusArrayTmp = new ArrayList<StatusDTO>();
		
		// 3. 나의 status의 todo_no 으로 나의 todo 리스트 만들기
		StudyDAO studyDAO = new StudyDAO();
		ChapterDAO chapterDAO = new ChapterDAO();
		TodoDAO todoDAO = new TodoDAO();
		MemberStudyDAO memberStudyDAO = new MemberStudyDAO();
		ArrayList<TodoDTO> todoArray = new ArrayList<TodoDTO>();
		System.out.println("statusArray ===> ");
		for(int i = 0; i < statusArray.size(); i++) {
			StatusDTO status = statusArray.get(i);
			System.out.println(status);
			TodoDTO todo = todoDAO.todoDetail(status.getTodoNo());
			ChapterDTO chapter = chapterDAO.chapterDetail(todo.getChapterNo());
			StudyDTO study = studyDAO.studyDetail(chapter.getStudyNo());
			System.out.println(id);
			System.out.println(todo);
			List<String> memberIdList = memberStudyDAO.memberIdList(study.getNo());
			boolean myStudy = false;
			System.out.println("memberIdList ===> ");
			for(int j = 0; j < memberIdList.size(); j++) {
				System.out.println(memberIdList.get(j));
				if(memberIdList.get(j).equals(id)) {
					myStudy = true;
					break;
				}
			}
			if(myStudy) {
				statusArrayTmp.add(statusArray.get(i));
				todoArray.add(todo);
			}
		}
		
		// 해당하는 todo의 chapter 가져오기
		List<ChapterDTO> chapterList = new ArrayList<ChapterDTO>();
		for(int i = 0; i < todoArray.size(); i++) {
			ChapterDTO chapter =chapterDAO.chapterDetail(todoArray.get(i).getChapterNo());
			chapterList.add(chapter);
		}
		
		// 해당하는 todo의 study 가져오기
		List<StudyDTO> todoStudyList = new ArrayList<StudyDTO>();
		for(int i = 0; i < chapterList.size(); i++) {
			StudyDTO study = studyDAO.studyDetail(chapterList.get(i).getStudyNo());
			todoStudyList.add(study);
		}
		
		// 4. 나의 statusTodo, 나의 todo 리스트 보내기 -> myTodoList.jsp
		request.setAttribute("statusArray", statusArrayTmp);
		request.setAttribute("todoArray", todoArray);
		request.setAttribute("todoStudyList", todoStudyList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/study/myTodoList.jsp");
		requestDispatcher.forward(request, response);
		
		
	}
	
	
//	/** 삭제해야할 코드!! **/
//	private void testSession(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//        // 세션을 가져옵니다. 없으면 새로 생성합니다.
//        HttpSession session = request.getSession(true);
//        // 세션에 데이터를 추가합니다.
//        session.setAttribute("id", "lasolim");
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	}

}

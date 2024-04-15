package controller.chapter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChapterDAO;
import dao.TodoDAO;
import dto.ChapterDTO;
import dto.TodoDTO;

/**
 * /chapter/ChapterDetailServlet
 * @since 2024.04.09
 * @author cyb
 */

@WebServlet("/chapter/chapterDetailServlet")
public class chapterDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ChapterDAO chapterDAO = null;

	public chapterDetailServlet() {
		super();

	}

	/**
	 * chapterDAO에서 해당 no가진 챕터 상세 정보 조회 => chapterDetail.jsp로 이동
	 * study_no 파라미터 받기 study_no로 chapter 상세정보 조회 세션에 id가져오기 create_user_id가져오기 id와
	 * chapter.create_user_id가 같으면 /mgr/chapterDetail.jsp로 이동 아니면
	 * /chapterDetail.jsp로 이동
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 파라미터
        String chapterNo = request.getParameter("no");
        //String studyNoParam = request.getParameter("studyNo");
        if (chapterNo == null || chapterNo.isEmpty()) {
            // 적절한 파라미터가 없는 경우 에러 처리
            response.sendRedirect(request.getContextPath() + "/errorLog.jsp");
            return;
        }

        int no = Integer.parseInt(chapterNo);
        
        ArrayList<TodoDTO> todo = new ArrayList<TodoDTO>();	
        TodoDAO todoDAO = new TodoDAO();
        todo.addAll(todoDAO.chapter_todoList(no));
        
        
        request.setAttribute("userid", 1); // 여기서 1은 테스트용 사용자 ID
        // 챕터 상세 조회 - todoList
        ChapterDTO chapter = new ChapterDTO();
        chapterDAO = new ChapterDAO();
        chapter = chapterDAO.chapterDetail(no); 

        // 챕터 정보를 JSP 페이지에 전달하고 포워딩
        request.setAttribute("chapter", chapter);
        request.setAttribute("todoArray", todo);
     // 서블릿에서 사용자 ID 설정 및 JSP 페이지로 전달
        
        

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/study/chapterDetail.jsp");
        requestDispatcher.forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// x
	}

}

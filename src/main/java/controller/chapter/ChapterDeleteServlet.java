package controller.chapter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChapterDAO;
import dto.ChapterDTO;

/**
 * /chapter/chapterDeleteServlet
 * @since 2024.04.09
 * @author cyb
 */
@WebServlet("/chapter/chapterDeleteServlet")
public class chapterDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private ChapterDAO chapterDAO = null;
 
    public chapterDeleteServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// x		
	}

	/** studyDAO에서 챕터 삭제 => error.jsp나 studyDetail.jsp로 이동 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// chapterUpdate에서 삭제 버튼 클릭시 
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		ChapterDTO chapter = new ChapterDTO();
		chapter.setNo(no);
		
		// 챕터 삭제
		chapterDAO = new ChapterDAO();
		
		// error.jsp 또는 chapterDetailServlet으로 이동
		response.sendRedirect("/mgr/studyDetailServlet");
	
	}

}

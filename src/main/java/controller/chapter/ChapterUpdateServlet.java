package controller.chapter;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * /chapter/chapterUpdateServlet
 * @since 2024.04.09
 * @author cyb
 */
@WebServlet("/chapter/chapterUpdateServlet")
public class ChapterUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ChapterUpdateServlet() {
        super();
        
    }
   /** chapterDAO에서 해당 no가진 챕터 상세 정보 조회 => chapterUpdate.jsp로 이동 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/mgr/chapterUpdate.jsp");
		requestDispatcher.forward(request, response);
	}

	/** chapterDAO에서 챕터 수정 => error.jsp나 chapterDetail.jsp로 이동 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// chapterUpdate에서 수정 버튼 클릭시 
		// error.jsp 또는 chapterDetailServlet으로 이동
		response.sendRedirect("/mgr/chapterDetailServlet");
	
	}

}

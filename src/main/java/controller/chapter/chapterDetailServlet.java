package controller.chapter;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * /chapter/ChapterDetailServlet
 * @since 2024.04.09
 * @author cyb
 */

@WebServlet("/chapter/ChapterDetailServlet")
public class chapterDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public chapterDetailServlet() {
        super();

    }

	/**chapterDAO에서 해당 no가진 챕터 상세 정보 조회 => 
	 * chapterDetail.jsp로 이동*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/mgr/chapterDetail.jsp");
		requestDispatcher.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// x	
	}

}

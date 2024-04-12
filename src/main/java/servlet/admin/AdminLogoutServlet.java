package servlet.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 관리자(admin) 로그아웃 controller
 * @author 임주연
 * @since 2024-04-09
 */
@WebServlet("/admin/adminLogoutServlet")
public class AdminLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminLogoutServlet() {
        super();
    } // 생성자 END

    /**
	 * GET 요청 수행
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	} // doGET() END

	/**
	 * POST 요청 수행(관리자 로그아웃)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "/index.jsp";
		
		// 기존 session 계속 사용
		HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("adminId");
            session.removeAttribute("isAdmin");
        }

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
		
	} // doPOST() END

}

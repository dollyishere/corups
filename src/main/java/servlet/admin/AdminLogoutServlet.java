package servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;

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
		PrintWriter out = response.getWriter();
		
		// 세션 불러온 후 세션에 adminId 값 저장되어 있을 시, logout
		HttpSession session = request.getSession(false);
        if (session.getAttribute("adminId") != null) {
            session.removeAttribute("adminId");
			session.setAttribute("isAdmin", false);
        }

     // 로그아웃 성공할 시 index.jsp로
     out.write("logout_complete!");
		
	} // doPOST() END

}

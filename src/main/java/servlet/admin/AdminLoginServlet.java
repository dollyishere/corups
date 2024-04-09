package servlet.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDAO;

/**
 * 관리자(admin) 로그인 controller
 * @author 임주연
 * @since 2024-04-09
 */
@WebServlet("/admin/adminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDAO adminDAO = null;
       
    public AdminLoginServlet() {
        super();
    } // 생성자 END

    /**
   	 * GET 요청 수행(관리자 로그인 페이지로 이동)
   	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// view로 보내기
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/login.jsp");
		requestDispatcher.forward(request, response);
	} // doGET() END

	/**
	 * POST 요청 수행(관리자 로그인)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "/admin/index.jsp";
		boolean status = false;
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		adminDAO = new AdminDAO();
		status = adminDAO.adminLogin(id, pwd);
		
		// 로그인 가능 시, 세션에 정보 저장
		if (status) {
			HttpSession session = request.getSession();
			session.setAttribute("adminId", id);
			session.setAttribute("isAdmin", true);
		} else {
			nextPage = "/logError.jsp?mod=0";
		}
		
		// 성공할 시 admin/index.jsp로
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	} // doPOST() END

}

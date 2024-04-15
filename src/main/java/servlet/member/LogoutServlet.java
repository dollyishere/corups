package servlet.member;

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
 * 로그아웃 controller
 * @author 임주연
 * @since 2024-04-09
 * **/
@WebServlet("/member/logoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public LogoutServlet() {
        super();
    } // 생성자 END

    /** GET 요청 **/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	} // doGET() END

	/** POST 요청: 로그아웃 요청 실행 **/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		// 세션 불러온 후 세션에 id 값 저장되어 있을 시, logout
		 HttpSession session = request.getSession(false);
        if (session.getAttribute("id") != null) {
            session.removeAttribute("id");
            session.removeAttribute("img");
            session.removeAttribute("name");
        }
		
		// 로그아웃 성공할 시 index.jsp로
		out.write("logout_complete!");
	} // doPOST() END

}

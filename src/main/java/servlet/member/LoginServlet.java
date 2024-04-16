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

import dao.MemberDAO;
import dto.MemberDTO;

/**
 * 로그인 controller
 * @author 임주연
 * @since 2024-04-09
 * **/
@WebServlet("/member/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDAO memberDAO = null;
	private MemberDTO member = null;
    
    public LoginServlet() {
        super();
    } // 생성자 END

    /** GET 요청 수행(로그인 페이지로 이동) **/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// view 보내기
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/mem/login.jsp");
		requestDispatcher.forward(request, response);
	} // GET() END

	/** POST 요청 수행(로그인 로직 수행) **/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		boolean status = false;
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		memberDAO = new MemberDAO();
		status = memberDAO.login(id, pwd);
		member = memberDAO.detail(id);
		// 로그인 가능 시, 세션에 정보 저장(아이디, 프사, 이름)
		if (status) {
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("img", member.getImage());
			session.setAttribute("name", member.getName());
			out.write("login");
		} else {
			out.write("login_error");
		}
		
		
	} // doPOST() END

}

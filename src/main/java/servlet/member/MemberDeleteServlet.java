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
 * 회원 탈퇴 controller
 * @author 임주연
 * @since 2024-04-09
 */
@WebServlet("/member/memberDeleteServlet")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberDAO memberDAO = null;
    
    public MemberDeleteServlet() {
        super();
    } // 생성자 END

	/**
	 * GET 요청 수행(admin일 시, MemberMgr로 돌아가기)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	} // doGET() END

	/**
	 * POST 요청 수행(회원 탈퇴)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		boolean status = false;
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		memberDAO = new MemberDAO();
		status = memberDAO.delete(id, pwd);
		
		if (status) {
			out.write("delete complete");
		} else {
			out.write("delete fail");
		}
		return;
		
	} // doPOST() END

}

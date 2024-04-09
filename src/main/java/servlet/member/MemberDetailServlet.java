package servlet.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import dto.MemberDTO;

/**
 * 회원 상세 조회 controller
 * @author 임주연
 * @since 2024-04-09
 */
@WebServlet("/member/memberDetailServlet")
public class MemberDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDTO member = null;
    private MemberDAO memberDAO = null;
    
    public MemberDetailServlet() {
        super();
    } // 생성자 END

	/**
	 * GET 요청 수행(회원가입 페이지로 이동)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	} // doGET() END

	/**
	 * POST 요청 수행
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	} // doPOST() END

}

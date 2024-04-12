package servlet.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import dto.MemberDTO;

/**
 * 회원 리스트 조회 controller
 * @author 임주연
 * @since 2024-04-09
 */
@WebServlet("/member/memberListServlet")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberDAO memberDAO = null;
    
    public MemberListServlet() {
        super();
    } // 생성자 END

    /**
  	 * GET 요청 수행(회원 관리 페이지로 이동)
  	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		memberDAO = new MemberDAO();
		List<MemberDTO> memberList = memberDAO.selectList();
		
		request.setAttribute("memberList", memberList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/memberMgr.jsp");
	    dispatcher.forward(request, response);
	} // doGET() END

	/**
  	 * POST 요청 수행
  	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	} // doPOST() END

}

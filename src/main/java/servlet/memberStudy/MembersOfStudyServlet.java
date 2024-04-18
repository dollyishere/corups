package servlet.memberStudy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 스터디에 참여하는 회원 리스트 조회 controller
 * @author 임주연
 * @since 2024-04-09
 */
@WebServlet("/memberStudy/membersOfStudyServlet")
public class MembersOfStudyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MembersOfStudyServlet() {
        super();
        // TODO Auto-generated constructor stub
    } // 생성자 END

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	} // doGET() END

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	} // doPOST() END

}

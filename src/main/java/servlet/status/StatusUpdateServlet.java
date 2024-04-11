package servlet.status;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StatusDAO;
import dto.StatusDTO;
import utils.SessionUtil;

/**
 * /status/StatusUpdateServlet
 * 
 * @author nsr
 * @since 2024-04-08
 **/
@WebServlet("/statusUpdateServlet")
public class StatusUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public StatusUpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	}

	/**
	 * 인코딩
	 * status, memberId, todoNo 가져오기
	 * StatusDTO 생성
	 * StatusDAO.updateStatus(StatusDTO status);
	 *  response.getWriter().write();
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		//  인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// status, memberId, todoNo 가져오기
		String status = request.getParameter("status");
		String todoNoStr = request.getParameter("todoNo");
		System.out.println(todoNoStr);
		int todoNo = Integer.parseInt(todoNoStr);
		
		// StatusDTO 생성
		StatusDTO statusDTO = new StatusDTO();
		statusDTO.setMemberId(SessionUtil.getID(request, response));
		statusDTO.setStatus(status);
		statusDTO.setTodoNo(todoNo);
		
		// StatusDAO.updateStatus(StatusDTO status);
		StatusDAO statusDAO = new StatusDAO();
		boolean isSuccess = statusDAO.updateStatus(statusDTO);
		
		String result = "fail";
		if(isSuccess)
			result = "success";
		
		// response.getWriter().write();
		response.getWriter().write(result);
		
	}

	
}

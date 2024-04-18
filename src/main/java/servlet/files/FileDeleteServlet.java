package servlet.files;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FileDAO;

/**
 * /file/fileDeleteServlet
 * 
 * @author nsr
 * @since 2024-04-08
 **/
@WebServlet("/fileDeleteServlet")
public class FileDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FileDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * 인코딩
	 * 파일 넘버 가져오기
	 * 해당 파일 삭제하기
	 * 결과 응답
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doHandle(request, response);
		
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("파일 삭제 들어옴");
		// 인코딩
		request.setCharacterEncoding("utf-8");
		System.out.println(request.getParameter("fileNo"));
		// 파일 넘버 가져오기
		int fileNo = Integer.parseInt(request.getParameter("fileNo"));
		
		
		FileDAO fileDAO = new FileDAO();
		boolean isSuccess = fileDAO.deleteFile(fileNo);
		
		if(isSuccess)
			response.getWriter().write("success");
		else
			response.getWriter().write("fail");
		
	}

}

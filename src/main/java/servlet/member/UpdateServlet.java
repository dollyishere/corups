package servlet.member;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.MemberDAO;
import dto.MemberDTO;

/**
 * 회원 정보 수정 controller
 * @author 임주연
 * @since 2024-04-09
 */
@WebServlet("/member/updateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDTO member = null;
    private MemberDAO memberDAO = null;
    HttpSession session = null;
    private String nowPath;
    private String nextPath;
    public UpdateServlet() {
        super();
    } // 생성자 END

    /**
  	 * GET 요청 수행(회원 정보 수정 페이지로 이동)
  	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// 회원 정보 가져온 뒤 view로 이동
			memberDAO = new MemberDAO();
			request.setCharacterEncoding("utf-8");
		    response.setContentType("test/html; charset=utf-8");
		     
			String id = "";
	
			// 일반 회원인지, admin인지에 따라 루트 변경
			nowPath = request.getParameter("nowPath");
			
			if (nowPath != null) {
			    id = (String) request.getParameter("id");
			    nextPath = "/admin/memberUpdate.jsp";
			} else {
			    session = request.getSession(false);
			    id = (String) session.getAttribute("id");
			    nextPath = "/mem/profileUpdate.jsp";
			}
			
			member = memberDAO.detail(id);
			request.setAttribute("member", member);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPath);
			requestDispatcher.forward(request, response);
	} // doGET() END

	/**
	 * POST 요청 수행(회원 정보 수정 수행)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter(); 
		request.setCharacterEncoding("utf-8");
	     response.setContentType("text/html; charset=utf-8");
	     String id = "";
	     boolean resultQ = false;
	     FileItem imgFile = null;
	     File uploadFile = null;
	     
	     member = new MemberDTO();
	     
	     // 관심사 이니셜만 뽑아내서 join으로 결합
	     List<String> interests = new ArrayList<>();
	     String joinedInterests = "";
	     
	     // 이미지 파일 업로드 위한 파일 객체 설정
	     ServletContext context = getServletContext();
	     String realPath = context.getRealPath("/uploads/profile_img/");
	     System.out.println("실제 경로: " + realPath);
	     
	     // 서블릿 파일 업로드 객체
	     DiskFileItemFactory factory = new DiskFileItemFactory();
	     factory.setSizeThreshold(1024 * 1024);
	     ServletFileUpload upload = new ServletFileUpload(factory);
	     
	     List<FileItem> itemsList = null;
	     
		try {
			itemsList = upload.parseRequest(request);
			// 파싱된 데이터 처리
	    	 for (FileItem item : itemsList) {
	    		 if (item.isFormField()) { 
	    			 // 관심사일 시
	    			 if ("interests".equals(item.getFieldName())) {
	    				 interests.add(item.getString("utf-8"));
	    				 // 파일 현재 경로 저장
	    			 } else if ("nowPath".equals(item.getFieldName())) {
	    				 nowPath = item.getString("utf-8");
	    				 // 이전 이미지 명 가져옴
	    			 } else if ("preImg".equals(item.getFieldName())) {
	    				 member.setImage(item.getString("utf-8"));
	    				 // 아이디 저장
	    			 } else if ("id".equals(item.getFieldName())) {
	    				 member.setId(item.getString("utf-8"));
	    				 id = item.getString("utf-8");
	    				 // 비밀번호 저장
	    			 } else if ("pwd".equals(item.getFieldName())) {
	    				 member.setPwd(item.getString("utf-8"));
	    			 }  else if ("name".equals(item.getFieldName())) {
	    				 member.setName(item.getString("utf-8"));
	    			 } else if ("birthday".equals(item.getFieldName())) {
	    			     // birthday date 속성으로 변환
	    			     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    			     java.util.Date utilDate = new Date(System.currentTimeMillis());
	    			     try {
	    			    	 utilDate = format.parse(item.getString("utf-8"));
	    			    	 java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	    			    	 member.setBirthday(sqlDate);
	    			     } catch (ParseException e) {
	    			    	 System.out.println(e.getMessage());
	    			    	 out.write("birthday err");
	    			     }
	    			     // 이메일 저장
	    			 } else if ("email".equals(item.getFieldName())) {
	    				 member.setEmail(item.getString("utf-8"));
	    				 // 직장 저장
	    			 } else if ("job".equals(item.getFieldName())) {
	    				 member.setJob(item.getString("utf-8"));
	    			 }
	    		 } else if (item.getSize() > 0) {
	    			 imgFile = item;
	    		 }
	    	 }
	    	 
	    	 // 만약 img 파일은 null이 아니고, member.getImage는 null일 시
	    	 if (imgFile != null && member.getImage() == null) {
	    		 File currentPath = new File(realPath);
    		     
    			 int idx = imgFile.getName().lastIndexOf("\\");
    			 if (idx == -1) {
    				 idx = imgFile.getName().lastIndexOf("/");
    			 }
    			 String fileName = imgFile.getName().substring(idx + 1);
    			 int lastDotIndex = fileName.lastIndexOf(".");
    			 member.setImage(id + "_p_img" + fileName.substring(lastDotIndex));
    			 uploadFile = new File(currentPath + "\\" + id + "_p_img" + fileName.substring(lastDotIndex));
    			 imgFile.write(uploadFile);
    			 
	    	 }
	    	 
		} catch (Exception e) {
			System.err.println("File Upload ERR : " + e.getMessage());
	    	out.write("file upload err");
		}
    	    
	     // 관심사 이니셜 결합해서 추가
	     if (interests != null & !interests.isEmpty()) {
	    	 joinedInterests = String.join("", interests);
	     }
	     
	     member.setInterest(joinedInterests);

	     memberDAO = new MemberDAO();
	     resultQ = memberDAO.update(member);

	     if (!resultQ) {
	    	 out.write("update error");
	     } else if (nowPath != null) {
	    	 out.write("update complete admin!");
	     } else {
	    	 out.write("update complete!");
	     }

	} // doPOST() END

}

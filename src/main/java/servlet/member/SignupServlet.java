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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.MemberDAO;
import dto.MemberDTO;

/**
 * 회원 가입 controller
 * @author 임주연
 * @since 2024-04-09
 */
@WebServlet("/member/signupServlet/*")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDTO member = null;
    private MemberDAO memberDAO = null;
       
    public SignupServlet() {
        super();
    } // 생성자 END

    /**
	 * GET 요청 수행(회원가입 페이지로 이동)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/mem/signup.jsp");
 		requestDispatcher.forward(request, response);
	}  // doGET() END

	/**
	 * POST 요청 수행(회원가입 수행)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	} // doPOST() END
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
	     response.setContentType("text/html; charset=utf-8");
	     String nextPath = "/mem/login.jsp";
	     String id = "";
	     boolean resultQ = false;
	     FileItem imgFile = null;
	     File uploadFile = null;
	     
	     String action = request.getPathInfo(); // 요청 주소 추출
	     System.out.println("action : " + action);
	     
	     if (action != null && action.equals("/confirmDuplicate.do")) { // 아이디 중복 조회
	    	 PrintWriter out = response.getWriter();
	    	 
	    	 id = (String) request.getParameter("id");
	    	 memberDAO = new MemberDAO();
	    	 boolean result = memberDAO.confirmDuplicate(id);
	    	 
	    	 if (result) {
	    		 out.write("not_usable");
	    	 } else {
	    		 out.write("usable");
	    	 }
	    	 return;
	     }
	     
	     member = new MemberDTO();
	     
	     // 관심사 이니셜만 뽑아내서 join으로 결합
	     List<String> interests = new ArrayList<>();
	     List<String> interestsName = new ArrayList<>();
	     String joinedInterests = "";
	     
	     interestsName.add("reading");
	     interestsName.add("travel");
	     interestsName.add("gaming");
	     interestsName.add("movie");
	     interestsName.add("exercise");
	     interestsName.add("cooking");
	     interestsName.add("programming");
	     interestsName.add("song");
	     interestsName.add("language");
	     interestsName.add("others");
	     
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
	    			 if (interestsName.contains(item.getFieldName())) {
	    				 interests.add(item.getString("utf-8"));
	    			 } else if ("id".equals(item.getFieldName())) {
	    				 member.setId(item.getString("utf-8"));
	    				 id = item.getString("utf-8");
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
	    			    	 nextPath = "/errorLog.jsp";
	    			    	 RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPath);
	    			    	 requestDispatcher.forward(request, response);
	    			     }
	    			 } else if ("email".equals(item.getFieldName())) {
	    				 member.setEmail(item.getString("utf-8"));
	    			 } else if ("job".equals(item.getFieldName())) {
	    				 member.setJob(item.getString("utf-8"));
	    			 }
	    		 } else if (item.getSize() > 0) {
	    			 imgFile = item;
	    		 }
	    	 }
	    	 
	    	 if (imgFile != null) {
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
	    	 } else {
	    		 member.setImage("0_p_img.jpg");
	    	 }
	    	 
		} catch (Exception e) {
			System.err.println("File Upload ERR : " + e.getMessage());
	    	 nextPath = "/errorLog.jsp";
	    	 RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPath);
	    	 requestDispatcher.forward(request, response);
		}
    	    
	     // 관심사 이니셜 결합해서 추가
	     if (interests != null & !interests.isEmpty()) {
	    	 joinedInterests = String.join("", interests);
	     }
	    
	     member.setInterest(joinedInterests);

	     System.out.println(member.toString());
	     memberDAO = new MemberDAO();
	     resultQ = memberDAO.signup(member);
	     System.out.println(resultQ);
	     if (!resultQ) {
	    	 nextPath = "/errorLog.jsp";
	     }
	     RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPath);
    	 requestDispatcher.forward(request, response);
	} // doPOST() END
}

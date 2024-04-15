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
		PrintWriter out = response.getWriter(); 
		request.setCharacterEncoding("utf-8");
	     response.setContentType("text/html; charset=utf-8");
	     String id = "";
	     boolean resultQ = false;
	     FileItem imgFile = null;
	     File uploadFile = null;
	     
	     String action = request.getPathInfo(); // 요청 주소 추출
	     System.out.println("action : " + action);
	     
	     if (action != null && action.equals("/confirmDuplicate.do")) { // 아이디 중복 조회
	    	 id = (String) request.getParameter("id");
	    	 memberDAO = new MemberDAO();
	    	 boolean result = memberDAO.confirmDuplicate(id);
	    	 
	    	 // 아이디 중복됐을 시 사용할 수 없다고 넘겨주고, 중복 아닐 시 사용 가능하다고 설정
	    	 if (result) {
	    		 out.write("not_usable");
	    	 } else {
	    		 out.write("usable");
	    	 }
	    	 return;
	     }
	     
	     
	     // member 객체 생성
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
	    				 // id
	    			 } else if ("id".equals(item.getFieldName())) {
	    				 member.setId(item.getString("utf-8"));
	    				 id = item.getString("utf-8");
	    				 // pwd
	    			 } else if ("pwd".equals(item.getFieldName())) {
	    				 member.setPwd(item.getString("utf-8"));
	    				 // name
	    			 }  else if ("name".equals(item.getFieldName())) {
	    				 member.setName(item.getString("utf-8"));
	    				// birthday
	    			 } else if ("birthday".equals(item.getFieldName())) {
	    			     // birthday java.sql.date 속성으로 변환
	    			     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    			     java.util.Date utilDate = new Date(System.currentTimeMillis());
	    			     try {
	    			    	 utilDate = format.parse(item.getString("utf-8"));
	    			    	 java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	    			    	 member.setBirthday(sqlDate);
	    			     } catch (ParseException e) {
	    			    	 System.out.println(e.getMessage());
	    			    	 out.write("birthday_err");
	    			     }
	    			     // email일 시
	    			 } else if ("email".equals(item.getFieldName())) {
	    				 member.setEmail(item.getString("utf-8"));
	    				 // job일 시
	    			 } else if ("job".equals(item.getFieldName())) {
	    				 member.setJob(item.getString("utf-8"));
	    			 }
	    			 // 만약 파일일 시, 해당 item 일단 imgFile에 저장
	    		 } else if (item.getSize() > 0) {
	    			 imgFile = item;
	    		 }
	    	 }
	    	 
	    	 // imgFile이 null이 아니라면, 즉 사용자가 업로드한 파일이 존재한다면, 파일 업로드 실행
	    	 if (imgFile != null) {
	    		 // 앞에서 구했던 실제 경로를 통해 파일 객체 생성
	    		 File currentPath = new File(realPath);
    		     // file 이름 구하기
    			 int idx = imgFile.getName().lastIndexOf("\\");
    			 if (idx == -1) {
    				 idx = imgFile.getName().lastIndexOf("/");
    			 }
    			 String fileName = imgFile.getName().substring(idx + 1);
    			 // 파일 확장자가 어떻게 되는지 구한 뒤, id_p_img.확장자 명으로 db에 저장
    			 int lastDotIndex = fileName.lastIndexOf(".");
    			 member.setImage(id + "_p_img" + fileName.substring(lastDotIndex));
    			 // 같은 이름으로 파일 경로 생성
    			 uploadFile = new File(currentPath + "\\" + id + "_p_img" + fileName.substring(lastDotIndex));
    			 // 경로에 저장함
	 			imgFile.write(uploadFile);
	    	 } else {
	    		 // 만약 사용자가 업로드 하지 않았다면, 기본 이미지 설정
	    		 member.setImage("0_p_img.jpg");
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

	     // 실제로 member 회원가입 실행
	     memberDAO = new MemberDAO();
	     resultQ = memberDAO.signup(member);

	     if (!resultQ) {
	    	 out.write("sign up error");
	     } else {
	    	 out.write("sign up complete!");
	     }
	} // doPOST() END
}

package servlet.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.FileDAO;
import dto.FileDTO;
/**
 * /file/fileUploadServlet
 * 
 * @author nsr
 * @since 2024-04-08
 **/
@WebServlet("/fileUploadServlet")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	int todoNo = 0;
	
    public FileUploadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {    
	}
	
	/**
	 * 인코딩
	 * 파일 다운로드 & FileDTO List 생성
	 * FileDTO insert
	 * 저장된 파일 수 응답보내기
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		

		// 인코딩
		request.setCharacterEncoding("utf-8");

		// 파일 다운로드 & FileDTO List 생성
		ArrayList<String> fileNameArray = uploadFile(request, response);
		
		// FileDTO List insert
		int file = 0;
		FileDAO fileDAO = new FileDAO();
		for(int i = 0; i < fileNameArray.size(); i++) {
			if(fileDAO.insertFiles(fileNameArray.get(i), todoNo))
				file++;
		}
			
		// 저장된 파일 수 응답보내기
		System.out.println("파일 수 : " + file);
		response.getWriter().write(String.valueOf(file));
		
		
	}
	
	private ArrayList<String> uploadFile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<String> fileNameArray = new ArrayList<String>();
		String realPath = getRealPath();
		// 파일 객체
		File currentPath = new File(realPath);

		// 파일 업로드를 위한 설정 객체
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentPath);
		factory.setSizeThreshold(1024 * 1024);

		// 서블릿 파일 업로드 객체
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 외부 자원 다루기
		try {
			List items = upload.parseRequest(request);
			
			for(int i = 0; i < items.size(); i++) {
				System.out.println("업로드할 데이터 추출 시작");
				FileItem fileitem = (FileItem)items.get(i);

				// form data
				if(fileitem.isFormField()) {
					System.out.println("==> " +  fileitem.getFieldName() + " " + fileitem.getString("utf-8"));
					todoNo = Integer.parseInt(fileitem.getString("utf-8"));
				}else {
					if(fileitem.getSize() > 0) {
						System.out.println("==> 파일 이름 : " + fileitem.getName());
						int idx = fileitem.getName().lastIndexOf("\\");
						
						if(idx == -1) {
						 	idx = fileitem.getName().lastIndexOf("/");
						}
						String fileName = fileitem.getName().substring(idx + 1);
						
						fileNameArray.add(fileName);
						File uploadFile = new File(currentPath + "\\" + fileName);
						fileitem.write(uploadFile);
						
					}
				}
			}
			
		} catch (Exception e) {
			System.err.println("FILE UPLOAD FAIL!!!" + e.getMessage());
		}
		
		return fileNameArray;

	}
	
	private String getRealPath() {
		
		 ServletContext context = getServletContext();
		 String realPath = context.getRealPath("/uploads/files/");
	        File folder = new File(realPath);

	        // 폴더 생성
	        if (!folder.exists()) {
	            folder.mkdirs();  // 폴더 생성
	            
	        }
	        
	     System.out.println("실제 경로: " + realPath);
	 
	     return realPath;
	}

}

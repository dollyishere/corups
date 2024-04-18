package servlet.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * /file/fileDownloadServlet
 * 
 * @author nsr
 * @since 2024-04-08
 **/
@WebServlet("/fileDownloadServlet")
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FileDownloadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		downloadFile(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	}
	

	protected void downloadFile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("utf-8");
		
		String file_repo = getRealPath();
		String fileName = (String) request.getParameter("fileName");
		System.out.println("==> fileName : " + fileName);
		
		// 파일 경로 설정
		String downFile = file_repo + "\\" + fileName;
		File f = new File(downFile);
		
		// 웹 브라우저의 캐시를 비활성화
		response.setHeader("Cache-Control", "no-Cache");
		
		// 파일 다운로드 설정
		response.setContentType(getContentTypeByFileName(fileName));
		response.setHeader("Content-disposition", "attachment; fileName=" + fileName);
		
		OutputStream out = response.getOutputStream();
		FileInputStream in = new FileInputStream(f);
		
		// 파일 전송
		byte[] buffer = new byte[1024 * 8];
		while(true) {
			int count = in.read(buffer);
			if(count == -1) {
				break;
			}
			out.write(buffer, 0, count);
		}
		
		in.close();
		out.close();
	}
	
	private String getContentTypeByFileName(String fileName) {
		if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
			return "image/jpeg";
		} else if (fileName.endsWith(".png")) {
			return "image/png";
		} else if (fileName.endsWith(".gif")) {
			return "image/gif";
		} else if (fileName.endsWith(".pdf")) {
			return "application/pdf";
		} else if (fileName.endsWith(".doc") || fileName.endsWith(".docx")) {
			return "application/msword";
		} else if (fileName.endsWith(".xls") || fileName.endsWith(".xlsx")) {
			return "application/vnd.ms-excel";
		} else if (fileName.endsWith(".zip")) {
			return "application/zip";
		}
		else if (fileName.endsWith(".txt")) {
			return "text/plain";
		} else {
			return "application/octet-stream";
		}
	}
	
	private String getRealPath() {
		
		 ServletContext context = getServletContext();
	     String realPath = context.getRealPath("/uploads/files/");
	     System.out.println("실제 경로: " + realPath);
	 
	     return realPath;
	}

}

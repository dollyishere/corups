package controller.chapter;
/**
 * /chapter/chapterUpdateServlet
 * 
 * @author cyb
 * @since 2024-04-09
 **/
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/chapter/chapterUpdateServlet")
public class chapterUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public chapterUpdateServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

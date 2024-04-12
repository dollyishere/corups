package servlet.files;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	}

}

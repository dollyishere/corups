package utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionUtil {

	public static String getID(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        String id = "";
        
        if (session == null) {
            response.getWriter().append("Session not found");
        } else {
            id = (String) session.getAttribute("id");
            if (id == null) {
                response.getWriter().append("Username attribute not found in session");
            }
        }
        
        return id;
    }
}

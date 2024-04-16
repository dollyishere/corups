package controller.study;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudyDAO;
import dto.StudyDTO;

@WebServlet("/study/studySearchServlet")
public class StudySearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public StudySearchServlet() {
        super();
    }

    /**
     * 인코딩
     * searchText, searchCategory 가져오기
     * searchStudy(searchText, searchCategory);
     * searchText, searchCategory, studyArray 보내기
     * -> study/studyList.jsp 으로 
     */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) 
         throws ServletException, IOException {
         //  인코딩
         request.setCharacterEncoding("UTF-8");
         response.setContentType("text/html; charset=UTF-8");
         
         // status, searchText 가져오기
         String searchCategory = request.getParameter("searchCategory");
         if(searchCategory =="" || searchCategory == null)
            searchCategory = "ALL";
         String searchText = request.getParameter("searchText");

         // searchStudy(searchText, searchCategory);
         StudyDAO studyDAO = new StudyDAO();
         ArrayList<StudyDTO> studyArray = studyDAO.searchStudy(searchText, searchCategory);
         
         
         //-> study/studyList.jsp
         request.setAttribute("searchCategory", searchCategory);
         request.setAttribute("searchText", searchText);
         request.setAttribute("studyList", studyArray);	
         RequestDispatcher requestDispatcher = request.getRequestDispatcher("studyList.jsp");
         requestDispatcher.forward(request, response);
      
      
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) 
         throws ServletException, IOException {
      doGet(request, response);
   }

}

package controller.chapter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChapterDAO;
import dto.ChapterDTO;

/**
 * /chapter/chapterDeleteServlet
 * @since 2024.04.09
 * @author cyb
 */
@WebServlet("/chapter/chapterDeleteServlet")
public class ChapterDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ChapterDAO chapterDAO;

    public ChapterDeleteServlet() {
        super();
        chapterDAO = new ChapterDAO(); // ChapterDAO 객체 초기화
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 삭제할 챕터 번호 파라미터 가져오기
        int chapterNo = Integer.parseInt(request.getParameter("chapterNo"));
        
        // 해당 챕터에 속한 스터디 번호 가져오기
        ChapterDTO chapter = chapterDAO.chapterDetail(chapterNo);
        int studyNo = chapter.getStudyNo();

        // 챕터 삭제
        boolean success = chapterDAO.deleteChapter(chapterNo);
        System.out.println(success);

        if (success) {
            // 챕터 삭제 성공 시 해당 스터디의 상세 페이지로 이동
            response.sendRedirect(request.getContextPath() + "/study/studyDetailServlet?studyNo=" + studyNo);
        } else {
            // 챕터 삭제 실패 시 에러 페이지로 이동
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}

package controller;

import DAO.CategoryDAO;
import DAO.FeedbackDAO;
import entity.Category;
import entity.Feedback;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Quoc_anh
 */
public class FeedbackUser extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //set list category will show in Sidebar
        HttpSession session = req.getSession();
        if (session.getAttribute("username") == null) {
            resp.sendRedirect("Login");
            return;
        }
        CategoryDAO cadao = new CategoryDAO();
        ArrayList<Category> listca = cadao.getListCategory();
        req.setAttribute("listca", listca);
        req.getRequestDispatcher("Feedback.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("username") == null) {
            resp.sendRedirect("Login");
            return;
        }
        
        String title= req.getParameter("title");
        String content= req.getParameter("content");
        
        Feedback fb= new Feedback(title, content, session.getAttribute("username").toString());
        FeedbackDAO fdao= new FeedbackDAO();
        fdao.insertFeedback(fb);
        resp.sendRedirect("Feedback");
    }
    
    
}

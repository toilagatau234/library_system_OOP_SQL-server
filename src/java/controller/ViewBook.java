package controller;

import DAO.BookDAO;
import DAO.CategoryDAO;
import entity.Book;
import entity.Category;
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
public class ViewBook extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("username") == null ||session.getAttribute("role")==null|| !session.getAttribute("role").equals("admin")) {
            resp.sendRedirect("Login");
            return;
        }
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            BookDAO bDAO = new BookDAO();
            Book book = bDAO.getBookById(id);
            CategoryDAO cDAO = new CategoryDAO();
            ArrayList<Category> list = cDAO.getListCategory();
            req.setAttribute("category", list);

            req.setAttribute("book", book);
            req.getRequestDispatcher("ViewBook.jsp").forward(req, resp);
            
        } catch (Exception e) {
        }
        
    }

}

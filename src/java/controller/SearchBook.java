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
import java.util.Map;

/**
 *
 * @author Quoc_anh
 */
public class SearchBook extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("username") == null) {
            resp.sendRedirect("Login");
            return;
        }
        String name = req.getParameter("name");

        CategoryDAO cDAO = new CategoryDAO();
        Map<Integer, Category> mapCategory = cDAO.getMapCategory();
        BookDAO bDAO = new BookDAO();
        ArrayList<Book> listAllBook = bDAO.getBookByName(name);
        int total = listAllBook.size();
        int bookPerPage = 5;
        int numberOfPage = (total % bookPerPage == 0) ? (total / bookPerPage) : (total / bookPerPage + 1); //Số trang
        int page;
        String xpage = req.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        
        int start = (page - 1) * bookPerPage;
        int end = Math.min((page) * bookPerPage, total);
        ArrayList<Book> list = bDAO.getListBookByPage(listAllBook, start, end);
        req.setAttribute("mapCategory", mapCategory);
        req.setAttribute("listBook", list);
        req.setAttribute("page", page);
        req.setAttribute("numberOfPage", numberOfPage);
        req.setAttribute("name", name);

        if (session.getAttribute("role") == "admin") {
            req.getRequestDispatcher("SearchBookAdmin.jsp").forward(req, resp);
        } else {
            CategoryDAO cadao = new CategoryDAO();
            ArrayList<Category> listca = cadao.getListCategory();
            req.setAttribute("listca", listca);
            req.getRequestDispatcher("SearchBookUser.jsp").forward(req, resp);
        }

    }

}

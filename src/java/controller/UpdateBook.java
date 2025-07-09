package controller;

import DAO.BookDAO;
import entity.Book;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author Quoc_anh
 */
public class UpdateBook extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("role") == null || !session.getAttribute("role").equals("admin")) {
            resp.sendRedirect("Login");
            return;
        }
        try {
            int bookid = Integer.parseInt(request.getParameter("bookid"));
            String name = request.getParameter("name");
            String author = request.getParameter("author");
            int category = Integer.parseInt(request.getParameter("category"));
            String publisher = request.getParameter("publisher");
            String avt = request.getParameter("avt");
            if(avt.length()== 0){
                avt= "img/book/book.jpg";
            }
            String language = request.getParameter("language");
            int total = Integer.parseInt(request.getParameter("total"));
            int current = Integer.parseInt(request.getParameter("current"));
            String position = request.getParameter("position");
            System.out.println(avt);
            Book newBook = new Book(bookid, name, author,avt, category, publisher, language, total, current, position);
            BookDAO bDAO = new BookDAO();
            bDAO.updateBook(newBook);
            resp.sendRedirect("ViewBook?id="+bookid);
        } catch (Exception e) {
        }
    }

}

package controller;

import DAO.BookDAO;
import DAO.BorrowerDAO;
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
public class ActionBorrow extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("username") == null) {
            resp.sendRedirect("Login");
            return;
        }
        
        String url= req.getParameter("url");
        String bookid= req.getParameter("id");

        BookDAO bookdao= new BookDAO();
        Book book= bookdao.getBookById(Integer.parseInt(bookid));
        book.setCurrent(book.getCurrent()-1);
        bookdao.updateBook(book);
        //Update quantity book
        
        //insert into borrower
        BorrowerDAO bdao= new BorrowerDAO();
        bdao.insertBorrower(session.getAttribute("username").toString(), bookid);
        resp.sendRedirect(url);
    }

}

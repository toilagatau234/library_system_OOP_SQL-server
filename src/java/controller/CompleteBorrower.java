package controller;

import DAO.BookDAO;
import DAO.BorrowerDAO;
import entity.Book;
import entity.Borrower;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Quoc_anh
 */
public class CompleteBorrower extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("role") == null || !session.getAttribute("role").equals("admin")) {
            resp.sendRedirect("Login");
            return;
        }
        //Update quantity of book when user borrow
        String bookidStr= req.getParameter("bookid");
        int bookid=0;
        try {
            bookid= Integer.parseInt(bookidStr);
        } catch (Exception e) {
        }
        BookDAO bdao= new BookDAO();
        Book book= bdao.getBookById(bookid);
        book.setCurrent(book.getCurrent()-1);
        bdao.updateBook(book);
        
        //Update borrow status to borrowed 
        String id = req.getParameter("id");
        BorrowerDAO bDAO = new BorrowerDAO();
        Borrower b = bDAO.getBorrowerById(id);
        b.setStatus("Borrowed");
        
        //and set date_from
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        b.setBorrow_from(dateFormat.format(new Date()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 14);
        b.setBorrow_to(dateFormat.format(calendar.getTime()));
        
        bDAO.updateBorrower(b);
        resp.sendRedirect("ListBorrowAdmin?action=processing");
    }

}

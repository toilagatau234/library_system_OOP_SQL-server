package controller;

import DAO.BorrowerDAO;
import entity.Borrower;
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
public class SearchBorrower extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("role") == null || !session.getAttribute("role").equals("admin")) {
            resp.sendRedirect("Login");
            return;
        }

        String action = req.getParameter("action");
        String username= req.getParameter("name");
        if (action == null || action.length() == 0) {
            resp.sendRedirect("HomePageAdmin");
            return;
        }
        if (username == null || username.length() == 0) {
            resp.sendRedirect("ListBorrowAdmin?action="+action);
            return;
        }
        BorrowerDAO bDAO = new BorrowerDAO();
               System.out.println(action+" "+ username);
        ArrayList<Borrower> listAll = bDAO.getBorrowerByStatusAndUserId(action, username);
        req.setAttribute("action", action);
        req.setAttribute("usernameSearch", username);
        req.setAttribute("list", listAll);
        switch (action) {
            case "processing":
                req.getRequestDispatcher("ListProcessingAdmin.jsp").forward(req, resp);
                break;
            case "borrowed":
                req.getRequestDispatcher("ListBorrowedAdmin.jsp").forward(req, resp);
                break;
            case "returned":
                req.getRequestDispatcher("ListReturnedAdmin.jsp").forward(req, resp);
                break;
            default:

        }
    }

}

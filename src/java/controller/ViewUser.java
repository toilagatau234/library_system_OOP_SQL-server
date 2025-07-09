package controller;

import DAO.CategoryDAO;
import DAO.UserDAO;
import entity.Category;
import entity.User;
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
public class ViewUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("role") == null) {
            resp.sendRedirect("Login");
            return;
        }
        //Is Admin
        if (session.getAttribute("role").equals("admin")) {
            String username = req.getParameter("id");
            UserDAO uDAO = new UserDAO();
            User user = uDAO.findUserByUsername(username);
            req.setAttribute("user", user);
            req.getRequestDispatcher("ViewUser.jsp").forward(req, resp);
            return;
        }

        //is User
        if (session.getAttribute("role").equals("user")) {
            String username = session.getAttribute("username").toString();
            UserDAO uDAO = new UserDAO();
            User user = uDAO.findUserByUsername(username);

            //list category in sidebar
            CategoryDAO cadao = new CategoryDAO();
            ArrayList<Category> listca = cadao.getListCategory();
            req.setAttribute("listca", listca);
            req.setAttribute("user", user);
            req.getRequestDispatcher("ViewUserByUser.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}

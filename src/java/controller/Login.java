package controller;

import DAO.UserDAO;
import entity.User;
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
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("username") != null) {
            if (session.getAttribute("role").equals("admin")) {
                resp.sendRedirect("HomePageAdmin");
                return;
            } else {
                resp.sendRedirect("HomePageUser");
                return;
            }
        }

        req.getRequestDispatcher("Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") != null) {
            if (session.getAttribute("role").equals("admin")) {
                response.sendRedirect("HomePageAdmin");
                return;
            } else {
                response.sendRedirect("HomeUserPage");
                return;
            }
        }

        UserDAO uDAO = new UserDAO();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = uDAO.findUser(username, password);
        if (user != null) {
            // Check the role of the user
            if (user.isRole()) {
                // Redirect to the admin page
                session.setAttribute("username", username);
                session.setAttribute("role", "admin");
                response.sendRedirect("HomePageAdmin");
            } else {
                // Redirect to the user page
                session.setAttribute("username", username);
                session.setAttribute("role", "user");
                response.sendRedirect("HomePageUser");
            }
        } else {
            request.setAttribute("message", "Username or Password is wrong");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }

}

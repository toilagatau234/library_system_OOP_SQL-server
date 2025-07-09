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
public class UpdateUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("role") == null) {
            resp.sendRedirect("Login");
            return;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String avt = request.getParameter("avt");
        String name = request.getParameter("name");
        boolean sex = Boolean.parseBoolean(request.getParameter("sex"));
        String datebirth = request.getParameter("datebirth");
        String phone = request.getParameter("phone");
        String gmail = request.getParameter("gmail");
        User user = new User(username, password, name, avt, sex, datebirth, phone, gmail);
        UserDAO uDAO = new UserDAO();
        uDAO.updateUser(user);
        //when admin update
        if (session.getAttribute("role").equals("admin")) {
            resp.sendRedirect("ViewUser?id=" + username);
        }

        //when userupdate
        if (session.getAttribute("role").equals("user")) {
//            CategoryDAO cadao = new CategoryDAO();
//            ArrayList<Category> listca = cadao.getListCategory();
//            request.setAttribute("listca", listca);
//            request.setAttribute("user", user);
//            request.getRequestDispatcher("ViewUserByUser.jsp").forward(request, resp);
                resp.sendRedirect("ViewUser");
        }
    }

}

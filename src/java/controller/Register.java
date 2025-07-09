package controller;

import DAO.UserDAO;
import entity.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Quoc_anh
 */
public class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("Register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String username = request.getParameter("username");
        UserDAO udao = new UserDAO();
        User u = udao.findUserByUsername(username);
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");
        String name = request.getParameter("name");
        boolean sex = Boolean.parseBoolean(request.getParameter("sex"));
        String datebirth = request.getParameter("datebirth");
        String phone = request.getParameter("phone");
        String gmail = request.getParameter("gmail");

        User user = new User();
        user.setRole(false);
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setSex(sex);
        user.setDatebirth(datebirth);
        user.setPhone(phone);
        user.setGmail(gmail);
        if (u != null) {
            request.setAttribute("message", "*Username really exit");
            request.setAttribute("user", user);
            System.out.println(user.isSex());
            request.getRequestDispatcher("Register.jsp").forward(request, resp);
            return;
        }
        if (!password.equals(passwordConfirm)) {
            request.setAttribute("message", "*Password don't match passwordConfirm");
            request.setAttribute("user", user);
            request.getRequestDispatcher("Register.jsp").forward(request, resp);
            return;
        }

        udao.insertUser(user);

        //validate session then login again
        HttpSession session = request.getSession();
        session.invalidate();
        request.setAttribute("messagRegister", "Register susscessfully, please Login");
        request.getRequestDispatcher("Login.jsp").forward(request, resp);
    }

}

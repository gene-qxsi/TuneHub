package com.matrosov.servlet;

import com.matrosov.service.UserService;
import com.matrosov.dao.UserDao;
import com.matrosov.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = new UserService(UserDao.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Optional<User> maybeUser = userService.findByUsernameAndPassword(username, password);

        if (maybeUser.isEmpty()) {
            req.setAttribute("error", "Пользователь с указанными учетными данными не найден.");

            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            session.setAttribute("user", maybeUser.get());
//            resp.sendRedirect(req.getContextPath() + "app.jsp");
            resp.sendRedirect("viewData");
        }
    }

}

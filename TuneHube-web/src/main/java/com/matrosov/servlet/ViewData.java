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
import java.util.List;

@WebServlet("/viewData")
public class ViewData extends HttpServlet {

    private final UserService userService = new UserService(UserDao.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String contextPath = req.getContextPath();

        List<User> users = userService.findAll();
        users.forEach(System.out::println);
        session.setAttribute("users", users);

        resp.sendRedirect(contextPath + "app.jsp");
    }
}














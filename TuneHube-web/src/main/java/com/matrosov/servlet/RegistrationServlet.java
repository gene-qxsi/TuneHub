package com.matrosov.servlet;

import com.matrosov.dao.UserDao;
import com.matrosov.entity.Gender;
import com.matrosov.entity.Role;
import com.matrosov.entity.User;
import com.matrosov.mapper.UserDtoMapper;
import com.matrosov.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final UserService userService = new UserService(UserDao.getInstance());

    private final UserDtoMapper mapper = new UserDtoMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(req.getContextPath() + "/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contextPath = req.getContextPath();
        System.out.println(contextPath);
        try {
            User user = User.builder().
                    username(req.getParameter("username"))
                    .password(req.getParameter("password"))
                    .email(req.getParameter("email"))
                    .birthday(LocalDate.parse(req.getParameter("birthday")))
                    .role(Role.valueOf(req.getParameter("role")))
                    .gender(Gender.valueOf(req.getParameter("gender")))
                    .created_at(LocalDateTime.now())
                    .build();

            userService.save(user);

            req.getSession().setAttribute("user", user);
            resp.sendRedirect("viewData");
//            resp.sendRedirect(contextPath + "/app.jsp");

        } catch (DateTimeParseException e) {
            req.setAttribute("error", "Неверный формат даты");
            req.getRequestDispatcher(contextPath + "/registration.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", "Произошла ошибка при регистрации. Попробуйте снова.");
            req.getRequestDispatcher(contextPath + "/registration.jsp").forward(req, resp);
        }

    }
}

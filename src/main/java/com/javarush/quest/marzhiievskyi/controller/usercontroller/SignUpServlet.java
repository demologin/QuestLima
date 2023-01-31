package com.javarush.quest.marzhiievskyi.controller.usercontroller;

import com.javarush.quest.marzhiievskyi.entity.User;
import com.javarush.quest.marzhiievskyi.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "SignUpServlet", value = "/signup")
public class SignUpServlet extends HttpServlet {

    UserService userService = UserService.USER_SERVICE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/signup.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User userToCreate = User.builder()
                .id(0L)
                .login(request.getParameter("login"))
                .password(request.getParameter("password"))
                .build();

        if (!userService.checkUserExist(userToCreate)) {
            response.sendRedirect("signup");
        } else {
            userService.create(userToCreate);
            HttpSession session = request.getSession();
            session.setAttribute("user", userToCreate);
            response.sendRedirect("profile");
        }

    }
}

package com.javarush.quest.torkel.controller;

import com.javarush.quest.torkel.config.Winter;
import com.javarush.quest.torkel.entity.Role;
import com.javarush.quest.torkel.entity.User;
import com.javarush.quest.torkel.service.ImageService;
import com.javarush.quest.torkel.service.UserService;
import com.javarush.quest.torkel.util.Go;
import com.javarush.quest.torkel.util.Jsp;
import com.javarush.quest.torkel.util.Key;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SignupServlet", value = Go.SIGNUP)
@MultipartConfig(fileSizeThreshold = 1 << 20)
public class SignupServlet extends HttpServlet {

    private final UserService userService = Winter.getBean(UserService.class);
    private final ImageService imageService = Winter.getBean(ImageService.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        config.getServletContext().setAttribute(Key.ROLES, Role.values());
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Jsp.forward(request,response, Go.SIGNUP);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = User.builder()
                .id(0L)
                .login(request.getParameter(Key.LOGIN))
                .password(request.getParameter(Key.PASSWORD))
                .role(Role.valueOf(request.getParameter(Key.ROLE)))
                .build();
        userService.create(user);
        imageService.uploadImage(request, user.getImage());
        Jsp.redirect(response, Go.USERS);
    }
}
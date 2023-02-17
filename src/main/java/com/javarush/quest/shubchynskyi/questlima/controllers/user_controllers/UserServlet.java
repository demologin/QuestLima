package com.javarush.quest.shubchynskyi.questlima.controllers.user_controllers;

import com.javarush.quest.shubchynskyi.questlima.entity.user.Role;
import com.javarush.quest.shubchynskyi.questlima.entity.user.User;
import com.javarush.quest.shubchynskyi.questlima.exception.AppException;
import com.javarush.quest.shubchynskyi.questlima.service.ImageService;
import com.javarush.quest.shubchynskyi.questlima.service.UserService;
import com.javarush.quest.shubchynskyi.questlima.util.Go;
import com.javarush.quest.shubchynskyi.questlima.util.Jsp;
import com.javarush.quest.shubchynskyi.questlima.util.Key;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@WebServlet(name = "UserServlet", value = Go.USER)
@MultipartConfig(fileSizeThreshold = 1 << 20)
public class UserServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        config.getServletContext().setAttribute(Key.ROLES, Role.values());
        super.init(config);
    }

    private final UserService userService = UserService.USER_SERVICE;
    private final ImageService imageService = ImageService.IMAGE_SERVICE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String parameterId = request.getParameter(Key.ID);
        request.setAttribute(Key.ID, parameterId);
        if (Objects.nonNull(parameterId)) {
            long id = Long.parseLong(parameterId);
            Optional<User> optionalUser = userService.get(id);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                request.setAttribute(Key.USER, user);
            }
            Jsp.forward(request, resp, Go.USER);
        }
        Jsp.redirect(resp, Go.USERS);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        User user = User.builder()
                .id(Long.valueOf(request.getParameter(Key.ID)))
                .login(request.getParameter(Key.LOGIN))
                .password(request.getParameter(Key.PASSWORD))
                .role(Role.valueOf(request.getParameter(Key.ROLE)))
                .build();

        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap.containsKey(Key.CREATE)) {
            userService.create(user);
        } else if (parameterMap.containsKey(Key.UPDATE)) {
            userService.update(user);
        } else if (parameterMap.containsKey(Key.DELETE)) {
            userService.delete(user);
        } else throw new AppException(Key.UNKNOWN_COMMAND);

        imageService.uploadImage(request, user.getImage());
        resp.sendRedirect(Go.USERS);
    }
}

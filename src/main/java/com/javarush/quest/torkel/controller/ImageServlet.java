package com.javarush.quest.torkel.controller;

import com.javarush.quest.torkel.config.Winter;
import com.javarush.quest.torkel.service.ImageService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet(value = "/images/*", name = "ImageServlet")
public class ImageServlet extends HttpServlet {

    private final ImageService imageService = Winter.getBean(ImageService.class);


    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String requestURI = req.getRequestURI();
        String target = req.getContextPath() + "/images/";
        String nameImage = requestURI.replace(target, "");
        Path path = imageService.getImagePath(nameImage);
        Files.copy(path, resp.getOutputStream());
    }
}

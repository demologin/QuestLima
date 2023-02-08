package com.javarush.parfenov.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.parfenov.repository.NodeRepository;
import com.javarush.parfenov.repository.ParentToChildRepository;
import com.javarush.parfenov.service.JsonPrepareService;
import com.javarush.parfenov.util.JsonStringExtractor;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SendJsonServlet", value = "/returnJson")
public class SendJsonServlet extends HttpServlet {

    private final JsonPrepareService jsonPrepareService = JsonPrepareService.INSTANCE;

    //    private final NodeRepository nodeRepository = NodeRepository.INSTANCE;
//    private final ParentToChildRepository parentToChildRepository = ParentToChildRepository.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonString = JsonStringExtractor.getJsonParameter(request);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonString);
        long questId = root.get("questId").asLong();
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(jsonPrepareService.getJson(questId));
        out.flush();
    }
}

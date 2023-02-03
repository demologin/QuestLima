package com.javarush.quest.shubchynskyi.questlima.controllers.quest_controllers;

import com.javarush.quest.shubchynskyi.questlima.entity.game.Quest;
import com.javarush.quest.shubchynskyi.questlima.service.QuestService;
import com.javarush.quest.shubchynskyi.questlima.util.Go;
import com.javarush.quest.shubchynskyi.questlima.util.Jsp;
import com.javarush.quest.shubchynskyi.questlima.util.Key;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "QuestsListServlet", value = Go.QUESTS_LIST)
public class QuestsListServlet extends HttpServlet {

    private final QuestService questService = QuestService.QUEST_SERVICE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<Quest> quests = questService.getAll();
        request.setAttribute(Key.QUESTS, quests);
        Jsp.forward(request, response, Key.QUESTS_LIST);
    }
}

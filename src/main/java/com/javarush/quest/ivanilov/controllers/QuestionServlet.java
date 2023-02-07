package com.javarush.quest.ivanilov.controllers;

import com.javarush.quest.ivanilov.entities.game.Event;
import com.javarush.quest.ivanilov.entities.game.Game;
import com.javarush.quest.ivanilov.entities.game.Option;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.services.*;
import com.javarush.quest.ivanilov.utils.Navigator;
import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Jsp;
import com.javarush.quest.ivanilov.utils.constants.Messages;
import com.javarush.quest.ivanilov.utils.constants.Targets;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "QuestionServlet", value = Targets.QUESTION)
public class QuestionServlet extends HttpServlet {

    GameWorker gameWorker;
    EventService eventService;
    GameService gameService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gameWorker = new GameWorkerImpl(
                GameService.GAME_SERVICE,
                UserService.USER_SERVICE,
                QuestService.QUEST_SERVICE
        );
        eventService = EventService.EVENT_SERVICE;
        gameService = GameService.GAME_SERVICE;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Event event = (Event) req.getSession().getAttribute(Attributes.CURR_EVENT);
        Option[] optionsToSend = gameWorker.getOptionsToSend(eventService.get(event.getId()));
        req.setAttribute(Attributes.MESSAGE, event.getTask().getDescription());
        req.setAttribute(Attributes.OPTIONS, optionsToSend);
        Navigator.dispatch(req, resp, Jsp.QUESTION_JSP);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            HttpSession session = req.getSession();
            Event event = (Event) session.getAttribute(Attributes.CURR_EVENT);
            int optionId = Integer.parseInt(req.getParameter(Attributes.OPTION_ID));
            Event resultEvent = gameWorker.validateAnswer(event, optionId);
            session.setAttribute(Attributes.CURR_EVENT, resultEvent);
            User user = (User) session.getAttribute(Attributes.USER);
            Game game = gameService.get(user.getCurrentGameId());
            game.setCurrEventId(resultEvent.getId());
            gameService.update(game);
            Navigator.redirect(req, resp, Targets.PLAY);
        } catch (Exception e) {
            Navigator.redirectError(req, resp, Messages.ANSER_NOT_SUBMITTED, e);
        }
    }
}

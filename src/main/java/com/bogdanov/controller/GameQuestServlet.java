package com.bogdanov.controller;

import com.bogdanov.entity.Quest;
import com.bogdanov.entity.Question;
import com.bogdanov.repository.QuestRepository;
import com.bogdanov.service.AnswerService;
import com.bogdanov.service.QuestService;
import com.bogdanov.service.QuestionService;
import com.bogdanov.util.Go;
import com.bogdanov.util.Jsp;
import com.bogdanov.util.Key;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@WebServlet(name = "GameQuestServlet", value = Go.GAME_QUEST)
@MultipartConfig(fileSizeThreshold = 1<<20)
public class GameQuestServlet extends HttpServlet {





    public final QuestService  service= QuestService.QUEST_SERVICE;
    public final QuestionService questionService = QuestionService.QUESTION_SERVICE;
    public final AnswerService answerService = AnswerService.ANSWER_SERVICE;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String parameter = request.getParameter(Key.ID);
        Optional<Quest> quest;
        quest = service.get(Long.valueOf(parameter));

        Collection<Question> questions = quest.get().getQuestions();
        ArrayList<Question> questionArrayList = new ArrayList<>(questions);

        String questions1 = request.getParameter(Key.NUM_QUESTION);
        request.setAttribute(Key.NUM_QUESTION, questionArrayList.get(Integer.parseInt(questions1)));
        Jsp.forward(request, response, Go.GAME_QUEST);



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parameter = request.getParameter("radios");
        String numQuestion = request.getParameter(Key.NUM_QUESTION);
        String parameter1 = request.getParameter(Key.ID);
        Optional<Quest> questOptional = service.getQuestFromRep(Long.valueOf(parameter1));
        int questionsQuantity = questOptional.get().getQuestions().size();

        request.setAttribute("size",questionsQuantity);
        request.setAttribute("count",numQuestion);

        if(parameter.equals("true") &&(questionsQuantity!=Integer.parseInt(numQuestion))){
            doGet(request, response);

        }else {
            if(parameter.equals("true") &&(questionsQuantity==Integer.parseInt(numQuestion))){
                request.setAttribute("result",parameter);
            }
            Jsp.forward(request,response,Go.LOSE_PAGE);
        }
    }


}
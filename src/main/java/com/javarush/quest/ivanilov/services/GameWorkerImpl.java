package com.javarush.quest.ivanilov.services;

import com.javarush.quest.ivanilov.entities.game.*;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Strings;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@AllArgsConstructor
public class GameWorkerImpl implements GameWorker {
    GameService gameService;
    UserService userService;
    QuestService questService;

    public void initializeGame(long playerId, long questId) {
        Quest quest = questService.get(questId);
        User player = userService.get(playerId);
        Hero hero = quest.getHero();
        hero.setName(player.getLogin());
        Game newGame = Game.builder()
                .currEventId(quest.getCurrentEventId())
                .questId(questId)
                .playerId(playerId)
                .hero(hero)
                .status(GameStatus.IN_PROGRESS)
                .build();
        Game game = gameService.create(newGame);
        player.setCurrentGameId(game.getId());
        userService.update(player);
    }

    public Option[] getOptionsToSend(Event event) {
        Task question = event.getTask();
        Map<Integer, String> options = question.getOptions();
        var iterator = options.entrySet().iterator();
        Option[] optionsToSend = new Option[options.size()];

        int i = 0;
        while (iterator.hasNext()) {
            var option = iterator.next();
            optionsToSend[i] = new Option(option.getKey(), event.getId(), option.getValue());
            i++;
        }
        return optionsToSend;
    }

    public Event validateAnswer(Event event, int optionId) {
        Map<Integer, Event> results = event.getTask().getResults();
        return results.get(optionId);
    }

    @Override
    public Fight getFightToSend(HttpSession session, Event event) {
        if (event.getTask().getType() != TaskType.FIGHT) {
            throw new IllegalArgumentException();
        }

        if (session.getAttribute(Attributes.FIGHT) != null) {
            return (Fight) session.getAttribute(Attributes.FIGHT);
        }

        long eventId = event.getId();
        Task fight = event.getTask();
        User user = (User) session.getAttribute(Attributes.USER);
        Hero hero = gameService.get(user.getCurrentGameId()).getHero();
        Hero villain;

        try {
            villain = fight.getVillain().clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        HitBlockOptions options = new HitBlockOptions(BodyPart.values());

        return Fight.builder()
                .eventId(eventId)
                .taskId(fight.getId())
                .hero(hero)
                .villain(villain)
                .status(EventType.TASK)
                .fightLog(new ArrayList<>())
                .hitOptions(options.getHitOptions())
                .blockOptions(options.getBlockOptions())
                .build();
    }

    @Override
    public Fight fight(Fight currentFight, String hitOption, String blockOption) {
        Hero hero = currentFight.getHero();
        Hero villain = currentFight.getVillain();
        HitBlockOptions hitBlockOptions = new HitBlockOptions(BodyPart.values());
        Set<String> blockOptions = hitBlockOptions.parseBlock(blockOption);
        String villainHitOption = hitBlockOptions.randomHit();
        Set<String> villainBlockOptions = hitBlockOptions.randomBlock(2);
        List<String> fightLog = currentFight.getFightLog();

        calcHitsAndBlocks(hitOption, hero, villain, villainBlockOptions, fightLog);
        calcHitsAndBlocks(villainHitOption, villain, hero, blockOptions, fightLog);

        currentFight.setHero(hero);
        currentFight.setVillain(villain);

        if (hero.getHealth() <= 0) {
            currentFight.setStatus(EventType.LOSE);
            return currentFight;
        }

        if (villain.getHealth() <= 0) {
            currentFight.setStatus(EventType.WIN);
        }
        return currentFight;
    }

    public Game endGame(Event event, User user) {
        Game game = gameService.get(user.getCurrentGameId());

        switch (event.getEventType()) {
            case WIN -> game.setStatus(GameStatus.WON);
            case LOSE -> game.setStatus(GameStatus.LOST);
            case TASK -> throw new IllegalArgumentException();
        }

        user.getGamesPlayed().add(game.getId());
        user.setCurrentGameId(0);
        userService.update(user);
        return gameService.update(game);
    }

    private void calcHitsAndBlocks(String hitOption, Hero hero, Hero villain, Set<String> villainBlockOptions, List<String> fightLog) {
        if (!villainBlockOptions.contains(hitOption)) {
            int hit = hero.hit(villain);
            fightLog.add(String.format(Strings.ABOUT_HIT,
                    hero.getName(),
                    villain.getName(),
                    hit,
                    villain.getName(),
                    villain.getHealth()));
        } else {
            fightLog.add(String.format(Strings.ABOUT_BLOCK, villain.getName(), hero.getName()));
        }
    }
}

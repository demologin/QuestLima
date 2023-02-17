package com.javarush.quest.shubchynskyi.questlima.repository;

import com.javarush.quest.shubchynskyi.questlima.entity.game.Quest;
import com.javarush.quest.shubchynskyi.questlima.repository.abstract_repo.BaseRepository;

import java.util.stream.Stream;

/**
 * repository for Quests, must be changed in future
 */
public class QuestRepository extends BaseRepository<Quest> {

    @Override
    public Stream<Quest> find(Quest pattern) {
        return map.values()
                .stream()
                .filter(quest -> nullOrEquals(pattern.getId(), quest.getId()))
                .filter(quest -> nullOrEquals(pattern.getDescription(), quest.getDescription()))
                .filter(quest -> nullOrEquals(pattern.getName(), quest.getName()))
                .filter(quest -> nullOrEquals(pattern.getAuthorId(), quest.getAuthorId()))
                .filter(quest -> nullOrEquals(pattern.getStartQuestionId(), quest.getStartQuestionId()));
    }

}

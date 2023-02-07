package com.javarush.quest.torkel.repository;

import com.javarush.quest.torkel.entity.Quest;

import java.util.stream.Stream;

public class QuestRepository extends BaseRepository<Quest> {

    @Override
    public Stream<Quest> find(Quest pattern) {
        return map.values()
                .stream()
                .filter(u -> nullOrEquals(pattern.getId(), u.getId()))
                .filter(u -> nullOrEquals(pattern.getText(), u.getText()))
                .filter(u -> nullOrEquals(pattern.getName(), u.getName()))
                .filter(u -> nullOrEquals(pattern.getAuthorId(), u.getAuthorId()))
                .filter(u -> nullOrEquals(pattern.getStartQuestionId(), u.getStartQuestionId()));
    }

}

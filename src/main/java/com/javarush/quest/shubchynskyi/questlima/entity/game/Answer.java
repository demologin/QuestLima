package com.javarush.quest.shubchynskyi.questlima.entity.game;

import com.javarush.quest.shubchynskyi.questlima.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer implements AbstractEntity {

    private Long id;
    private Long questionId;
    private String text;
    private Long nextQuestionId;

}

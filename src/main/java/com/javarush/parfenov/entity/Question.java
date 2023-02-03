package com.javarush.parfenov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    private Long nodeId;
    private String shortName;
    private String text;
    private Long questId;
    private QuestionType type;
}

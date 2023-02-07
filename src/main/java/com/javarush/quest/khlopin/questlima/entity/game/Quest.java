package com.javarush.quest.khlopin.questlima.entity.game;

import com.javarush.quest.khlopin.questlima.entity.user.User;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quest extends GameEntity {

    private Long id;
    private User author;
    private String info;
    private List<Question> questionList;

    private int countOfStages;

    public String toString() {
        return "Quest id=" + this.getId() + ", author=" + this.getAuthor() + ", info=" + this.getInfo();
    }


}

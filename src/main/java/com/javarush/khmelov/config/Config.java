package com.javarush.khmelov.config;

import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.QuestService;
import com.javarush.khmelov.service.UserService;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Config {

    public static void fillEmptyRepository() {
        UserService userService = Winter.getBean(UserService.class);
        if (userService.get(1L).isEmpty()) {
            User admin = new User(-1L, "admin", "qwerty", Role.ADMIN);
            userService.create(admin);
            userService.create(new User(-1L, "user", "user", Role.USER));
            userService.create(new User(-1L, "guest", "guest", Role.GUEST));

            QuestService questService = Winter.getBean(QuestService.class);
            Long adminId = admin.getId();

            questService.create(
                    "Играем в неопознанный летающий объект (обязательный квест)",
                    """
                            1: Ты потерял память. Принять вызов НЛО?
                            2<  Принять вызов
                            91< Отклонить вызов
                                                    
                            2: Ты принял вызов. Подняться на мостик к капитану?
                            92< Отказаться подниматься на мостик
                            3< Подняться на мостик
                                                    
                            3: Ты поднялся на мостик. Ты кто?
                            93< Солгать о себе
                            99< Рассказать правду
                                                    
                            91- Ты отклонил вызов. Поражение.
                            92- Ты не пошел на переговоры. Поражение.
                            93- Твою ложь разоблачили. Поражение.

                            99+ Вы выиграли
                            """,
                    adminId
            );


            questService.create(
                    "Сказочная сказка о приключениях богатыря-разработчика!",
                    """
                            1: Ты перед волшебным камнем, на нем есть надписи.<br>Что выберешь?
                            2< налево пойдешь - в сказку попадешь
                            3< направо пойдешь - свою смерть найдешь
                            4< прямо пойдешь - в разработчики пойдешь
                                         
                            2: Ты видишь закрытый сундук, ну-с... <br>И что будем делать?
                            1< Кто ж его знает, что там, вернусь-ка я обратно...
                            7< Я ничего не боюсь, я его открою...
                                               
                            3: Ты видишь какую-то пещеру, что будешь делать?
                            1< Та ну её нафиг. Я боюсь. Вернусь-ка я за подмогой.
                            8< Держите меня семеро! Я  достаю меч и быстро захожу в пещеру...
                                               
                            4: Ты видишь Кикимору Болотную, которая что-то увлеченно печатает на ноутбуке.
                            1< Кикимора? Ноутбук? Какие-то опасные грибы я съел... Бегом отсюда...
                            5< Скажу громко "Эй ты, а ну отошла от ноута, ща я тебе покажу класс..."
                            6< Похоже без ноута никак разработчиком не стать. Бью Кикимору дубиной по башке, и теперь у меня большое будущее.
                                             
                            5: Кикимора прыгнула в болото, ноут утонул вместе с ней, но зато справа я вижу какой-то тоннель
                            3< Ок, пойду-ка я туда, делать-то нечего.
                                                                  
                            6: Настоящий поступок, настоящего программиста. <br>Теперь осталось только Java доучить, и все, дело в шляпе.
                            9< Стоп, мне же еще нужна литература... Поищу-ка я ее, вон и тропинка видна какая-то с табличкой "в библиотеку"
                            10< Да, точно. Все, я быстро-быстро иду все повторять, учить, и кодить-кодить-кодить. Игры - это зло.
                                               
                            7: Ого. Вот она, большая кучу золота.
                            11< Ура. Набиваем карманы....
                            2< Что-то тут не то, закрою-ка я этот сундук..
                                                    
                            8: Ты заходишь в пещеру, а там Кащей, с Бабой Ягой, и Змеем Горынычем смотрят ролики на Youtube. <br>Что будешь делать?
                            12< Ясно что! В Бой!!!
                                                    
                            9: Какая-то странная дорога, она ведет назад или мне это кажется?
                            1< Делать нечего, иду дальше....
                                                    
                            10+ Ура! Это победа! <br>Но мне пора действительно кодить, хватит уже ерундой заниматься.
                            11- И тут прибежали злые печенеги и убили тебя. <br>Жадность до добра не доведет. <br>Это поражение!
                            12- Тут и сказочке конец. <br>Ужасная смерть в неравном бою. <br>Это поражение!
                            """,
                    adminId
            );

            questService.create(
                    "Проверим твои знания арифметики",
                    """
                            1: Знаешь арифметику?
                            2< Да, конечно
                            99< А что это такое?
    
                            2: Сколько будет дважды два?
                            99< Один
                            99< Два
                            99< Три
                            100< Четыре
    
                            99- Эх... это проигрыш. Надо мне было лучше учиться в школе.
    
                            100+ Ура, победа в сложнейшем квесте!!!
                            """,
                    adminId
            );
        }
    }

    public static final Path WEB_INF = Paths.get(URI.create(
                    Objects.requireNonNull(
                            Config.class.getResource("/")
                    ).toString()))
            .getParent();
}

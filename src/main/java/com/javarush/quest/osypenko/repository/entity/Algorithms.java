package com.javarush.quest.osypenko.repository.entity;

import com.javarush.quest.osypenko.repository.DB;
import com.javarush.quest.osypenko.repository.Training;

import java.util.HashMap;

public class Algorithms implements Training {

    private final HashMap<Long, DB> mapCore1 = new HashMap<>();

    private static final Long CONSTANT_ID = 4000L;

    public Algorithms() {
        mapCore1.put(4000L, new DB(1L, "Что такое ООП?", "ООП - методология программирования, основанная на представлении программы в виде совокупности объектов, каждый из которых является экземпляром определенного класса, а классы образуют иерархию наследования. " +
                "Согласно парадигмы ООП программа состоит из объектов, обменивающихся сообщениями. Объекты могут обладать состоянием, единственный способ изменить состояние объекта - передать ему сообщение,  в ответ на которое, объект может изменить собственное состояние. " +
                "Класс — это описание еще не созданного объекта, как бы общий шаблон, состоящий из полей, методов и конструктора, а объект – экземпляр класса, созданный на основе этого описания."));
    }

    @Override
    public Long getConstantID() {
        return CONSTANT_ID;
    }

    @Override
    public HashMap<Long, DB> getMap() {
        return mapCore1;
    }

}

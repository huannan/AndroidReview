package com.nan.androidreview.designpattern.memento;

public class Client {

    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        originator.setState("状态1");
        printState(originator);

        System.out.println("创建一个备忘录");
        caretaker.setMemento(originator.createMemento());

        System.out.println("修改状态");
        originator.setState("状态2");
        printState(originator);

        System.out.println("恢复一个备忘录");
        originator.restoreMemento(caretaker.getMemento());
        printState(originator);
    }

    public static void printState(Originator originator) {
        System.out.println("当前的状态：" + originator.getState());
    }
}

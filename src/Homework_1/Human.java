package Homework_1;
/*
Класс Человека со своими способностями к бегу и прыжкам
 */

public class Human extends Member{
    private String name;
    public Human(String name){
        this.name=name;
    }


    @Override
    public int run() {
    return (int)(Math.random()*10+50);
    }

    @Override
    public int jump() {
    return (int)(Math.random()*3+1);
    }



    @Override
    public String toString() {
        return "Human "+name;
    }
}

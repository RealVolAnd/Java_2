package Homework_1;

/*
Класс Котов со своими способностями к бегу и прыжкам
 */
public class Cat extends Member {

    private String name;

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public int run() {
        return (int) (Math.random() * 10 + 40);
    }


    @Override
    public int jump() {
        return (int) (Math.random() * 3 + 1);
    }


    @Override
    public String toString() {
        return "Cat " + name;
    }
}

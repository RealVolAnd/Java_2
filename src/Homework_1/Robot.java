package Homework_1;

/*
Класс Роботов со своими способностями к бегу и прыжкам
 */
public class Robot extends Member {

    private String name;

    public Robot(String name) {
        this.name = name;
    }

    @Override
    public int run() {
        return (int) (Math.random() * 10 + 50);
    }

    @Override
    public int jump() {
        return (int) (Math.random() * 3 + 1);
    }

    @Override
    public String toString() {
        return "Robot " + name;
    }
}

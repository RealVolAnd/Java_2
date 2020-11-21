package Homework_1;

/*
Класс полосы препятствий.
 */
import java.util.ArrayList;

public class Course {
    private int course[];

    public Course(int stagesCount) {
        this.course = new int[stagesCount];
        for (int i = 0; i < this.course.length; i++) {
            if (i % 2 == 0) {
                course[i] = (int) (Math.random() * 30 + 30);
            } else {
                course[i] = (int) (Math.random() * 2 + 1);
            }
        }
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < this.course.length; i++) {
            if (i % 2 == 0) {
                result += "run(" + this.course[i] + "m) ";
            } else {
                result += "jump(" + this.course[i] + "m) ";
            }
        }
        return result;
    }

    public ArrayList<Member> doIt(Team team) {
        int j = 0, diff = 0;
        ArrayList<Member> clon = new ArrayList<>(); //Пришлось создавать клоны т.к. нужно удалять записи.
        ArrayList<Member> result = new ArrayList<>();
        clon.addAll(team.getTeam());

        for (int i = 0; i < this.course.length; i++) {
            if (i % 2 == 0) {
                //run
                j = 0;
                for (Member m : clon) {
                    diff = m.run();
                    if (diff < this.course[i]) {
                        result.add(clon.get(j));
                        //clon.remove(j);
                        Console.printText(clon.get(j).toString() + " dropped out at stage " + (i + 1) + "  (Running " + diff + "m from " + this.course[i] + "m )");
                    }
                    j++;
                }
                clon.removeAll(result);
            } else {
                //jump
                j = 0;
                for (Member m : clon) {
                    diff = m.jump();
                    if (diff < this.course[i]) {
                        result.add(clon.get(j));
                        // clon.remove(j);
                        Console.printText(clon.get(j).toString() + " dropped out at stage " + (i + 1) + " (Jumping " + diff + "m from " + this.course[i] + "m )");
                    }
                    j++;
                }
                clon.removeAll(result);
            }
            if (clon.size() == 0) break;
        }
        if (clon.size() > 0)
            result.addAll(clon);// Эта строка не действует. Она выводит всех в порядке выбывания. Но пока это не нужно.
        return clon;

    }
}

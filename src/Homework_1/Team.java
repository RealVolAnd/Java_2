package Homework_1;

/*
Класс команды. Сначало думал от него наследоваться, но в процессе выходило как-то криво, и логически и физически.
Это лучший вариант.
 */
import java.util.ArrayList;

public class Team {
    private String teamName;
    private ArrayList<Member> team = new ArrayList<>();
    private ArrayList<Member> lastResult = new ArrayList<>();

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public void addMember(Member m) {
        team.add(m);
    }

    public void setLastResult(ArrayList<Member> lastResult) {
        this.lastResult.addAll(lastResult);
    }

    public ArrayList<Member> getTeam() {
        return team;
    }


    public String getLastResults() {
        int counter = 0;
        String result = "";

        for (Member s : this.lastResult) {
            counter++;
            result += counter + ". " + s.toString() + "\n";

        }
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        result += "Team name: \"" + teamName + "\"\n\n" + Console.BOLD + "MEMBERS:\n" + Console.RESET;
        for (Object o : team) {
            result += o.toString() + "\n";
        }
        return result;
    }
}

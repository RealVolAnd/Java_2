package Homework_1;

public class Main {
    public static Team team;

    public static void main(String[] args) {
/*
Собираем  команду
 */
        team = new Team("Team of Four");
        team.addMember(new Cat("Murzik"));
        team.addMember(new Human("Han Solo"));
        team.addMember(new Robot("C3PO"));
        team.addMember(new Human(" Luke Skywalker"));
/*
Выводим название и состав команды
 */
        Console.printHeader("TEAM INFO: ");
        Console.printText(team.toString());
/*
 Генерация случайной полосы препятствий в формате: бег-прыжки-бег-прыжки.
 Количество этапов задается в конструкторе трассы.
 */
        Course c = new Course(4);

        Console.printHeader("COURSE INFO: ");
        System.out.println(c.toString());
/*
Если кто-то сошел с дистанции - выводим на каком этапе и почему
 */
        Console.printHeader("CHAMPIONSHIP OUTSIDERS:");
        team.setLastResult(c.doIt(team));
/*
Остаются только те, кто дошел до конца
 */
        Console.printHeader("CHAMPIONSHIP TOTAL: ");
        Console.printText(team.getLastResults());

    }
}

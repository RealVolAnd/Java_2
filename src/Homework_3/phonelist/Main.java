package Homework_3.phonelist;

public class Main {
    public static void main(String[] args) {
        PhoneList phoneList = new PhoneList();

        phoneList.add("Lennon", "111-11-11");
        phoneList.add("McCartney", "222-22-22");
        phoneList.add("Lennon", "333-33-33");
        phoneList.add("Starr", "444-44-44");
        phoneList.add("Starr", "555-55-55");
        phoneList.add("McCartney", "666-66-66");
        phoneList.add("Harrison", "777-77-77");
        phoneList.add("Lennon", "888-88-88");
        phoneList.add("Harrison", "999-99-99");

        System.out.println("Lennon:\n" + phoneList.get("lennon"));
    }
}

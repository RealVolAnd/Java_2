package Homework_3.phonelist;

import java.util.ArrayList;
import java.util.HashMap;

public class PhoneListByUser {
    private HashMap<String, ArrayList<String>> phoneList;

    public PhoneListByUser() {
        phoneList = new HashMap<String, ArrayList<String>>();
    }

    public void add(String name, String phoneNo) {
        ArrayList<String> phoneItem = phoneList.get(name);

        if (phoneItem == null) {
            phoneItem = new ArrayList();
            phoneItem.add(phoneNo);
            phoneList.put(name, phoneItem);
        } else {
            phoneItem.add(phoneNo);
            phoneList.put(name, phoneItem);
        }
    }

    public String get(String name) {

        String result = "Has no record for Name: " + name;

        if (phoneList.containsKey(name)) {
            ArrayList<String> phoneItem = phoneList.get(name);
            result = "";
            for (String s : phoneItem) {
                result += s + "\n";
            }
        }
        return result;
    }
}

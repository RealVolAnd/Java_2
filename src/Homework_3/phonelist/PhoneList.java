package Homework_3.phonelist;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PhoneList {
    private HashMap<String,String>phoneList;

    public PhoneList(){
        phoneList=new HashMap<>();
    }

    public void add(String name,String phoneNo){
            phoneList.put(phoneNo,name);
    }

    public String get(String name){
        String result="";
        for (Map.Entry<String, String> entry : phoneList.entrySet()) {
            if(entry.getValue().equalsIgnoreCase(name)){
                result+=entry.getKey()+"\n";
            }
        }

        return result;
    }

}

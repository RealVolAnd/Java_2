package Homework_7.MyChatClient1;

import java.util.ArrayList;

public class MyChatBuffer {
    private ArrayList<String> myChatBuffer;

    MyChatBuffer() {
        myChatBuffer = new ArrayList<>();
    }

    synchronized public void putMsg(String msg) {
        myChatBuffer.add(msg);
    }

    synchronized public String getNextMsg() {
        String result = "";
        if (myChatBuffer.size() > 0) {
            result = myChatBuffer.get(0);
            myChatBuffer.remove(0);
        }
        return result;
    }

    synchronized public boolean hasMsg() {
        boolean result = false;
        if (myChatBuffer.size() > 0) result = true;
        return result;
    }

}

package Homework_8.ChatServerApp;

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
        String result = null;
        if (myChatBuffer.size() > 0) {
            result = myChatBuffer.get(0);
            myChatBuffer.remove(0);
        }
        return result;
    }
}

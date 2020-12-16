package Homework_8.ChatClientApp.ChatNetwork;

import Homework_8.ChatClientApp.PutGetMsg;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ChatClientNetwork implements PutGetMsg {
    ArrayList<String> sendBuffer;
    private Socket clientSocket;
    private String serverIp;
    private int serverPort;
    private DataInputStream input;
    private DataOutputStream output;
    private boolean receiverStop=false;

    public ChatClientNetwork (String serverIp, int serverPort) throws Exception{
        this.serverIp=serverIp;
        this.serverPort=serverPort;
        sendBuffer=new ArrayList<>();
        connectToServer();
        startReceiver();
    }


    private void connectToServer() throws Exception{

            clientSocket = new Socket(this.serverIp, this.serverPort);
            input = new DataInputStream(clientSocket.getInputStream());
            output = new DataOutputStream(clientSocket.getOutputStream());
    }


    private void startReceiver() throws Exception {
        new Thread(() -> {
            try {
                while (true) {
                    if(receiverStop) break;

                   if(input.available()>0) sendBuffer.add(input.readUTF());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }



    @Override
    public void putMsg(String message) {

        try {
            output.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
   synchronized public String getNextMsg() {
        if(sendBuffer.size()>0) {
            String str=sendBuffer.get(0);
            sendBuffer.remove(0);
            return str;
        }
        return null;
    }
    public void close() throws Exception{
        receiverStop=true;
        input.close();
        output.close();
    }
}

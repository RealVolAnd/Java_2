package Homework_8.ChatClientApp.ChatController;

import Homework_8.ChatClientApp.ChatGui.ChatMainWindow;
import Homework_8.ChatClientApp.ChatNetwork.ChatClientNetwork;

public class ChatClientController {
private ChatClientNetwork clientNetwork;
private ChatMainWindow chatConsole;
private String serverIp;
private int serverPort;

    public ChatClientController(String serverIP,int serverPort) {
        this.serverIp=serverIP;
        this.serverPort=serverPort;

        this.chatConsole = new ChatMainWindow();
        startServerToClientMsgHandler();
        startClientToServerMsgHandler();

        connectToServer();
    }

    private void connectToServer(){


        try{
            this.clientNetwork = new ChatClientNetwork(this.serverIp,this.serverPort);

            chatConsole.putMsg("--serverconnectionok-");
        }catch (Exception e){
            chatConsole.putMsg("--serverconnectionerror-");
        }

    }


    private void startClientToServerMsgHandler() {
        new Thread(() -> {
            String str;
            try {
                while (true) {
                    if(clientNetwork!=null) {

                        str = chatConsole.getNextMsg();
                        if (str != null) {
                            if (str.startsWith("--tryconnect-")) {
                                connectToServer();
                            } else {
                                clientNetwork.putMsg(str);
                            }

                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void startServerToClientMsgHandler() {

        new Thread(() -> {
            String str;
            try {
                while (true) {
                    if(clientNetwork!=null) {

                        str = clientNetwork.getNextMsg();
                        if (str != null) {
                            chatConsole.putMsg(str + "\n");
                            if (str.startsWith("--exit-")) {
                                clientNetwork.close();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


}

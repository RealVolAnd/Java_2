package Homework_8.ChatServerApp;



import Homework_8.Tools.Console;
import Homework_8.Tools.UserConnectionException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyChatServer {
    int serverPort;
    protected ServerSocket serverSocket;
    private Socket clientSocket;
    MyChatBuffer rxBuffer;
    MyChatBuffer txBuffer;
    private MyChatUserList userList;


    MyChatServer(int serverPort) {
        this.serverPort = serverPort;
        this.userList = new MyChatUserList();
        this.rxBuffer = new MyChatBuffer();
        this.txBuffer = new MyChatBuffer();
    }


    public void startServer(){

        openServerSocket();
        handleQueue();

        while (true) {
            try {

            Console.printServerInfo("Server waiting for connection on port: " + this.serverPort + ".....");
            clientSocket = this.serverSocket.accept();  // Waiting for clients......
            Console.printServerInfo("Client connected from: " + clientSocket.getInetAddress().toString() + ":" + clientSocket.getPort());
            new MyChatUserConnection(this, clientSocket, userList);
            } catch (Exception e) {
                Console.printServerError("User connection lost");
            }
        }
    }

    private void openServerSocket() {
        Console.printServerInfo("Opening Server socket...");
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open Server port " + this.serverPort, e);
        }
    }

    private void handleQueue() {
        Runnable run1 = () -> {
            String tmpStr;
            while (true) {
            tmpStr = rxBuffer.getNextMsg();
                if (tmpStr != null) {
                    String[] tmpBuf = tmpStr.split(" ");

                    if (tmpBuf[1].equals("--BROADCAST-")) {

                        for (MyChatUser user : userList.getActiveUsersList()) {
                                try {
                                    String msgBody = tmpStr.substring(tmpStr.lastIndexOf("--BROADCAST-") + 13, tmpStr.length());
                                    user.getConnection().sendMsgToClient(tmpBuf[0] + " (BROADCAST) >>" + msgBody);
                                   Console.printlnText("Broadcast message:" + tmpBuf[0] + " " + msgBody);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                        }
                    } else {
                        MyChatUser dstUser = userList.findUserByNickName(tmpBuf[1]);
                        MyChatUser srcUser = userList.findUserByNickName(tmpBuf[0]);
                        if (dstUser != null && dstUser.isAutorized()) {
                            try {
                                dstUser.getConnection().sendMsgToClient(tmpBuf[0] + " (PRIVATE) >> " + tmpStr.substring(tmpStr.lastIndexOf(tmpBuf[1]) + tmpBuf[1].length() + 1, tmpStr.length()));
                                Console.printlnText("Private message:" + tmpStr);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {

                            try {
                                srcUser.getConnection().sendMsgToClient("User " + tmpBuf[1] + " is not on-line now. So that your message is dropped out");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        };
        Thread thr1 = new Thread(run1);
        thr1.start();
    }
}

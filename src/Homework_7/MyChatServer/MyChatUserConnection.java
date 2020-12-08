package Homework_7.MyChatServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class MyChatUserConnection {

    private DataInputStream input;
    private DataOutputStream output;
    private MyChatUser user;
    private MyChatServer server;
    private MyChatUserList userList;
    private Socket userSocket;


    MyChatUserConnection(MyChatServer server, Socket userSocket, MyChatUserList userList) {
        try {
            this.server = server;
            this.userList = userList;
            this.userSocket = userSocket;
            input = new DataInputStream(this.userSocket.getInputStream());
            output = new DataOutputStream(this.userSocket.getOutputStream());

            newConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void newConnection() throws Exception {
        String tempNickname = "";
        String tempPassword = "";
        MyChatUser tempUser = null;

        for (int i = 0; i < 3; i++) {
            sendMsgToClient("Enter nickname:");
            tempNickname = input.readUTF();
            sendMsgToClient("Enter password:");
            tempPassword = input.readUTF();

            tempUser = userList.checkUserLogin(tempNickname, tempPassword);

            if (tempUser != null) {
                tempUser.bindConnection(this);
                this.user = tempUser;
                sendMsgToClient("You has being authorized in MyChat.\n***** HELP *************************************\n" +
                        "For send private message use: -pm nickname message." +
                        "\nAll other messages will be sent in Broadcast mode\n" +
                        "For view list of active users type: -list\n" +
                        "For get this help type: -help\n" +
                        "For remember your name type: -name\n" +
                        "For get sever date and time: -time\n" +
                        "For exit Chat type: -exit\n" +
                        "**********************************************");
                break;
            }
            sendMsgToClient("Incorrect nickname or password. Try again.");
        }


        if (tempUser == null) {
            sendMsgToClient("It's enought. Session closed.");
            return;
        } else {
            final String tmpName = tempUser.getName();
            final MyChatUser tmpUsr = tempUser;

            Runnable run1 = () -> {
                while (true) {
                    try {
                        String tmpStr = "";
                        if (input.available() > 0) {
                            tmpStr = input.readUTF();

                            if (tmpStr.startsWith("-pm")) {
                                tmpStr = tmpName + tmpStr.substring(3, tmpStr.length());
                                server.rxBuffer.putMsg(tmpStr);
                            } else if (tmpStr.startsWith("-list")) {
                                sendMsgToClient("\nActive users:" + userList.getActiveUsersList());
                            } else if (tmpStr.startsWith("-help")) {
                                sendMsgToClient("***** HELP *************************************\n" +
                                        "For send private message use: -pm nickname message." +
                                        "\nAll other messages will be sent in Broadcast mode\n" +
                                        "For view list of active users type: -list\n" +
                                        "For get this help type: -help\n" +
                                        "For remember your name type: -name\n" +
                                        "For get sever date and time: -time\n" +
                                        "For exit Chat type: -exit\n" +
                                        "**********************************************");
                            } else if (tmpStr.startsWith("-name")) {
                                sendMsgToClient("Your Nickname: "+tmpName);
                            }else if(tmpStr.startsWith("-exit")) {
                                tmpUsr.setAuthorized(false);
                                sendMsgToClient("Session closed. Bye!");
                                return;
                            }else if(tmpStr.startsWith("-time")){
                                sendMsgToClient("Server Date and time: "+new Date().toString());
                            } else {
                                tmpStr = tmpName + " --++-+~ " + tmpStr;
                                server.rxBuffer.putMsg(tmpStr);
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            Thread thr1 = new Thread(run1);
            thr1.start();
        }
    }

    public void sendMsgToClient(String msg) throws IOException {
        output.writeUTF(msg);
    }
}

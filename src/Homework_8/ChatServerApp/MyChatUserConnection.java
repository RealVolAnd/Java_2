package Homework_8.ChatServerApp;
import Homework_8.Tools.Console;
import Homework_8.Tools.UserConnectionException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyChatUserConnection {

    private DataInputStream input;
    private DataOutputStream output;
    private MyChatUser user;
    private MyChatServer server;
    private MyChatUserList userList;
    private Socket userSocket;


    MyChatUserConnection(MyChatServer server, Socket userSocket, MyChatUserList userList) throws Exception {

            this.server = server;
            this.userList = userList;
            this.userSocket = userSocket;
            input = new DataInputStream(this.userSocket.getInputStream());
            output = new DataOutputStream(this.userSocket.getOutputStream());

            newConnection();
    }

    private void closeUserConnection() throws IOException{
            input.close();
            output.close();
    }

    private void newConnection()  {
        String tempNickname = "";
        String tempPassword = "";
        MyChatUser tempUser = null;
        TimerTask task = new TimerTask() {
            public void run() {
                try{
                    sendMsgToClient("Session closed by timeout.");
                    Console.printServerError("Unauthorized user session closed by timeout");
                    sendMsgToClient("--exit-");
                    closeUserConnection();
                    return;
                }catch(Exception e){
                    new UserConnectionException("Error in timer",e);
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 120000);

        for (int i = 0; i < 3; i++) {

            try {
                Thread.sleep(100);
                sendMsgToClient("Enter nickname:");
                tempNickname = input.readUTF();
                if(!checkForExit(tempNickname)) {
                    sendMsgToClient("Enter password:");
                    tempPassword = input.readUTF();
                    checkForExit(tempPassword);
                }

            }catch(Exception e){
                throw new UserConnectionException("User connection error",e);
            }

            tempUser = userList.checkUserLogin(tempNickname, tempPassword);

            if (tempUser != null) {
                tempUser.bindConnection(this);
                this.user = tempUser;
                sendHelp();
                break;
            }
            sendMsgToClient("Incorrect nickname or password. Try again.");
        }


        if (tempUser == null) {
           timer.cancel();
            sendMsgToClient("Session closed. Login try count exceed limit.");
            sendMsgToClient("--exit-");
            return;
        } else {
            final String tmpName = tempUser.getName();
            final MyChatUser tmpUsr = tempUser;
            timer.cancel();

            Runnable run1 = () -> {
                String tmpStr = "";
                while (true) {
                    try {
                        if (input.available() > 0) {
                            tmpStr = input.readUTF();

                            if (tmpStr.startsWith("-pm")) {
                                tmpStr = tmpName + tmpStr.substring(3, tmpStr.length());
                                server.rxBuffer.putMsg(tmpStr);
                            } else if (tmpStr.startsWith("-list")) {
                                sendMsgToClient("\nActive users:" + userList.getActiveUsersListAsString());
                            } else if (tmpStr.startsWith("-help")) {
                                sendHelp();
                            } else if (tmpStr.startsWith("-name")) {
                                sendMsgToClient("Your are "+tmpName);
                            }else if(tmpStr.startsWith("-exit")) {
                                tmpUsr.setAuthorized(false);
                                sendMsgToClient("You unauthorized. Session closed. Bye!");
                                sendMsgToClient("--exit-");
                                return;
                            }else if(tmpStr.startsWith("-time")){
                                sendMsgToClient("Server Date and time:\n "+new Date().toString());
                            } else {
                                tmpStr = tmpName + " --BROADCAST- " + tmpStr;
                                server.rxBuffer.putMsg(tmpStr);
                            }
                        }

                    } catch (Exception e) {
                      throw  new UserConnectionException("Error in connection routine",e);
                    }
                }
            };
            Thread thr1 = new Thread(run1);
            thr1.setName("UserConnection"+thr1.getName());
            thr1.start();
        }
    }

    public void sendMsgToClient(String msg) {
        try {
            output.writeUTF(msg);
        } catch (Exception e) {
           throw new UserConnectionException("Send message error",e);
        }
    }

    private void sendHelp(){
        sendMsgToClient("AUTHORIZATION OK !\n***** HELP *************************************\n" +
                "-pm nickname message   : send private message to nickname\n" +
                "-list   : view list of active users \n" +
                "-help   : get this help type \n" +
                "-name   : get your name \n" +
                "-time   : get sever date and time \n" +
                "-exit   : exit MyChat\n" +
                "**********************************************");
    }

    private boolean checkForExit(String str){
        boolean result=false;
        if(str.startsWith("-exit")) {
            sendMsgToClient("You unauthorized. Session closed. Bye!");
            sendMsgToClient("--exit-");
            return true;
        }
        return result;
    }

}

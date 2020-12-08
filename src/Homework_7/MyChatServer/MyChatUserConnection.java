package Homework_7.MyChatServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MyChatUserConnection {

    private DataInputStream input;
    private DataOutputStream output;
    private MyChatUser user;
    private MyChatServer server;
    private MyChatUserList userList;
    private Socket userSocket;


    MyChatUserConnection(MyChatServer server,Socket userSocket,MyChatUserList userList){
        try{
            this.server = server;
            this.userList=userList;
            this.userSocket=userSocket;
            input = new DataInputStream(this.userSocket.getInputStream());
            output= new DataOutputStream(this.userSocket.getOutputStream());

            newConnection();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void newConnection() throws Exception{
       String tempNickname="";
        String tempPassword="";
        MyChatUser tempUser=null;
        Scanner scan = new Scanner(input);

        for(int i=0;i<3;i++){
            sendMsgToClient("nickname:");
            tempNickname=scan.nextLine();
            sendMsgToClient("password:");
            tempPassword=scan.nextLine();

            tempUser=userList.checkUserLogin(tempNickname,tempPassword);
            if(tempUser!=null){
                tempUser.bindConnection(this);
                this.user=tempUser;
                break;
            }
            sendMsgToClient("Incorrect nickname or password. Try again.");
        }


        if(tempUser==null){
            sendMsgToClient("Incorrect nickname or password. Session closed.");
        } else{

            Runnable run1 = () -> {
                while (true) {
                    try{
                        server.rxBuffer.putMsg(input.readUTF());
                    }catch (Exception e){

                    }
                }
            };
            Thread thr1 = new Thread(run1);
            thr1.start();
        }
    }

    public void sendMsgToClient(String msg) throws IOException{
        output.writeUTF(msg);
    }



}

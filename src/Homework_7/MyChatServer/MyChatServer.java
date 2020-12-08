package Homework_7.MyChatServer;

import Homework_7.Console.Console;

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


    public void startServer() throws IOException {

        openServerSocket();
        handleQueue();

        while(true){

            Console.printServerInfo("Server waiting for connection on port: " + this.serverPort + ".....");
            clientSocket = this.serverSocket.accept();// Waiting for clients......
            Console.printServerInfo("Client connected from: "+clientSocket.getInetAddress().toString()+":"+clientSocket.getPort());
            new MyChatUserConnection(this,clientSocket,userList);
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

    private void handleQueue(){
        Runnable run1 = () -> {
            while (true) {
                String tmpStr=rxBuffer.getNextMsg();
               if( tmpStr!=null){
                   String[] tmpBuf=tmpStr.split(" ");

                   if(tmpBuf[1].equals("--++-+~")){
                       for(MyChatUser user: userList.getList()){
                           if(user.isAutorized()){
                               try{
                               user.getConnection().sendMsgToClient(tmpStr);
                                   Console.printlnText("Broadcast message:"+tmpStr);
                               }catch (Exception e){
                                   e.printStackTrace();
                               }
                           }
                       }



                   } else{
                       MyChatUser dstUser= userList.findUserByNickName(tmpBuf[1]);
                       MyChatUser srcUser= userList.findUserByNickName(tmpBuf[0]);
                       if(dstUser!=null&&dstUser.isAutorized()){
                           try{
                               dstUser.getConnection().sendMsgToClient(tmpStr);
                               Console.printlnText("Private message:"+tmpStr);
                           }catch (Exception e){
                               e.printStackTrace();
                           }
                       } else{


                           try{
                               srcUser.getConnection().sendMsgToClient("User "+dstUser+" is not on-line now. Message undelivered");
                           }catch (Exception e){
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

    private void handleTxQueue(){

    }
}
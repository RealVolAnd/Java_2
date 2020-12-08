package Homework_7.MyChatServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class MyChatUser {

    private String nickname;
    private String password;
    private boolean isAuthorized=false;
    private MyChatUserConnection connection;

        MyChatUser(String nickname,String password){
        this.nickname=nickname;
        this.password=password;
    }

    public String getName(){
            return this.nickname;
    }
    public boolean checkIn(String nickname,String password){
            if(nickname.equals(this.nickname)&&password.equals(this.password)) {
                this.isAuthorized=true;
            }
            return this.isAuthorized;
    }
    public void checkOut(){
            this.isAuthorized=false;
    }
    public boolean isAutorized(){
        return this.isAuthorized;
    }

    public void bindConnection(MyChatUserConnection connection){
            this.connection=connection;
    }
    public MyChatUserConnection getConnection(){
            return this.connection;
    }


}

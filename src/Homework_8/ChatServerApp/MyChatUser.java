package Homework_8.ChatServerApp;


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
    public String getPassword(){
        return this.password;
    }

    public void setAuthorized(boolean b){
        this.isAuthorized=b;
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
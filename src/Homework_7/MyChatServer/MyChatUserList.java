package Homework_7.MyChatServer;

import java.util.HashSet;
import java.util.Set;

public class MyChatUserList {
    private Set<MyChatUser> chatUsers;

    MyChatUserList(){
        chatUsers = new HashSet<>();
    }

    public void add(MyChatUser user){
        chatUsers.add(user);
    }

    public void remove(MyChatUser user){
        chatUsers.remove(user);
    }

    public MyChatUser findUserByNickName(String nickName){
        for(MyChatUser user: chatUsers){
            if (user.getName()==nickName) return user;
        }
        return null;
    }

    public MyChatUser checkUserLogin(String name,String password){
        for(MyChatUser user:chatUsers){
            if(user.checkIn(name,password)) return user;
        }
        return null;
    }

    public Set<MyChatUser> getList(){
        return chatUsers;
    }



}

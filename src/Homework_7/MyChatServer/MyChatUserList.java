package Homework_7.MyChatServer;

import java.util.HashSet;
import java.util.Set;

public class MyChatUserList {
    private Set<MyChatUser> chatUsers;

    MyChatUserList(){
        chatUsers = new HashSet<>();
        fillUserList();
    }


    private void fillUserList(){
        chatUsers.add(new MyChatUser("nickname1","pass1"));
        chatUsers.add(new MyChatUser("nickname2","pass2"));
        chatUsers.add(new MyChatUser("nickname3","pass3"));
        chatUsers.add(new MyChatUser("nickname4","pass4"));
    }

    public void add(MyChatUser user){
        chatUsers.add(user);
    }

    public void remove(MyChatUser user){
        chatUsers.remove(user);
    }

    public MyChatUser findUserByNickName(String nickName){
        for(MyChatUser user: chatUsers){
            if (nickName.equals(user.getName()) ) return user;
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

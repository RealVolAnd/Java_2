package Homework_8.ChatServerApp;

import java.util.HashSet;
import java.util.Set;

public class MyChatUserList {
    private Set<MyChatUser> chatUsers;

    MyChatUserList() {
        chatUsers = new HashSet<>();
        fillUserListFromDb();
    }


    private void fillUserListFromDb() {
        chatUsers.add(new MyChatUser("nick1", "pass1"));
        chatUsers.add(new MyChatUser("nick2", "pass2"));
        chatUsers.add(new MyChatUser("nick3", "pass3"));
        chatUsers.add(new MyChatUser("nick4", "pass4"));
    }

    public void add(MyChatUser user) {
        chatUsers.add(user);
    }

    public MyChatUser findUserByNickName(String nickName) {
        for (MyChatUser user : chatUsers) {
            if (nickName.equals(user.getName())) return user;
        }
        return null;
    }

    public MyChatUser checkUserLogin(String name, String password) {
        for (MyChatUser user : chatUsers) {
            if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                user.setAuthorized(true);
                return user;
            }
        }
        return null;
    }

    public Set<MyChatUser> getUsersList() {
        return chatUsers;
    }

    public String getActiveUsersListAsString() {
        String result = "";

        for (MyChatUser user : chatUsers) {
            if (user.isAutorized()) {
                result += "\n" + user.getName();
            }
        }
        if (result.equals("")) result = "No active users in the Chat";

        return result;
    }

    public Set<MyChatUser> getActiveUsersList() {
        Set<MyChatUser> result = new HashSet<>();

        for (MyChatUser user : chatUsers) {
            if (user.isAutorized()) {
                result.add(user);
            }
        }

        return result;
    }


}

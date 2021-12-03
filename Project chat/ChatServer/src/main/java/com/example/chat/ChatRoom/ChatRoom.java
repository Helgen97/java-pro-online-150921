package com.example.chat.ChatRoom;

import com.example.chat.Users.User;

import java.util.ArrayList;

public class ChatRoom {
    private String roomName;
    private final ArrayList<User> users = new ArrayList<>();

    public ChatRoom(String roomName, User creator){
        this.roomName = roomName;
        users.add(creator);
    }

    public void addUser(User user){
        users.add(user);
    }

    public void deleteUser(User user){
        String login = user.getLogin();
        for (int i = 0; i< users.size(); i++){
            if(users.get(i).getLogin().equals(login)){
                users.remove(i);
                break;
            }
        }
    }

    public boolean findUserByLogin(String login){
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public String getRoomName() {
        return roomName;
    }
}

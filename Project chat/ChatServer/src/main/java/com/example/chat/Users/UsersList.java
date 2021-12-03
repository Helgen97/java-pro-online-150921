package com.example.chat.Users;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class UsersList {
    private static final UsersList usrList = new UsersList();

    private final Gson gson;
    private final ArrayList<User> users = new ArrayList<>();

    private UsersList() {
        gson = new GsonBuilder().create();
    }

    public static UsersList getInstance() {
        return usrList;
    }

    public synchronized boolean add(User user) {
        if (users.contains(user)) {
            return false;
        }
        users.add(user);
        return true;
    }

    public synchronized String toGson() {
        return gson.toJson(users);
    }

    public boolean containLogin(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public boolean truePass(String login, String pass) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                if (user.getPassword().equals(pass))
                    return true;
            }
        }
        return false;
    }

    public User findUserWithLogin(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (User user : users) {
            sb.append(user.toString()).append("\n");
        }
        return sb.toString();
    }
}

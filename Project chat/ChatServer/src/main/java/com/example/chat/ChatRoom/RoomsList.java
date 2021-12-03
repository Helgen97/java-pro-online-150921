package com.example.chat.ChatRoom;

import java.util.ArrayList;

public class RoomsList {
    private static final RoomsList roomsList = new RoomsList();

    private final ArrayList<ChatRoom> rooms = new ArrayList<>();

    private RoomsList(){

    }

    public static RoomsList getInstance(){
        return roomsList;
    }

    public boolean addRoom(ChatRoom newRoom){
        for (ChatRoom room : rooms){
            if(room.getRoomName().equals(newRoom.getRoomName())){
                return false;
            }
        }
        rooms.add(newRoom);
        return true;
    }

    public ChatRoom findRoomByName(String roomName){
        for (ChatRoom room : rooms){
            if(room.getRoomName().equals(roomName)) {
                return room;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (ChatRoom room : rooms){
            stringBuilder.append("Room name: ").append(room.getRoomName()).append("\n");
        }
        return stringBuilder.toString();
    }

}

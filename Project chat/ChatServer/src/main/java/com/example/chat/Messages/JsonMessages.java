package com.example.chat.Messages;

import com.example.chat.ChatRoom.RoomsList;

import java.util.ArrayList;
import java.util.List;

public class JsonMessages {
    private final List<Message> list;
    private final RoomsList roomsList = RoomsList.getInstance();

    public JsonMessages(List<Message> sourceList, int fromIndex, String login) {
        this.list = new ArrayList<>();
        for (int i = fromIndex; i < sourceList.size(); i++) {
            if (sourceList.get(i).getTo() == null && sourceList.get(i).getRoomName() == null) {
                list.add(sourceList.get(i));
                continue;
            }
            if(sourceList.get(i).getTo() != null) {
                if (sourceList.get(i).getTo().equals(login)) {
                    list.add(sourceList.get(i));
                }
            }
            String messageRoomName = sourceList.get(i).getRoomName();
            if(roomsList.findRoomByName(messageRoomName).findUserByLogin(login)){
                list.add(sourceList.get(i));
            }
        }
    }
}

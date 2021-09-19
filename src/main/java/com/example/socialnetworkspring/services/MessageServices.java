package com.example.socialnetworkspring.services;

import com.example.socialnetworkspring.models.Message;
import com.example.socialnetworkspring.models.Users;

import java.util.List;

public interface MessageServices {
    List<Message> getAllMessagesFromUsers(Users user1,Users user2);
    Message saveMessage(Message message);
    List<Message> getAllDialogs(Users user1);
}

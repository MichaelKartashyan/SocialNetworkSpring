package com.example.socialnetworkspring.services.impl;

import com.example.socialnetworkspring.Repositories.MessageRepository;
import com.example.socialnetworkspring.models.Message;
import com.example.socialnetworkspring.models.Users;
import com.example.socialnetworkspring.services.MessageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MessageServicesImpl implements MessageServices {
    @Autowired
    MessageRepository messageRepository;


    @Override
    public List<Message> getAllMessagesFromUsers(Users user1, Users user2) {
        return messageRepository.findAllByUser1AndUser2(user1,user2);
    }

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getAllDialogs(Users user1) {
        return messageRepository.findListDialogs(user1);
    }
}

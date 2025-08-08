package com.MessageStreaming.MessageStreamingApp.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MessageStreaming.MessageStreamingApp.cassandra.MessageEntity;
import com.MessageStreaming.MessageStreamingApp.cassandra.MessageRepository;

@Service
public class CassandraService {

    @Autowired
    private MessageRepository messageRepository;

    public void saveMessage(String message) {
        MessageEntity entity = new MessageEntity();
        entity.setId(UUID.randomUUID());
        entity.setMessage(message);
        messageRepository.save(entity);
    }

}

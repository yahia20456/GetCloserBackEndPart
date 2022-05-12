package com.esprit.pidevbackend.Service;

import com.esprit.pidevbackend.Domain.ChatMessage;
import com.esprit.pidevbackend.Domain.MessageStatus;
import com.esprit.pidevbackend.Repository.ChatMessageRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatMessageService {
    @Autowired
    private ChatMessageRepository repository;
    @Autowired private ChatRoomService chatRoomService;


    public ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setStatus(MessageStatus.RECIEVED);
        repository.save(chatMessage);
        return chatMessage;
    }

    public long countNewMessages(Long senderId, Long recipientId) {
        return repository.countBySenderIdAndRecipientIdAndStatus(
                senderId, recipientId, MessageStatus.RECIEVED);
    }

    public List<ChatMessage> findChatMessages(Long senderId, Long recipientId) {
        var chatId = chatRoomService.getChatId(senderId, recipientId, false);

        var messages =
                chatId.map(cId -> repository.findByChatId(cId)).orElse(new ArrayList<>());



        return messages;
    }

    public ChatMessage findById(Long id) {
        return repository
                .findById(id)
                .map(chatMessage -> {
                    chatMessage.setStatus(MessageStatus.DELIVERED);
                    return repository.save(chatMessage);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("can't find message (" + id + ")"));
    }


}

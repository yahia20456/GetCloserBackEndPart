package com.esprit.pidevbackend.Web;

import com.esprit.pidevbackend.Domain.ChatMessage;
import com.esprit.pidevbackend.Service.ChatMessageService;
import com.esprit.pidevbackend.Service.ChatRoomService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class ChatController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired private ChatMessageService chatMessageService;
    @Autowired private ChatRoomService chatRoomService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        /*log.info("sender id"+chatMessage.getSenderId().toString());
        log.info("reciever id"+chatMessage.getRecipientId().toString());*/
        log.info("chat id "+chatMessage.getChatId());
        var chatId = chatRoomService
                .getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId.get());

        ChatMessage saved = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(chatMessage.getRecipientId().toString()
                ,"/queue/messages",saved
        );


    }

    @GetMapping("/messages/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewMessages(
            @PathVariable Long senderId,
            @PathVariable Long recipientId) {

        return ResponseEntity
                .ok(chatMessageService.countNewMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessages ( @PathVariable Long senderId,
                                                @PathVariable Long recipientId) {
        return ResponseEntity
                .ok(chatMessageService.findChatMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage ( @PathVariable Long id) {
        return ResponseEntity
                .ok(chatMessageService.findById(id));
    }
}


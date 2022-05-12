package com.esprit.pidevbackend.Service;

import com.esprit.pidevbackend.Domain.ChatRoom;
import com.esprit.pidevbackend.Repository.ChatRoomRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ChatRoomService {
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public Optional<String> getChatId(
            Long senderId, Long recipientId, boolean createIfNotExist) {
        log.info("sender id" + senderId.toString());
        log.info("reciever id" + recipientId.toString());

        return chatRoomRepository
                .getChatRoomBySenderIdAndRecipientId(senderId, recipientId)
                .map(chatRoom -> {
                    log.info("chat found");
                    log.info(chatRoom.getChatId());
                    return chatRoom.getChatId();
                })
                .or(() -> {
                    if(!createIfNotExist) {
                        log.info("noot found creating new ");
                        return  Optional.empty();
                    }
                    var chatId =
                            String.format("%s_%s", senderId, recipientId);

                    ChatRoom senderRecipient = new ChatRoom();
                    senderRecipient.setRecipientId(recipientId);
                    senderRecipient.setChatId(chatId);
                    senderRecipient.setSenderId(senderId);

                    ChatRoom recipientSender = new ChatRoom();
                    recipientSender.setRecipientId(senderId);
                    recipientSender.setChatId(chatId);
                    recipientSender.setSenderId(recipientId);

                    chatRoomRepository.save(senderRecipient);
                    chatRoomRepository.save(recipientSender);

                    return Optional.of(chatId);
                });
    }




}

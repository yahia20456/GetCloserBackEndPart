package com.esprit.pidevbackend.Repository;

import com.esprit.pidevbackend.Domain.ChatRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends CrudRepository<ChatRoom,Long> {
    Optional<ChatRoom> getChatRoomBySenderIdAndRecipientId(Long senderId, Long recipientId);
}

package com.estuscode.opus.chat_service.repository;

import com.estuscode.opus.chat_service.entity.ChatRoom;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface ChatRoomRepository extends CrudRepository<ChatRoom, Long> {
    Optional<ChatRoom> findById(UUID id);
}

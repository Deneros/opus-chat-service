package com.estuscode.opus.chat_service.controller;

import java.util.UUID;

import com.estuscode.opus.chat_service.entity.ChatRoom;
import com.estuscode.opus.chat_service.repository.ChatRoomRepository;
import com.estuscode.opus.chat_service.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.estuscode.opus.chat_service.entity.Message;

@Controller
public class ChatController {
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    private ChatRoomRepository chatRoomRepository;
    private MessageRepository messageRepository;

    @MessageMapping("/chat.sendMessage/{chatId}")
    @SendTo("/topic/chat/{chatId}")
    public Message sendMessage(@DestinationVariable String chatId, Message message) {
//        ChatRoom chatRoom = chatRoomRepository.findByUUID(UUID.fromString(chatId))
//                .orElseThrow(() -> new RuntimeException("Chat room not found"));
//        message.setChatRoom(chatRoom);

//        return messageRepository.save(message);
        return message;
    }

    @MessageMapping("/chat.createRoom")
    public UUID createRoom() {
        UUID id = UUID.randomUUID();
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setId(id);
        chatRoomRepository.save(chatRoom);
        return id;
    }

    public void sendPrivateMessage(String username, Message message) {
        messagingTemplate.convertAndSendToUser(username, "/queue/private", message);
    }
}

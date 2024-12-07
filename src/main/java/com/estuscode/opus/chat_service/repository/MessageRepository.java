package com.estuscode.opus.chat_service.repository;

import com.estuscode.opus.chat_service.entity.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message,Long> {
}

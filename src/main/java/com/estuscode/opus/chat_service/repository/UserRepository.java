package com.estuscode.opus.chat_service.repository;

import com.estuscode.opus.chat_service.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}

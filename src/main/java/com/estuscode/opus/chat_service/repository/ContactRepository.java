package com.estuscode.opus.chat_service.repository;

import com.estuscode.opus.chat_service.entity.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {
}

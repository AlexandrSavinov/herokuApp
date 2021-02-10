package com.example.znanya.repos;

import com.example.znanya.domain.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {

    List<Message> findByTag(String tag);
    List<Message> findByAuthor(String name);

    List<Message> deleteMessageByAuthor(String name);

}

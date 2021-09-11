package com.digital.repository;

import com.digital.model.User;
import com.digital.model.tchat.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {

    List<Message> findAllByState(boolean state);
    //List<Message> findByUserOrderByIdAsc(User user);
    List<Message> findByExpediteurOrderByIdAsc(User user);
    List<Message> findByExpediteurOrDestinataireAndExpediteurOrderByIdAsc(User exp, User dest, User dest2);
}

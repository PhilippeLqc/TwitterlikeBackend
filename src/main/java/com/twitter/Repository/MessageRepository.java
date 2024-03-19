package com.twitter.Repository;

import com.twitter.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
    @Query("SELECT m FROM Message m WHERE m.sender.username = :username OR m.receiver.username = :username")
    List<Message> findAllBySender_UsernameOrReceiver_Username(String username);

    @Query("SELECT m FROM Message m WHERE m.sender.username = :sender AND m.receiver.username = :receiver")
    List<Message> findAllBySender_UsernameAndReceiver_Username(String sender, String receiver);
}

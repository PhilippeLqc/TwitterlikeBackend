package com.twitter.Controller;

import com.twitter.Dto.MessageDto;
import com.twitter.Model.Message;
import com.twitter.Service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    @PostMapping()
    //send message
    public MessageDto saveMessage(@RequestBody MessageDto messageDto){
        return messageService.saveMessage(messageDto);
    }

    @GetMapping("/user/{username}")
    //get all messages by username
    public List<MessageDto> getAllMessageByUser(@PathVariable String username){
        return messageService.getAllMessageByUser(username);
    }

    @GetMapping("/message/{id}")
    //get message by id
    public MessageDto getMessageById(@PathVariable Long id){
        return messageService.getMessageById(id);
    }

    @PutMapping("/message/{id}")
    //update message by id
    public MessageDto updateMessageById(@PathVariable Long id, @RequestBody Message message){
        return messageService.updateMessageById(id, message);
    }

    @DeleteMapping("/message/{id}")
    //delete message by id
    public void deleteMessageById(@PathVariable Long id){
        messageService.deleteMessageById(id);
    }

    @GetMapping("/message/{sender}/{receiver}")
    //get all messages between sender and receiver
    public List<MessageDto> getAllMessageBySenderAndReceiver(@PathVariable String sender, @PathVariable String receiver){
        return messageService.getAllMessageBySenderAndReceiver(sender, receiver);
    }
}

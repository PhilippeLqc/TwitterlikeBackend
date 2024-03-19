package com.twitter.Service;

import com.twitter.Dto.MessageDto;
import com.twitter.Model.Message;
import com.twitter.Model.User;
import com.twitter.Repository.MessageRepository;
import com.twitter.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Builder
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageDto convertToDto(Message message){
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setMessage(message.getMessage());
        messageDto.setSender(message.getSender().getId());
        messageDto.setReceiver(message.getReceiver().getId());
        return messageDto;
    }
    public Message convertToEntity(MessageDto messageDto){
        User sender = userRepository.findById(messageDto.getSender()).orElse(null);
        User receiver = userRepository.findById(messageDto.getReceiver()).orElse(null);
        return Message.builder()
                .id(messageDto.getId())
                .message(messageDto.getMessage())
                .sender(sender)
                .receiver(receiver)
                .build();
    }
    // post a message to the database using DTO
    public MessageDto saveMessage(MessageDto messageDto){
        Message message = convertToEntity(messageDto);
        return convertToDto(message);
    }

    // get all message send and receive by a user using DTO
    public List<MessageDto> getAllMessageByUser(String username){
        List<Message> messages = messageRepository.findAllBySender_UsernameOrReceiver_Username(username);
        return messages.stream().map(this::convertToDto).toList();
    }

    // get a message by id using DTO
    public MessageDto getMessageById(Long id){
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Message not found"));
        return convertToDto(message);
    }

    // update a message by id using DTO
    public MessageDto updateMessageById(Long id, Message message){
        Message existingMessage = messageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Message not found"));
        existingMessage.setMessage(message.getMessage());
        messageRepository.save(existingMessage);
        return convertToDto(messageRepository.save(existingMessage));
    }

    // delete a message by id
    public void deleteMessageById(Long id){
        messageRepository.deleteById(id);
    }

    //get all message between sender and receiver
    public List<MessageDto> getAllMessageBySenderAndReceiver(String sender, String receiver){
        List<Message> messages = messageRepository.findAllBySender_UsernameAndReceiver_Username(sender, receiver);
        return messages.stream().map(this::convertToDto).toList();
    }
}

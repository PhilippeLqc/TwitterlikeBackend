package com.twitter.Dto;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    Long id;
    String message;
    Long sender;
    Long receiver;

}

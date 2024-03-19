package com.twitter.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String username;
    private String password;
    @OneToMany(mappedBy = "sender")
    private List<Message> messages;
    @OneToMany(mappedBy = "receiver")
    private List<Message> receivedMessages;

    public Boolean isEqual(User user) {
        return this.username.equals(user.getUsername()) && this.password.equals(user.getPassword());
    }
}

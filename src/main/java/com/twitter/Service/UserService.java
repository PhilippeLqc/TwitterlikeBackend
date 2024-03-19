package com.twitter.Service;

import com.twitter.Model.User;
import com.twitter.Repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // create user
    public User createUser(User user) {
        User userAlreadyExists;
        if (isUserExists(user.getUsername())) {
            userAlreadyExists = userRepository.findByUsername(user.getUsername());
        } else {
            userAlreadyExists = userRepository.save(user);
        }
        return userAlreadyExists;
    }
    public Boolean isUserExists(String username) {
        return this.getAllUsers()
                .stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }

    // get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // get user by username
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // update user
    public void updateUser(User user) {
        User userToUpdate = getUserByUsername(user.getUsername());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        userRepository.update(userToUpdate.getUsername(), userToUpdate.getPassword(), userToUpdate.getId());
    }

    // delete user
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    // login user by username and password
    public User loginUser(User user) {
        User userConnect;
        if (isUserExistsAndPassword(user)) {
            userConnect = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        } else {
            throw new RuntimeException("User not found");
        }
        return userConnect;
    }
    public Boolean isUserExistsAndPassword(User user) {
        return this.getAllUsers()
                .stream()
                .anyMatch(userExist -> userExist.isEqual(user));
    }

}

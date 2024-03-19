package com.twitter.Controller;

import com.twitter.Model.User;
import com.twitter.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    //create user
    @PostMapping()
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    //login user
    @PostMapping("/login")
    public User loginUser(@RequestBody User user) {
        return userService.loginUser(user);
    }

    //update user
    @PutMapping("/{username}")
    public void updateUser(@RequestBody User user, @PathVariable String username) {

        userService.updateUser(user);
    }

    //delete user
    @PostMapping("/{username}")
    public void deleteUser(@RequestBody User user, @PathVariable String username) {
        userService.deleteUser(user);
    }

    //get all users
    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //get user by username
    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
}

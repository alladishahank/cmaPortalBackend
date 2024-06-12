package com.accelq.csma_portal.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "{userId}")
    public Optional<User> getUserById(@PathVariable("userId") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public void addNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long id) {
        userService.deleteUser(id);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") Long id, @RequestBody User updatedUser) {
        userService.updateUser(id, updatedUser);
    }
}

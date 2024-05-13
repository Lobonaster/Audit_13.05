package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;
    static class UserRequest {
        private Integer id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUser(id);
    }

    @PostMapping
    public User getUserByJson(@RequestBody(required = false) UserRequest userRequest) {
        if (userRequest != null && userRequest.getId() != null) {
            return userService.getUser(userRequest.getId());
        } else {
            // Если userRequest пуст
            return null;
        }
    }
}

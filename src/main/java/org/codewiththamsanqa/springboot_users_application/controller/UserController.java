package org.codewiththamsanqa.springboot_users_application.controller;

import org.codewiththamsanqa.springboot_users_application.dto.UserRequest;
import org.codewiththamsanqa.springboot_users_application.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.codewiththamsanqa.springboot_users_application.service.UserService;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

   private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request){
        UserResponse response = userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> allUsers(){
        List<UserResponse> users = userService.listUsers();

        return ResponseEntity.status(HttpStatus.FOUND).body(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest request){
        UserResponse response = userService.updateUser(id,request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        String response = userService.deleteUser(id);

        return ResponseEntity.ok().body(response);
    }
}

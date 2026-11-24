package org.codewiththamsanqa.springboot_users_application.service;

import org.codewiththamsanqa.springboot_users_application.dto.UserRequest;
import org.codewiththamsanqa.springboot_users_application.dto.UserResponse;
import org.codewiththamsanqa.springboot_users_application.entity.User;
import org.codewiththamsanqa.springboot_users_application.exceptions.ResourceNotFoundException;
import org.codewiththamsanqa.springboot_users_application.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest request){
        User user = new User();

        user.setName(request.name());
        user.setEmail(request.email());

        User savedUser = userRepository.save(user);

        return UserResponse.fromEntity(savedUser);
    }

    public List<UserResponse> listUsers(){

        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public UserResponse updateUser(Long id, UserRequest request){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        user.setName(request.name());
        user.setEmail(request.email());

        User savedUser = userRepository.save(user);

        return UserResponse.fromEntity(savedUser);
    }

    public String deleteUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        userRepository.delete(user);

        return "User Removed";

    }



    private UserResponse mapToResponse(User user){
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }


}

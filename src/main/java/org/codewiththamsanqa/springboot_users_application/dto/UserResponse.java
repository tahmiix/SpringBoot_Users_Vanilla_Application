package org.codewiththamsanqa.springboot_users_application.dto;

import org.codewiththamsanqa.springboot_users_application.entity.User;

public record UserResponse(
        Long id,
        String name,
        String email
) {

    public static UserResponse fromEntity(User user){
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}

package com.xport.shopify.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private UUID userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    @Size(min = 10, max = 10, message = "Password must be of 10 characters")
    private String userMobile;
    public LocalDateTime createdAt;

    public UserDto(UUID userId, String userName, String userEmail, String userPassword, String userMobile) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userMobile = userMobile;
    }

}

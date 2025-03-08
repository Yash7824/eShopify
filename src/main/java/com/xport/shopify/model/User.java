package com.xport.shopify.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;

    @Column(name = "user_name")
    private String userName;

    @Column(unique = true, name = "user_email")
    private String userEmail;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_mobile", length = 10)
    @Size(min = 10, max = 10, message = "Password must be of 10 characters")
    private String userMobile;

    @Column(name = "created_at")
    public LocalDateTime createdAt;
}

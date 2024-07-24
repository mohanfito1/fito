package com.fitoverse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "otp")
    private String otp;

    @Column(name = "is_verified")
    private boolean isVerified;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "last_logout")
    private LocalDateTime lastLogout;
}

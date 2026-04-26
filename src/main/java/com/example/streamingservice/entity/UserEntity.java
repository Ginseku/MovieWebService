package com.example.streamingservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.print.attribute.standard.DateTimeAtCreation;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Builder
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userid;
    @Column(name = "user_email")
    @JsonProperty("email")
    private String userEmail;
    @Column(name = "user_password")
    @JsonProperty("password")
    private String userPassword;
    @JsonProperty("username")
    @Column(name = "user_name")
    private String username;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "provider")
    private String provider;
    @Column(name = "provider_id")
    @JsonProperty("providerid")
    private String providerId;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public enum Role {
        ROLE_USER,
        ROLE_ADMIN
    }

    public UserEntity(Long userid, String userEmail, String userPassword, String username, LocalDateTime createdAt, String provider, String providerId, Role role) {
        this.userid = userid;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.username = username;
        this.createdAt = createdAt;
        this.provider = provider;
        this.providerId = providerId;
        this.role = role;
    }

    public UserEntity(){

    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); //доработать так что бы я мог получать роль пользователя return List.of(() -> role.name());
    }

    @Override
    public @Nullable String getPassword() {
        return userPassword;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}


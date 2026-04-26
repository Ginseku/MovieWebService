package com.example.streamingservice.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;


public class SignUpRequest {

    @Size(min = 5, max = 255, message = "Username shoud contain from 5 to 255 symbols")
    @NotBlank(message = "Email can not be empty")
    private String username;
    @Size(min = 5, max = 255, message = "Email shoud contain from 5 to 255 symbols")
    @NotBlank(message = "Email can not be empty")
    private String email;
    @Size(min = 5, max = 255, message = "Password shoud contain from 5 to 255 symbols")
    @NotBlank(message = "Password can not be empty")
    private String password;

    public SignUpRequest(String username,String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public SignUpRequest(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SignUpRequest that = (SignUpRequest) o;
        return Objects.equals(username, that.username) && Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, password);
    }

    @Override
    public String toString() {
        return "SignUpRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

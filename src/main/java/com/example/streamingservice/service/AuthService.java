package com.example.streamingservice.service;

import com.example.streamingservice.entity.UserEntity;
import com.example.streamingservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService{
    private UserRepository userRepository;
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void addUserToBd(UserEntity data){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserEmail(data.getUserEmail());
        userEntity.setUserPassword(data.getUserPassword());
        userRepository.save(userEntity);
    }

    public Optional<UserEntity> getUserById(Long id){
        return userRepository.findById(id);
    }

    public String sendJWTToken(){
        return "JWTToken";
    }

}

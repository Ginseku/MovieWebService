package com.example.streamingservice.service;

import com.example.streamingservice.DTO.SignUpRequest;
import com.example.streamingservice.JWT.JWTService;
import com.example.streamingservice.entity.UserEntity;
import com.example.streamingservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserEntity saveUser(UserEntity user){
        return userRepository.save(user);
    }

    public UserEntity createUser(UserEntity user){
        if (userRepository.existsByUsername(user.getUsername())){
            throw new RuntimeException("This user already exist");
        }
        if (userRepository.existsByUserEmail(user.getUserEmail())) {
            throw new RuntimeException("User with this email already exist");
        }
        return saveUser(user);
    }

    public UserEntity getUserByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User wasn't found"));
    }

    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService userDetailsService() {
        return this::getUserByUsername;
    }
    //Получение текущего пользователя
    public UserEntity getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByUsername(username);
    }
    /**
     * Выдача прав администратора текущему пользователю
     */
    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(UserEntity.Role.ROLE_ADMIN);
        saveUser(user);
    }
}

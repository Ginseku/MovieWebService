package com.example.streamingservice.controller;

import com.example.streamingservice.entity.UserEntity;
import com.example.streamingservice.repository.UserRepository;
import com.example.streamingservice.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;
    private UserRepository userRepository;
    private boolean isExist;
    public AuthController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody UserEntity data){

        if (!data.getUserEmail().equals(userRepository.findByEmail(data.getUserEmail()))){
            try {
                authService.addUserToBd(data);
                return new ResponseEntity<>(HttpStatus.OK);//  вместо этой строки можно потом поставить возвращение JWT token, а эту строку перенести в основной return
            }catch (Exception e){
                return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody UserEntity data){
        if (data.getUserEmail().equals(userRepository.findByEmail(data.getUserEmail())) || data.getUserPassword().equals(userRepository.findByPassword(data.getUserPassword()))){
            authService.sendJWTToken();
            return new ResponseEntity(HttpStatus.OK);//тут надо вернуть JWT token потому что пользователь явно существует
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

//        return new ResponseEntity<>(HttpStatus.OK);
    }

}

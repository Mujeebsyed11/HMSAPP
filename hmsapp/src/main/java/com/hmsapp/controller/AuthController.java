package com.hmsapp.controller;

import com.hmsapp.entity.User;
import com.hmsapp.payload.LoginDto;
import com.hmsapp.payload.UserDto;
import com.hmsapp.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/sign-up")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto){
        if(authService.isUsernamePresent(userDto.getUsername())){
            return new ResponseEntity<> ("Username is already exist, try another one", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(authService.isEmailPresent(userDto.getEmail())){
            return new ResponseEntity<>("Email is already exist, Try with Different email.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(authService.isMobilePresent(userDto.getMobile())){
            return new ResponseEntity<> ("Mobile number is exist, Try another mobile number", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        UserDto savedUser = authService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        String token = authService.verifyLogin(loginDto);
        if(token!=null){
            return new ResponseEntity<>(token, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("invalid username/password",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

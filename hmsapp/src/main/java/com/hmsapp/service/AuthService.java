package com.hmsapp.service;

import com.hmsapp.entity.User;
import com.hmsapp.payload.LoginDto;
import com.hmsapp.payload.UserDto;
import com.hmsapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Optional;

@Service
public class AuthService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private JWTService jwtService;

    public AuthService(UserRepository userRepository, ModelMapper modelMapper, JWTService jwtService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    public UserDto convertToDto(User user){
        return modelMapper.map(user, UserDto.class);
    }
    public User convertToEntity(UserDto dto){
        return modelMapper.map(dto, User.class);
    }
    public boolean isUsernamePresent(String username){
        return userRepository.findByUsername(username).isPresent();
    }
    public boolean isEmailPresent(String email){
        return userRepository.findByEmail(email).isPresent();
    }
    public boolean isMobilePresent(String mobile){
        return userRepository.findByMobile(mobile).isPresent();
    }

    public UserDto createUser(UserDto userDto) {
        String encryptedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(12));
        userDto.setPassword(encryptedPassword);
        User savedUser = userRepository.save(convertToEntity(userDto));
        return convertToDto(savedUser);
    }

    public String verifyLogin(LoginDto loginDto){
        Optional<User> opUser = userRepository.findByUsername(loginDto.getUsername());
        if(opUser.isPresent()){
            User user = opUser.get();
            if(BCrypt.checkpw(loginDto.getPassword(), user.getPassword())){
                return jwtService.generateToken(user.getUsername());
            }else{
                return null;
            }
        }
        return null;
    }
}

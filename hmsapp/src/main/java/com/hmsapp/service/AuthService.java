package com.hmsapp.service;

import com.hmsapp.entity.User;
import com.hmsapp.payload.UserDto;
import com.hmsapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Service
public class AuthService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public AuthService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
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
}

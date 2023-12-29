package com.vtoan1517.service.impl;

import com.vtoan1517.dto.UserDTO;
import com.vtoan1517.entity.UserEntity;
import com.vtoan1517.repository.UserRepository;
import com.vtoan1517.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isExistingEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean isExistingUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public UserDTO findByUsername(String username) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        return userEntity.map(entity -> mapper.map(entity, UserDTO.class)).orElse(null);
    }

    @Override
    public UserDTO register(UserDTO newUserDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        newUserDTO.setPassword(encoder.encode(newUserDTO.getPassword()));

        UserEntity savedUser = userRepository.save(mapper.map(newUserDTO, UserEntity.class));

        return mapper.map(savedUser, UserDTO.class);
    }
}

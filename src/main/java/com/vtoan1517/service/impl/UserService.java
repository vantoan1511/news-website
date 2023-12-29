package com.vtoan1517.service.impl;

import com.vtoan1517.dto.UserDTO;
import com.vtoan1517.entity.UserEntity;
import com.vtoan1517.repository.UserRepository;
import com.vtoan1517.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isExistingUsername(String username) {
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public UserDTO findByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity != null) {
            return mapper.map(userEntity, UserDTO.class);
        }
        return null;
    }

    @Override
    public UserDTO register(UserDTO userDTO) {
        return null;
    }
}

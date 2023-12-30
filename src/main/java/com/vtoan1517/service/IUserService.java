package com.vtoan1517.service;

import com.vtoan1517.dto.UserDTO;

public interface IUserService {

    boolean isExistingEmail(String email);

    boolean isExistingUsername(String username);

    UserDTO findByUsername(String username);

    UserDTO findByToken(String token);

    UserDTO register(UserDTO userDTO);

    UserDTO activate(String token);
}

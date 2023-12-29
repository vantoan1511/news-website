package com.vtoan1517.service;

import com.vtoan1517.dto.UserDTO;

public interface IUserService {

    boolean isExistingUsername(String username);

    UserDTO findByUsername(String username);

    UserDTO register(UserDTO userDTO);
}

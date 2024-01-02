package com.vtoan1517.service;

import com.vtoan1517.dto.UserDTO;
import com.vtoan1517.exception.EmailNotFoundException;
import com.vtoan1517.exception.InvalidUserTokenException;

public interface IUserService {

    boolean foundByToken(String token);

    boolean isExistingEmail(String email);

    boolean isExistingUsername(String username);

    UserDTO findByUsername(String username);

    UserDTO findByToken(String token);

    UserDTO register(UserDTO userDTO);

    UserDTO activate(String token);

    void resetPassword(String email) throws EmailNotFoundException;

    void changePassword(String token, String newPassword) throws InvalidUserTokenException;
}

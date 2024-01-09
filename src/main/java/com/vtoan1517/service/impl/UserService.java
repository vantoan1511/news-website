package com.vtoan1517.service.impl;

import com.vtoan1517.constant.Application;
import com.vtoan1517.dto.UserDTO;
import com.vtoan1517.entity.Role;
import com.vtoan1517.entity.User;
import com.vtoan1517.exception.EmailNotFoundException;
import com.vtoan1517.exception.InvalidUserTokenException;
import com.vtoan1517.exception.UserNotFoundException;
import com.vtoan1517.repository.RoleRepository;
import com.vtoan1517.repository.UserRepository;
import com.vtoan1517.service.IEmailService;
import com.vtoan1517.service.IUserService;
import com.vtoan1517.utils.CollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final IEmailService emailService;

    private final CollectionMapper mapper = new CollectionMapper();

    @Autowired
    public UserService(UserRepository userRepository, IEmailService emailService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean foundByToken(String token) {
        return userRepository.findByToken(token) != null;
    }

    @Override
    public boolean isExistingEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public boolean isExistingUsername(String username) {
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO findByToken(String token) {
        User user = userRepository.findByToken(token);
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO register(UserDTO newUserDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        newUserDTO.setPassword(encoder.encode(newUserDTO.getPassword()));
        newUserDTO.setFirstName(newUserDTO.getFirstName().trim());
        newUserDTO.setLastName(newUserDTO.getLastName().trim());
        newUserDTO.setAvatarUrl("/static/admin/dist/img/avatar.png");

        String token = UUID.randomUUID().toString();
        newUserDTO.setToken(token);
        StringBuilder text = new StringBuilder("Tai khoan cua ban da duoc dang ky thanh cong!");
        text.append("Click vao duong link sau de kich hoat tai khoan: ");
        text.append("<a href='");
        text.append(Application.BASE_URL + "/accounts/activate?token=").append(token);
        text.append("'>Kich hoat ngay!</a>");
        emailService.sendSimpleEmail(newUserDTO.getEmail(), "Kích hoạt tài khoản", text.toString());

        User savedUser = mapper.map(newUserDTO, User.class);
        Role role = roleRepository.findByCode("user");
        savedUser.setRoles(Collections.singletonList(role));
        savedUser = userRepository.save(savedUser);

        return mapper.map(savedUser, UserDTO.class);
    }

    @Override
    public UserDTO activate(String token) throws InvalidUserTokenException {
        User user = userRepository.findByToken(token);
        if (user == null) throw new InvalidUserTokenException("Token không tồn tại hoặc đã hết hạn");

        user.setToken(null);
        user.setActivated(true);
        user = userRepository.save(user);
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public void resetPassword(String email) throws EmailNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user = userRepository.save(user);

            StringBuilder text = new StringBuilder();
            text.append("Click vao link sau day de reset mat khau: ");
            text.append("<a href='");
            text.append(Application.BASE_URL + "/reset?token=");
            text.append(token);
            text.append("'>Reset mat khau</a>");

            emailService.sendSimpleEmail(email, "Reset mat khau", text.toString());

            mapper.map(user, UserDTO.class);
        } else {
            throw new EmailNotFoundException("Địa chỉ email không tôn tại trên hệ thống");
        }
    }

    @Override
    public void changePassword(String token, String newPassword) throws InvalidUserTokenException {
        User user = userRepository.findByToken(token);
        if (user == null) throw new InvalidUserTokenException("Token không tồn tại hoặc đã hết hạn");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(newPassword));
        user.setToken(null);
        userRepository.save(user);
    }
}

package com.vtoan1517.service.impl;

import com.vtoan1517.constant.Application;
import com.vtoan1517.dto.UserDTO;
import com.vtoan1517.entity.RoleEntity;
import com.vtoan1517.entity.UserEntity;
import com.vtoan1517.exception.EmailNotFoundException;
import com.vtoan1517.exception.InvalidUserTokenException;
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
        return userRepository.findByToken(token).isPresent();
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
    public UserDTO findByToken(String token) {
        Optional<UserEntity> userEntity = userRepository.findByToken(token);
        return userEntity.map(entity -> mapper.map(entity, UserDTO.class)).orElse(null);
    }

    @Override
    public UserDTO register(UserDTO newUserDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        newUserDTO.setPassword(encoder.encode(newUserDTO.getPassword()));
        newUserDTO.setFirstName(newUserDTO.getFirstName().trim());
        newUserDTO.setLastName(newUserDTO.getLastName().trim());
        newUserDTO.setAvatarUrl("/static/admin/dist/img/avatar.png");
        //Just for testing
        String token = UUID.randomUUID().toString();
        newUserDTO.setToken(token);
        StringBuilder text = new StringBuilder("Tai khoan cua ban da duoc dang ky thanh cong!");
        text.append("Click vao duong link sau de kich hoat tai khoan: ");
        text.append("<a href='");
        text.append(Application.BASE_URL + "/accounts/activate?token=").append(token);
        text.append("'>Kich hoat ngay!</a>");
        emailService.sendSimpleEmail(newUserDTO.getEmail(), "Kích hoạt tài khoản", text.toString());

        UserEntity savedUser = mapper.map(newUserDTO, UserEntity.class);
        RoleEntity roleEntity = roleRepository.findByCode("user");
        savedUser.setRoles(Collections.singletonList(roleEntity));
        savedUser = userRepository.save(savedUser);

        return mapper.map(savedUser, UserDTO.class);
    }

    @Override
    public UserDTO activate(String token) {
        Optional<UserEntity> found = userRepository.findByToken(token);
        if (found.isPresent()) {
            UserEntity userEntity = found.get();
            userEntity.setToken(null);
            userEntity.setActivated(true);
            userEntity = userRepository.save(userEntity);
            return mapper.map(userEntity, UserDTO.class);
        }
        return null;
    }

    @Override
    public void resetPassword(String email) throws EmailNotFoundException {
        Optional<UserEntity> found = userRepository.findByEmail(email);
        if (found.isPresent()) {
            UserEntity userEntity = found.get();
            String token = UUID.randomUUID().toString();
            userEntity.setToken(token);
            userEntity = userRepository.save(userEntity);

            StringBuilder text = new StringBuilder();
            text.append("Click vao link sau day de reset mat khau: ");
            text.append("<a href='");
            text.append(Application.BASE_URL + "/reset?token=");
            text.append(token);
            text.append("'>Reset mat khau</a>");

            emailService.sendSimpleEmail(email, "Reset mat khau", text.toString());

            mapper.map(userEntity, UserDTO.class);
        } else {
            throw new EmailNotFoundException("Địa chỉ email không tôn tại trên hệ thống");
        }
    }

    @Override
    public void changePassword(String token, String newPassword) throws InvalidUserTokenException {
        Optional<UserEntity> found = userRepository.findByToken(token);
        if (found.isPresent()) {
            UserEntity user = found.get();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(newPassword));
            user.setToken(null);
            userRepository.save(user);
        } else {
            throw new InvalidUserTokenException("Truy cập vào token không hợp lệ");
        }
    }
}

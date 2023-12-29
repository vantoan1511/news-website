package com.vtoan1517.validator;

import com.vtoan1517.dto.UserDTO;
import com.vtoan1517.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CustomUserValidator implements Validator {

    private final IUserService userService;

    @Autowired
    public CustomUserValidator(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;
        if (userService.isExistingUsername(userDTO.getUsername()))
            errors.rejectValue("username", "username.exists", "Tên đăng nhập đã tồn tại!");
        if (userService.isExistingEmail(userDTO.getEmail()))
            errors.rejectValue("email", "email.exists", "Email đã liên kết với một tài khoản khác");
    }
}

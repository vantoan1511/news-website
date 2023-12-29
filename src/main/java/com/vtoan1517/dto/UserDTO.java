package com.vtoan1517.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Getter
@Setter
public class UserDTO {

    @Email(message = "Email không đúng định dạng!")
    @NotBlank(message = "Vui lòng nhập địa chỉ email!")
    private String email;
    @NotBlank(message = "Vui lòng nhập họ tên!")
    @Pattern(regexp = ".*[a-zA-Z]+.", message = "Họ tên không hợp lệ!")
    private String fullname;
    @NotEmpty(message = "Vui lòng nhập tên đăng nhập!")
    @Size(min = 1, max = 16, message = "Độ dài không hợp lệ!")
    private String username;
    @NotEmpty(message = "Vui lòng nhập mật khẩu")
    @Size(min = 1, max = 16, message = "Mật khẩu hợp lệ có độ dài từ 1-16 ký tự.")
    private String password;

}

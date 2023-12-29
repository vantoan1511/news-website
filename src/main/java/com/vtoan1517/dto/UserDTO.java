package com.vtoan1517.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @Email(message = "Email không đúng định dạng!")
    @Size(min = 1, max = 45, message = "Email có độ dài từ 1-45 ký tự")
    private String email;
    @Pattern(regexp = ".*[a-zA-Z]+.", message = "Họ tên chứa ký tự không hợp lệ!")
    private String firstName;
    @Pattern(regexp = ".*[a-zA-Z]+.", message = "Họ tên chứa ký tự không hợp lệ!")
    private String lastName;
    @Size(min = 1, max = 46, message = "Tên đăng nhập có độ dài từ 1-46 ký tự")
    private String username;
    @Size(min = 1, max = 64, message = "Mật khẩu có độ dài từ 1-64 ký tự.")
    private String password;
    private boolean activated;
    private String token;
    private String avatarUrl;
}

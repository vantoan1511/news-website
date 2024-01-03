package com.vtoan1517.service.impl;

import com.vtoan1517.dto.CustomUser;
import com.vtoan1517.entity.Role;
import com.vtoan1517.entity.User;
import com.vtoan1517.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUserEntity;
        optionalUserEntity = isEmail(username) ? userRepository.findByEmail(username) : userRepository.findByUsername(username);

        if (optionalUserEntity.isEmpty()) {
            throw new UsernameNotFoundException("Không tìm thấy tài khoản với tên đăng nhập " + username);
        }

        User user = optionalUserEntity.get();

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            String code = role.getCode();
            authorities.add(new SimpleGrantedAuthority(code));
        }

        return CustomUser.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .activated(user.isActivated())
                .credentialsNonExpired(true)
                .build();
    }

    private boolean isEmail(String text) {
        return text.contains("@");
    }
}

package com.vtoan1517.service.impl;

import com.vtoan1517.dto.CustomUser;
import com.vtoan1517.entity.RoleEntity;
import com.vtoan1517.entity.UserEntity;
import com.vtoan1517.repository.UserRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

        Optional<UserEntity> optionalUserEntity;
        optionalUserEntity = isEmail(username) ? userRepository.findByEmail(username) : userRepository.findByUsername(username);

        if (optionalUserEntity.isEmpty()) {
            throw new UsernameNotFoundException("Không tìm thấy tài khoản với tên đăng nhập " + username);
        }

        UserEntity userEntity = optionalUserEntity.get();

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity roleEntity : userEntity.getRoles()) {
            String code = roleEntity.getCode();
            authorities.add(new SimpleGrantedAuthority(code));
        }

        return CustomUser.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(authorities)
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .activated(userEntity.isActivated())
                .credentialsNonExpired(true)
                .build();
    }

    private boolean isEmail(String text) {
        return text.contains("@");
    }
}

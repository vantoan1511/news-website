package com.vtoan1517.service.impl;

import com.vtoan1517.dto.MyUser;
import com.vtoan1517.entity.RoleEntity;
import com.vtoan1517.entity.UserEntity;
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
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role : userEntity.get().getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        }

        MyUser myUser = new MyUser(userEntity.get().getUsername(), userEntity.get().getPassword(), true, true, true, true,
                authorities);
        myUser.setFirstName(userEntity.get().getFirstName());
        myUser.setLastName(userEntity.get().getLastName());
        return myUser;
    }

}

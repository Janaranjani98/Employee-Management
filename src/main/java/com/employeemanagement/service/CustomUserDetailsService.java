package com.employeemanagement.service;

import com.employeemanagement.entity.UserInfo;
import com.employeemanagement.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo  userInfo = userInfoRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User  not found"));

        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_" +userInfo.getRole().name())
        );
        return new org.springframework.security.core.userdetails.User(userInfo.getUserName(),userInfo.getPassword(),authorities);
    }
}

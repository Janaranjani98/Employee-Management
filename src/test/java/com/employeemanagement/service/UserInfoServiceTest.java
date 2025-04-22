package com.employeemanagement.service;

import com.employeemanagement.dto.UserInfoDTO;
import com.employeemanagement.entity.Role;
import com.employeemanagement.entity.UserInfo;
import com.employeemanagement.repository.UserInfoRepository;
import com.employeemanagement.utility.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserInfoServiceTest {


    @Mock
    private UserInfoRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UserInfoService userInfoService;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    void registerUser_successfulRegistration() {
        // given
        UserInfoDTO dto = new UserInfoDTO("john@example.com", "password123", Role.Employee,"john_doe");

        when(userRepository.findByUserName("john_doe")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password123")).thenReturn("hashedPwd");

        // when
        String result = userInfoService.registerUser(dto);

        // then
        verify(userRepository).save(any(UserInfo.class));
        assertEquals("User Registered Successfully", result);
       // verify(userRepository, times(1)).save(any(UserInfo.class));
    }

    @Test
    void registerUser_usernameAlreadyExists_shouldThrowException() {
        // given
        UserInfoDTO dto = new UserInfoDTO("john@example.com", "password123", Role.Employee,"john_doe");
        UserInfo existingUser = new UserInfo();
        existingUser.setUserName("john_doe");

        when(userRepository.findByUserName("john_doe")).thenReturn(Optional.of(existingUser));

        // when & then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userInfoService.registerUser(dto);
        });

        assertEquals("User name already exists", exception.getMessage());
        //verify(userRepository, never()).save(any());
    }

    @Test
    void shouldDeleteUserById(){
        Long id=5L;
        userInfoService.deleteUser(id);
        verify(userRepository).deleteById(id);
    }




}

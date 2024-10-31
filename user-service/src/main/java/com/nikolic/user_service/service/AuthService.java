package com.nikolic.user_service.service;

import com.nikolic.user_service.dto.UserLoginDTO;
import com.nikolic.user_service.model.User;
import com.nikolic.user_service.repository.UserRepository;
import com.nikolic.user_service.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String login(UserLoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return jwtUtil.generateToken(user.getUsername(), user.getRole().name());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}

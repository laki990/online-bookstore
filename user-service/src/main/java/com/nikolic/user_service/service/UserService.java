package com.nikolic.user_service.service;

import com.nikolic.user_service.dto.UserProfileDTO;
import com.nikolic.user_service.dto.UserRegistrationDTO;
import com.nikolic.user_service.dto.UserUpdateDTO;
import com.nikolic.user_service.mapper.UserMapper;
import com.nikolic.user_service.model.User;
import com.nikolic.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserProfileDTO registerUser(UserRegistrationDTO registerDTO) {
        User user = UserMapper.INSTANCE.toUser(registerDTO);
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        User savedUser = userRepository.save(user);
        return UserMapper.INSTANCE.toUserProfileDTO(savedUser);
    }

    public UserProfileDTO getUserById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));;
        return UserMapper.INSTANCE.toUserProfileDTO(user);
    }

    public UserDetails getUserByUsername(String username) {
        return userRepository.findByUsername(username).
                orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserProfileDTO updateUser( Long id, UserUpdateDTO updateDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        updateDTO.getUsername().ifPresent(user::setUsername);
        updateDTO.getPassword().ifPresent(password -> user.setPassword(passwordEncoder.encode(password)));
        updateDTO.getEmail().ifPresent(user::setEmail);
        updateDTO.getRole().ifPresent(user::setRole);

        return UserMapper.INSTANCE.toUserProfileDTO(user);
    }
}

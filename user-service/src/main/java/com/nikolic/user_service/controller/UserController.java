package com.nikolic.user_service.controller;

import com.nikolic.user_service.dto.UserLoginDTO;
import com.nikolic.user_service.dto.UserProfileDTO;
import com.nikolic.user_service.dto.UserRegistrationDTO;
import com.nikolic.user_service.service.AuthService;
import com.nikolic.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {


        private final UserService userService;
        private final AuthService authService;

        @PostMapping("/register")
        public ResponseEntity<UserProfileDTO> register(@RequestBody UserRegistrationDTO registerDTO) {
            return ResponseEntity.ok(userService.registerUser(registerDTO));
        }

        @PostMapping("/login")
        public ResponseEntity<String> login(@RequestBody UserLoginDTO loginDTO) {
            String token = authService.login(loginDTO);
            return ResponseEntity.ok(token);
        }

        @GetMapping("/{id}")
        public ResponseEntity<UserProfileDTO> getUser(@PathVariable Long id) {
            // Implement logic to retrieve user by ID
            return null;  // Placeholder for actual implementation
        }

        @PutMapping("/{id}")
        public ResponseEntity<UserProfileDTO> updateUser(@PathVariable Long id, @RequestBody UserRegistrationDTO updateDTO) {
            // Implement logic to update user by ID
            return null;  // Placeholder for actual implementation
        }
    }


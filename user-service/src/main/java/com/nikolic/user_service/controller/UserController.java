package com.nikolic.user_service.controller;

import com.nikolic.user_service.dto.UserLoginDTO;
import com.nikolic.user_service.dto.UserProfileDTO;
import com.nikolic.user_service.dto.UserRegistrationDTO;
import com.nikolic.user_service.dto.UserUpdateDTO;
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

        @PostMapping("/register")
        public ResponseEntity<UserProfileDTO> register(@RequestBody UserRegistrationDTO registerDTO) {
            return ResponseEntity.ok(userService.registerUser(registerDTO));
        }

        @GetMapping("/{id}")
        public ResponseEntity<UserProfileDTO> getUser(@PathVariable Long id) {
            var userDTO = userService.getUserById(id);
            return ResponseEntity.ok(userDTO);
        }

        @PutMapping("/{id}")
        public ResponseEntity<UserProfileDTO> updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO updateDTO) {
            var updatedUser = userService.updateUser(id, updateDTO);
            return ResponseEntity.ok(updatedUser);
        }
    }


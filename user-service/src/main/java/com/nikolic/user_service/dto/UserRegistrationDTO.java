package com.nikolic.user_service.dto;

import com.nikolic.user_service.model.Role;
import lombok.Data;

@Data
public class UserRegistrationDTO {

    private String username;
    private String password;
    private String email;
    private Role role;
}

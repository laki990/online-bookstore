package com.nikolic.user_service.dto;

import lombok.Data;

@Data
public class UserRegistrationDTO {

    private String username;
    private String password;
    private String email;
}

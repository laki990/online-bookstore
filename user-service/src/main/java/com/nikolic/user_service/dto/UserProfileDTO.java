package com.nikolic.user_service.dto;

import lombok.Data;

@Data
public class UserProfileDTO {

    private Long id;
    private String username;
    private String email;
}

package com.nikolic.user_service.dto;

import com.nikolic.user_service.model.Role;
import lombok.Data;

import java.util.Optional;

@Data
public class UserUpdateDTO {
    private Optional<String> username = Optional.empty();
    private Optional<String> password = Optional.empty();
    private Optional<String> email = Optional.empty();
    private Optional<Role> role = Optional.empty();
}

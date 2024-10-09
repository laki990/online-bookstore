package com.nikolic.user_service.mapper;


import com.nikolic.user_service.dto.UserProfileDTO;
import com.nikolic.user_service.dto.UserRegistrationDTO;
import com.nikolic.user_service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(UserRegistrationDTO userRegisterDTO);

    UserProfileDTO toUserProfileDTO(User user);
}
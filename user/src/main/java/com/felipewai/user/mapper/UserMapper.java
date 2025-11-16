package com.felipewai.user.mapper;


import com.felipewai.user.dto.user.RegisterUserRequestDTO;
import com.felipewai.user.dto.user.UserResponse;
import com.felipewai.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toModel(RegisterUserRequestDTO registerUserRequestDTO);

    UserResponse toResponse(User user);

}

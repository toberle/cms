package com.example.cms.mapper;

import com.example.cms.api.rest.user.UserDto;
import com.example.cms.api.rest.user.UserRegisterDto;
import com.example.cms.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User map(UserRegisterDto userRegisterDto);

    UserDto map(User user);
}

package com.example.cms.mapper;

import com.example.cms.api.rest.user.UserDto;
import com.example.cms.api.rest.user.UserListDto;
import com.example.cms.api.rest.user.UserRegisterDto;
import com.example.cms.api.rest.user.UserUpdateDto;
import com.example.cms.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User map(UserRegisterDto userRegisterDto);

    UserDto map(User user);

    List<UserListDto> map(List<User> entities);

    void update(UserUpdateDto updateDto, @MappingTarget User user);
}

package com.example.cms.service;

import com.example.cms.api.rest.user.UserDto;
import com.example.cms.api.rest.user.UserRegisterDto;
import com.example.cms.api.rest.user.UserUpdateDto;

public interface UserService {


    UserDto register(UserRegisterDto registerDto);
    UserDto update(UserUpdateDto updateDto);
    UserDto findById(Long id);
    UserDto findByUsername(String username);
    void enable(Long id);
    void disable(Long id);
    void unlock(Long id);
}

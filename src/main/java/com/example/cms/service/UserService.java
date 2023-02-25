package com.example.cms.service;

import com.example.cms.api.rest.ListDto;
import com.example.cms.api.rest.user.*;

public interface UserService {

    UserDto register(UserRegisterDto registerDto);
    ListDto<UserListDto> getAll(int pageNumber);
    UserDto update(Long id, UserUpdateDto updateDto);
    UserDto findById(Long id);
    UserDto findByUsername(String username);
    void enable(Long id);
    void disable(Long id);
    void unlock(Long id);
    void changePassword(UserChangePasswordDto changePasswordDto);
    void forgotPassword(UserForgotPasswordDto forgotPasswordDto);
    void resetPassword(UserResetPasswordDto resetPasswordDto);
}

package com.example.cms.service;

import com.example.cms.api.rest.ListDto;
import com.example.cms.api.rest.user.*;
import com.example.cms.domain.User;
import com.example.cms.exception.EntityNotFoundException;
import com.example.cms.mapper.UserMapper;
import com.example.cms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDto register(UserRegisterDto registerDto) {
        //TODO handle duplicity on DB layer (exception mapper - DB ex)
        User user = userMapper.map(registerDto);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        User save = userRepository.save(user);
        return userMapper.map(save);
    }

    @Transactional(readOnly = true)
    @Override
    public ListDto<UserListDto> getAll(int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, 20);
        Page<User> page = userRepository.findAll(pageRequest);
        List<UserListDto> dtoList = userMapper.map(page.getContent());
        return new ListDto<>(dtoList, page.getNumber(), page.getSize(), page.getTotalPages(), page.getTotalElements());
    }

    @Transactional
    @Override
    public UserDto update(Long id, UserUpdateDto updateDto) {
        User user = find(id);
        userMapper.update(updateDto, user);
        User result = userRepository.save(user);
        return userMapper.map(result);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto findById(Long id) {
        User user = find(id);
        return userMapper.map(user);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found - username" + username));
        return userMapper.map(user);
    }

    @Transactional
    @Override
    public void enable(Long id) {
        User user = find(id);
        user.setDisabled(false);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void disable(Long id) {
        User user = find(id);
        user.setDisabled(true);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void unlock(Long id) {
        User user = find(id);
        user.setLocked(false);
        user.setInvalidPasswords(0);
        user.setLastInvalidPasswordTimestamp(null);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void changePassword(UserChangePasswordDto changePasswordDto) {
        //TODO implement (encoder)
    }

    @Transactional
    @Override
    public void forgotPassword(UserForgotPasswordDto forgotPasswordDto) {
        //TODO implement (generate token with TS and expiration + send mail with link/token)
    }

    @Transactional
    @Override
    public void resetPassword(UserResetPasswordDto resetPasswordDto) {
        //TODO implement (link -> check token and expiration -> set new password + mark token as used/delete)
    }

    private User find(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found - ID" + id));
    }
}

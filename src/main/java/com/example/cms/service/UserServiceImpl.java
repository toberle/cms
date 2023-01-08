package com.example.cms.service;

import com.example.cms.api.rest.user.UserDto;
import com.example.cms.api.rest.user.UserRegisterDto;
import com.example.cms.api.rest.user.UserUpdateDto;
import com.example.cms.domain.User;
import com.example.cms.exception.EntityNotFoundException;
import com.example.cms.mapper.UserMapper;
import com.example.cms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDto register(UserRegisterDto registerDto) {
        //TODO check duplicity for username (validator) + handle duplicity on DB layer (exception mapper - DB ex)
        User user = userMapper.map(registerDto);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setCreatedTimestamp(new Date()); //TODO delete - enable auditing @CreatedDate,...
        User save = userRepository.save(user);
        return userMapper.map(save);
    }

    @Override
    public UserDto update(UserUpdateDto updateDto) {
        //TODO implement
        return null;
    }

    @Override
    public UserDto findById(Long id) {
        User user = find(id);
        return userMapper.map(user);
    }

    @Override
    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(
                        "User not found - username" + username));
        return userMapper.map(user);
    }

    @Override
    public void enable(Long id) {
        User user = find(id);
        user.setDisabled(false);
        userRepository.save(user);
    }

    @Override
    public void disable(Long id) {
        User user = find(id);
        user.setDisabled(true);
        userRepository.save(user);
    }

    @Override
    public void unlock(Long id) {
        User user = find(id);
        user.setLocked(false);
        user.setInvalidPasswords(0);
        user.setLastInvalidPasswordTimestamp(null);
        userRepository.save(user);
    }

    private User find(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "User not found - ID" + id));
    }
}

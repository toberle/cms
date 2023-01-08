package com.example.cms.controller;

import com.example.cms.api.rest.user.UserDto;
import com.example.cms.api.rest.user.UserRegisterDto;
import com.example.cms.service.UserService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@Timed
@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserService userService;

    @PostMapping(
            path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDto> register(@RequestBody UserRegisterDto registerDto) {
        return ResponseEntity.ok(userService.register(registerDto));
    }
}

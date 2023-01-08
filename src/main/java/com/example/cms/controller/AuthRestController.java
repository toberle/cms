package com.example.cms.controller;

import com.example.cms.api.rest.auth.LoginDto;
import com.example.cms.facade.AuthFacade;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@Timed
@RestController
public class AuthRestController {

    private final AuthFacade authFacade;

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        String jwt = authFacade.login(loginDto);
        return ResponseEntity.ok(jwt);
    }
}

package com.example.cms.service;

import org.springframework.security.core.Authentication;

public interface AuthService {

    String login(Authentication authentication);
}

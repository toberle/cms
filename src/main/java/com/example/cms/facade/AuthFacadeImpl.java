package com.example.cms.facade;

import com.example.cms.api.rest.auth.AuthDto;
import com.example.cms.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthFacadeImpl implements AuthFacade {

    private final AuthenticationManager authenticationManager;
    private final AuthService authService;

    @Override
    public String login(AuthDto authDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword())
        );
        return authService.login(authentication);
    }
}

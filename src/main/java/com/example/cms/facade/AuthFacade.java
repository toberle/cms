package com.example.cms.facade;

import com.example.cms.api.rest.auth.LoginDto;

public interface AuthFacade {

    String login(LoginDto loginDto);
}

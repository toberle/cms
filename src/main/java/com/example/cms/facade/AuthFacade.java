package com.example.cms.facade;

import com.example.cms.api.rest.auth.AuthDto;

public interface AuthFacade {

    String login(AuthDto authDto);
}

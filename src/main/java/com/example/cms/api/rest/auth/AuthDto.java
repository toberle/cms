package com.example.cms.api.rest.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class AuthDto {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}

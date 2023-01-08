package com.example.cms.api.rest.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class LoginDto {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}

package com.example.cms.api.rest.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRegisterDto {

    private String username;

    private String email;

    private String password;
}

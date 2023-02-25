package com.example.cms.api.rest.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserForgotPasswordDto {
    private String email;
}

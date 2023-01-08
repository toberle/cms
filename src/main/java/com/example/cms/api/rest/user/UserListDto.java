package com.example.cms.api.rest.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class UserListDto {
    private Long id;

    private String username;

    private String email;

    private boolean disabled;

    private Date lastInvalidPasswordTimestamp;

    private byte invalidPasswords;

    private boolean locked;
}

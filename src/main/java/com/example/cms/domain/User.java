package com.example.cms.domain;

import com.example.cms.domain.converter.BooleanConverter;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "disabled")
    @Convert(converter = BooleanConverter.class)
    private boolean disabled;

    @Column(name = "last_invalid_password_ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastInvalidPasswordTimestamp;

    @Column(name = "invalid_passwords")
    private int invalidPasswords;

    @Column(name = "locked")
    @Convert(converter = BooleanConverter.class)
    private boolean locked;

    @CreatedDate
    @Column(name = "created_ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTimestamp;

    @Column(name = "updated_ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTimestamp;
}

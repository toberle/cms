package com.example.cms.domain;

import com.example.cms.domain.converter.BooleanConverter;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
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
    private Instant lastInvalidPasswordTimestamp;

    @Column(name = "invalid_passwords")
    private int invalidPasswords;

    @Column(name = "locked")
    @Convert(converter = BooleanConverter.class)
    private boolean locked;

    @CreatedDate
    @Column(name = "created_ts")
    private Instant createdTimestamp;

    @LastModifiedDate
    @Column(name = "updated_ts")
    private Instant updatedTimestamp;
}

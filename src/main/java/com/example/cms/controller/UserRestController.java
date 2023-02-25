package com.example.cms.controller;

import com.example.cms.api.rest.ListDto;
import com.example.cms.api.rest.user.*;
import com.example.cms.service.UserService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Timed
@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserService userService;

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> register(@RequestBody UserRegisterDto registerDto) {
        return ResponseEntity.ok(userService.register(registerDto));
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListDto<UserListDto>> getAll(
            @RequestParam(name = "page", defaultValue = "0") int page) {
        return ResponseEntity.ok(userService.getAll(page));
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')") //TODO or is "user"
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')") //TODO or is "user"
    @GetMapping(path = "/username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')") //TODO or is "user"
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> update(@PathVariable("id") Long id,
                                          @RequestBody UserUpdateDto updateDto) {
        return ResponseEntity.ok(userService.update(id, updateDto));
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    @PostMapping(path = "/{id}/unlock")
    public ResponseEntity<Void> unlock(@PathVariable("id") Long id) {
        userService.unlock(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    @PostMapping(path = "/{id}/enable")
    public ResponseEntity<Void> enable(@PathVariable("id") Long id) {
        userService.enable(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    @PostMapping(path = "/{id}/disable")
    public ResponseEntity<Void> disable(@PathVariable("id") Long id) {
        userService.disable(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/forgot-password", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserForgotPasswordDto> forgotPassword(
            @RequestBody UserForgotPasswordDto forgotPasswordDto) {
        userService.forgotPassword(forgotPasswordDto);
        return ResponseEntity.ok(forgotPasswordDto);
    }

    @PostMapping(path = "/reset-password", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> resetPassword(@RequestBody UserResetPasswordDto resetPasswordDto) {
        userService.resetPassword(resetPasswordDto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/change-password", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> changePassword(@RequestBody UserChangePasswordDto changePasswordDto) {
        userService.changePassword(changePasswordDto);
        return ResponseEntity.noContent().build();
    }
}

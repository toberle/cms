package com.example.cms.configuration;

import com.example.cms.api.rest.ErrorDto;
import com.example.cms.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ErrorDto> handleEntityNotFoundException(EntityNotFoundException e) {
        log.info("NOT FOUND: " + e.getMessage());
        return ResponseEntity.notFound().build();
    }

}

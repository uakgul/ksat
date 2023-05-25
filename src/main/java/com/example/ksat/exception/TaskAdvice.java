package com.example.ksat.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class TaskAdvice {

    @ExceptionHandler(TaskHandlerException.class)
    public ResponseEntity<String> handleException(TaskHandlerException exception) {
        String errorClass = exception.getClass().getSimpleName();
        log.error("ErrorClass: {}", errorClass, exception);

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<String> handleAllExceptions(Exception exception) {
        log.error("Unknown exception occurred", exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

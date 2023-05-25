package com.example.ksat.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class TaskHandlerException extends RuntimeException {
    private final String message;
}

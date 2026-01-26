package com.load.fundation.shared.exception;

import com.load.fundation.shared.util.constants.HttpMessages;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex, HttpServletRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put(HttpMessages.KEY_TIMESTAMP, LocalDateTime.now().toString());
        body.put(HttpMessages.KEY_STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put(HttpMessages.KEY_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        body.put(HttpMessages.KEY_EXCEPTION, ex.getClass().getName());
        body.put(HttpMessages.KEY_MESSAGE, ex.getMessage());
        body.put(HttpMessages.KEY_PATH, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}

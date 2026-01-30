package com.load.fundation.shared.exception;

import com.load.fundation.dataset.exception.DatasetInternalException;
import com.load.fundation.dataset.exception.DatasetValidateException;
import com.load.fundation.shared.util.constants.HttpMessages;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DatasetValidateException.class)
    public ResponseEntity<Map<String, Object>> handleValidateException(DatasetValidateException ex, HttpServletRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put(HttpMessages.KEY_TIMESTAMP, LocalDateTime.now().toString());
        body.put(HttpMessages.KEY_STATUS, HttpStatus.BAD_REQUEST.value());
        body.put(HttpMessages.KEY_ERROR, HttpStatus.BAD_REQUEST.getReasonPhrase());
        body.put(HttpMessages.KEY_EXCEPTION, ex.getClass().getName());
        body.put(HttpMessages.KEY_MESSAGE, ex.getMessage());
        body.put(HttpMessages.KEY_PATH, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(DatasetInternalException.class)
    public ResponseEntity<Map<String, Object>> handleInternalException(DatasetInternalException ex, HttpServletRequest request) {
        // Log the internal exception with stack trace and message
        log.error("DatasetInternalException thrown: {}", ex.getMessage(), ex);

        Map<String, Object> body = new HashMap<>();
        body.put(HttpMessages.KEY_TIMESTAMP, LocalDateTime.now().toString());
        body.put(HttpMessages.KEY_STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put(HttpMessages.KEY_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        body.put(HttpMessages.KEY_EXCEPTION, ex.getClass().getName());
        body.put(HttpMessages.KEY_MESSAGE, ex.getMessage());
        body.put(HttpMessages.KEY_PATH, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

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

package edu.nvs.manage.validation;

import edu.nvs.manage.validation.exceptions.ResourceNotFoundException;
import edu.nvs.manage.validation.response.ErrorResponse;
import edu.nvs.manage.validation.response.ValidationErrorResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.info("ResourceNotFoundException - {}", ex.getStackTrace());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<ValidationErrorResponse>> handleConstraintViolationException(ConstraintViolationException ex) {
        List<ValidationErrorResponse> errors = ex.getConstraintViolations().stream()
                .map(violation ->
                        new ValidationErrorResponse(violation.getPropertyPath().toString(), violation.getMessage())).collect(Collectors.toList());
        log.info("ConstraintViolationException - {}", ex.getStackTrace());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(ServerWebInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleWebExchangeBindException(ServerWebInputException ex) {
        log.info("WebExchangeBindException - {}", ex.getStackTrace());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<ValidationErrorResponse>> handleWebExchangeBindException(WebExchangeBindException ex) {
        List<ValidationErrorResponse> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ValidationErrorResponse(error.getField(), error.getDefaultMessage())).collect(Collectors.toList());
        log.info("WebExchangeBindException - {}", ex.getStackTrace());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.info("Exception - {}", ex.getStackTrace());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("An unexpected error occurred " + ex.getMessage()));
    }
}

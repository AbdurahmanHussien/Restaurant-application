package com.spring.restaurant.exceptions;

import com.spring.restaurant.dto.BundleMessageDTO;
import com.spring.restaurant.response.ApiError;
import com.spring.restaurant.service.BundleTranslatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@ControllerAdvice
public class GlobalException {

    private final BundleTranslatorService bundleTranslator;

    public GlobalException(BundleTranslatorService bundleTranslator) {
        this.bundleTranslator = bundleTranslator;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex) {
       BundleMessageDTO messages = bundleTranslator.getBundleMessages(ex.getMessage());

       ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.value(),
               HttpStatus.NOT_FOUND.getReasonPhrase(),
               messages);

            return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequest(BadRequestException ex) {
        BundleMessageDTO messages = bundleTranslator.getBundleMessages(ex.getMessage());

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                messages);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ApiError>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<ApiError> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(errorField -> {
                    var messages = bundleTranslator.getBundleMessages(errorField.getDefaultMessage());
                    return new ApiError(
                            HttpStatus.BAD_REQUEST.value(),
                            errorField.getField(),
                            messages
                    );
                })
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleRuntime(BadCredentialsException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}

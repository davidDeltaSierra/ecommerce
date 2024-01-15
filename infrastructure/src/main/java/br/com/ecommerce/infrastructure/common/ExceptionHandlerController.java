package br.com.ecommerce.infrastructure.common;

import br.com.ecommerce.domain.exception.EntityException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
class ExceptionHandlerController {
    @ExceptionHandler
    ResponseEntity<Object> entityNotFoundException(EntityException.NotFound ex) {
        return factoryResponseEntityWithResponseEntityException(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    ResponseEntity<Object> responseStatusException(ResponseStatusException ex) {
        var message = ex.getReason();
        return factoryResponseEntityWithResponseEntityException(message, HttpStatus.valueOf(ex.getStatusCode().value()));
    }

    @ExceptionHandler
    ResponseEntity<Object> httpStatusCodeException(HttpStatusCodeException ex) {
        var message = ex.getResponseBodyAsString();
        return factoryResponseEntityWithResponseEntityException(message, HttpStatus.valueOf(ex.getStatusCode().value()));
    }

    @ExceptionHandler
    ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var message = getStringErrors(ex.getBindingResult());
        log.error("MethodArgumentNotValidException: {}", message);
        return factoryResponseEntityWithResponseEntityException(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    ResponseEntity<Object> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        var message = ex.getMessage();
        log.error("HttpRequestMethodNotSupportedException: {}", message);
        return factoryResponseEntityWithResponseEntityException(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    ResponseEntity<Object> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        var message = getInternalException(ex, InvalidFormatException.class)
                .map(Throwable::getMessage)
                .orElse(ex.getMessage());
        log.error("HttpMessageNotReadableException: {}", message);
        return factoryResponseEntityWithResponseEntityException(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    ResponseEntity<Object> missingRequestHeaderException(MissingRequestHeaderException ex) {
        var message = "Header '" + ex.getHeaderName() + "' is required";
        log.error("MissingRequestHeaderException: {}", message);
        return factoryResponseEntityWithResponseEntityException(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    ResponseEntity<Object> missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        var message = "Parameter '" + ex.getParameterName() + "' type '" + ex.getParameterType() + "' is required";
        log.error("MissingRequestHeaderException: {}", message);
        return factoryResponseEntityWithResponseEntityException(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    ResponseEntity<Object> bindException(BindException ex) {
        var message = "Query params errors, " + getStringErrors(ex.getBindingResult());
        log.error("BindException: {}", message);
        return factoryResponseEntityWithResponseEntityException(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    ResponseEntity<Object> constraintViolationException(ConstraintViolationException ex) {
        log.error("BindException: {}", ex.getMessage());
        return factoryResponseEntityWithResponseEntityException(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    ResponseEntity<Object> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        var message = String.format(
                "Failed to convert parameter %s, value %s, to %s",
                ex.getName(),
                ex.getValue(),
                ofNullable(ex.getRequiredType())
                        .map(Class::getSimpleName)
                        .orElse("Unknown")
        );
        log.error("MethodArgumentTypeMismatchException: {}", message);
        return factoryResponseEntityWithResponseEntityException(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    ResponseEntity<Object> assertionError(AssertionError ex) {
        log.error("AssertionError: {}", ex.getMessage(), ex);
        return factoryResponseEntityWithResponseEntityException(getAllMessageFromException(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    ResponseEntity<Object> exception(Exception ex) {
        log.error("Exception: {}", ex.getClass().getName(), ex);
        return factoryResponseEntityWithResponseEntityException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getStringErrors(BindingResult bindingResult) {
        return bindingResult
                .getFieldErrors().stream()
                .map(it -> it.getField() + ": " + it.getDefaultMessage())
                .collect(Collectors.joining(", "));
    }

    private ResponseEntity<Object> factoryResponseEntityWithResponseEntityException(String message, HttpStatus httpStatus) {
        return new ResponseEntity<>(
                ExceptionResponse.builder()
                        .message(message)
                        .status(httpStatus)
                        .build(),
                httpStatus
        );
    }

    private String getAllMessageFromException(Throwable throwable) {
        var messages = new ArrayList<String>(5);
        while (nonNull(throwable)) {
            messages.add(throwable.getClass().getSimpleName() + ": " + throwable.getMessage());
            throwable = throwable.getCause();
        }
        return String.join(" && ", messages);
    }

    private <T> Optional<Throwable> getInternalException(Throwable throwable, Class<T> tClass) {
        do {
            if (tClass.isInstance(throwable)) {
                return Optional.of(throwable);
            }
            throwable = throwable.getCause();
        } while (nonNull(throwable));
        return Optional.empty();
    }
}

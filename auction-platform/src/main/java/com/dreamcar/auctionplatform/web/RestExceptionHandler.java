package com.dreamcar.auctionplatform.web;

import com.dreamcar.auctionplatform.exceptions.EntityNotFoundException;
import com.dreamcar.auctionplatform.exceptions.ErrorInfo;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
public class RestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorInfo handleNotFound(HttpServletRequest req, EntityNotFoundException e) {
        return new ErrorInfo(req.getRequestURL(), e);
    }

    // Validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorInfo handleMethodArgumentNotValid(HttpServletRequest req, MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        String[] details = result.getFieldErrors().stream()
                .map(fe -> {
                    String msg = fe.getDefaultMessage();
                    if (msg != null) {
                        msg = fe.getField() + ": " + msg;
                    }
                    return msg;
                })
                .toArray(String[]::new);
        return new ErrorInfo(req.getRequestURL(), e, details);
    }

    // Database errors
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorInfo handleConflict(HttpServletRequest req, DataIntegrityViolationException e) {
        Throwable cause = RestExceptionHandler.getRootCause(e);
        return new ErrorInfo(req.getRequestURL(), cause);
    }

    // Other errors
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorInfo handleCustomException(HttpServletRequest req, Exception e) {
        return new ErrorInfo(req.getRequestURL(), e);
    }

    private static Throwable getRootCause(Throwable t) {
        Throwable result = t;
        Throwable cause;
        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }
}

package com.ryuProject.tripleProject.handler;

import com.ryuProject.tripleProject.dto.ErrorDTO;
import com.ryuProject.tripleProject.exception.CustomException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

//@ControllerAdvice
@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(value = { CustomException.class })
    @ResponseBody
    protected ErrorDTO fail(RuntimeException ex, WebRequest request) {
        return new ErrorDTO(BAD_REQUEST.value(), ex.getMessage());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(value = { IllegalArgumentException.class })
    @ResponseBody
    protected ErrorDTO handlerIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return new ErrorDTO(BAD_REQUEST.value(), ex.getMessage());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(value = { Exception.class })
    @ResponseBody
    protected ErrorDTO allException(Exception ex, WebRequest request) {
        return new ErrorDTO(BAD_REQUEST.value(), ex.getMessage());
    }
}
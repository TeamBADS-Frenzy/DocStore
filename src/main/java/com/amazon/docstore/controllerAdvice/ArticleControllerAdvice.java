package com.amazon.docstore.controllerAdvice;

import com.amazon.docstore.exception.GenericBadRequestException;
import com.amazon.docstore.exception.ResourceNotFoundException;
import com.amazon.docstore.models.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ArticleControllerAdvice {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse unKnownException(ResourceNotFoundException ex) {
        log.error("Resource not found exception occurred for resource "+ex.getResourceId(), ex);
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Resource not found " + ex.getResourceId());
    }

    @ExceptionHandler(value = {GenericBadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse genericBadRequest(GenericBadRequestException ex) {
        log.error("Exception occurred ",ex);
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {HttpMessageConversionException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse badRequest(HttpMessageConversionException ex) {
        log.error("HttpMessageConversionException occurred ",ex);
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Request json provided cannot be parsed");
    }
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse badRequest(Exception ex) {
        log.error("Exception occurred ",ex);
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request");
    }

    //Ideally when this is called, something very severe has happened
    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse badRequest(Throwable ex) {
        log.error("Exception occurred ",ex);
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Application error");
    }
}

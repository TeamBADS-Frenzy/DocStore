package com.amazon.docstore.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GenericBadRequestException extends RuntimeException{
    private String message;
}

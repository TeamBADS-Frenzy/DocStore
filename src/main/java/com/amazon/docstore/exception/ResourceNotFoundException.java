package com.amazon.docstore.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResourceNotFoundException extends RuntimeException {
    private String resourceId;
    private String message;

    public ResourceNotFoundException(final String message) {
        this.message = message;
    }
}

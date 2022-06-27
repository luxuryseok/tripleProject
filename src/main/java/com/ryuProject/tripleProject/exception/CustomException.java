package com.ryuProject.tripleProject.exception;

import com.ryuProject.tripleProject.enums.ErrorMessage;

public class CustomException extends RuntimeException {
    public CustomException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}


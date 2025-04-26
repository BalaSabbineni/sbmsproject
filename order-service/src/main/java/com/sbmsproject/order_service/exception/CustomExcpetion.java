package com.sbmsproject.order_service.exception;

import lombok.Builder;
import lombok.Data;

public class CustomExcpetion extends RuntimeException{

    private String errorCode;
    private int status;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CustomExcpetion(String message, String errorCode, int status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }
}

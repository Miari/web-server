package com.study.exception;

import com.study.entity.HttpStatus;

public class ServerException extends RuntimeException {

    private HttpStatus httpStatus;

    public ServerException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
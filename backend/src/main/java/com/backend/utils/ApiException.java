package com.backend.utils;

import lombok.Data;
import lombok.Getter;


@Getter
public class ApiException extends RuntimeException {

    protected final int code;

    public ApiException(int code, String mensaje){
        super(mensaje);
        this.code = code;
    }

}

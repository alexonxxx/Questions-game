package com.ballogomezharo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Categoria not found")
public class CategoriaNotFoundException extends RuntimeException{

        public CategoriaNotFoundException(String s) {
            super();
        }
    }



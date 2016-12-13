package com.ballogomezharo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Àlex on 29/10/2016.
 */

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Usuari not found")
    public class UsuariNotFoundException extends RuntimeException {
        public UsuariNotFoundException(String s) {
            super();
        }
    }


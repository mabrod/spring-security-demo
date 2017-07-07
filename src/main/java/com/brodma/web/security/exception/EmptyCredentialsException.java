package com.brodma.web.security.exception;

import org.springframework.security.core.AuthenticationException;

public class EmptyCredentialsException extends AuthenticationException {

    public EmptyCredentialsException(String msg, Throwable t) {
        super(msg, t);
    }

    public EmptyCredentialsException(String msg) {
        super(msg);
    }
}

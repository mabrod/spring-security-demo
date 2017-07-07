package com.brodma.web.security.exception;

public class EmptyPasswordException extends EmptyCredentialsException {

    public EmptyPasswordException(String msg, Throwable t) {
        super(msg, t);
    }

    public EmptyPasswordException(String msg) {
        super(msg);
    }
}

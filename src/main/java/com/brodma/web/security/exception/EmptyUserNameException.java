package com.brodma.web.security.exception;

public class EmptyUserNameException extends EmptyCredentialsException {

    public EmptyUserNameException(String msg, Throwable t) {
        super(msg, t);
    }

    public EmptyUserNameException(String msg) {
        super(msg);
    }

}

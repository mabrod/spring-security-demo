package com.brodma.web.security.service;

public interface LoginAttempts {

    void succeeded(String key);

    void failed(String key);

    boolean reachedLimit(String key);
}

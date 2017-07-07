package com.brodma.web.security.service;

import com.brodma.web.security.config.ApplicationConfig;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class InMemoryLoginAttemptsService implements LoginAttempts {

    private final int maxLoginAttempts;

    private LoadingCache<String, AtomicInteger> loginAttemptsByUserName;

    @Autowired
    public InMemoryLoginAttemptsService(ApplicationConfig applicationConfig) {

        maxLoginAttempts = applicationConfig.getMaxLoginAttempts();
        loginAttemptsByUserName = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.DAYS)
                .build(new CacheLoader<String, AtomicInteger>() {
            @Override
            public AtomicInteger load(String key) throws Exception {
                return new AtomicInteger(0);
            }
        });
    }

    @Override
    public void succeeded(String key) {
        loginAttemptsByUserName.invalidate(key);
    }

    @Override
    public void failed(String key) {

        AtomicInteger attempts = new AtomicInteger(0);
        try {
            attempts = loginAttemptsByUserName.get(key);
        } catch(ExecutionException ee) {
            attempts.set(0);
        }
        attempts.getAndIncrement();
        loginAttemptsByUserName.put(key, attempts);

    }

    @Override
    public boolean reachedLimit(String key) {
        boolean retValue = false;
        try {
            AtomicInteger attempts = loginAttemptsByUserName.get(key);
            if (attempts != null) {
               retValue = attempts.intValue() >= maxLoginAttempts;
            }
        } catch(ExecutionException ee) {
            retValue = false;
        }
        return retValue;
    }
 }

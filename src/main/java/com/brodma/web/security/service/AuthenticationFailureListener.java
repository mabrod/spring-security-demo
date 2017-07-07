package com.brodma.web.security.service;

import com.brodma.web.security.exception.EmptyCredentialsException;
import com.brodma.web.security.exception.EmptyPasswordException;
import com.brodma.web.security.exception.EmptyUserNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private InMemoryLoginAttemptsService loginAttemptService;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) event.getAuthentication();
        if (token != null) {

            final String credentials = (String) token.getCredentials();
            final String username = (String) token.getPrincipal();
            if (StringUtils.isEmpty(credentials) && StringUtils.isEmpty(username)) {
                throw new EmptyCredentialsException(username);
            } else if (StringUtils.isEmpty(credentials)) {
                throw new EmptyPasswordException(credentials);
            } else if (StringUtils.isEmpty(username)) {
                throw new EmptyUserNameException(username);
            }

            loginAttemptService.failed(username);
        }

        AuthenticationException ae = event.getException();
        if (ae != null) {
            throw ae;
        }
    }
}

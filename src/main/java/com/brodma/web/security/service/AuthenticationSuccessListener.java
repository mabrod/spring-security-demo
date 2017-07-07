package com.brodma.web.security.service;

import com.brodma.web.security.domain.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent>{

    @Autowired
    private InMemoryLoginAttemptsService loginAttemptService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {

        AccountDetails accountsDetails = (AccountDetails) event.getAuthentication().getPrincipal();
        if (accountsDetails != null) {
            loginAttemptService.succeeded(accountsDetails.getUsername());
        }
    }
}

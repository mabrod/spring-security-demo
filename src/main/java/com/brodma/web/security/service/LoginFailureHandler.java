package com.brodma.web.security.service;

import com.brodma.web.security.config.WebConfig;
import com.brodma.web.security.exception.EmptyCredentialsException;
import com.brodma.web.security.exception.EmptyPasswordException;
import com.brodma.web.security.exception.EmptyUserNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private WebConfig webConfig;

    private final Map<String, String> failureMessages = new HashMap<String, String>();

    public LoginFailureHandler() {
        failureMessages.put(CredentialsExpiredException.class.getName(), "login.auth.expired");
        failureMessages.put(LockedException.class.getName(), "login.auth.blocked");
        failureMessages.put(AccountExpiredException.class.getName(), "login.auth.expired");
        failureMessages.put(DisabledException.class.getName(), "login.auth.disabled");
        failureMessages.put(EmptyPasswordException.class.getName(), "login.auth.empty.password");
        failureMessages.put(EmptyUserNameException.class.getName(), "login.auth.empty.username");
        failureMessages.put(EmptyCredentialsException.class.getName(), "login.auth.empty.credentials");
        failureMessages.put(BadCredentialsException.class.getName(), "login.auth.badcredentials");
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        setDefaultFailureUrl("/login?error=true");
        String errorMessage = toErrorMessage(toErrorMessageMapping(exception));
        request.getSession().setAttribute("authError", errorMessage);
        super.onAuthenticationFailure(request, response, exception);
    }

    private String toErrorMessageMapping(AuthenticationException exception) {
        String messageMapping = "";
        if (exception instanceof InternalAuthenticationServiceException) {
            // try to get the real cause of exceptional behavior
            Throwable ex = exception.getCause();
            if (ex != null) {
                messageMapping = failureMessages.get(ex.getClass().getName());
            }
        }else {
            messageMapping = failureMessages.get(exception.getClass().getName());
        }
        return messageMapping;
    }

    private String toErrorMessage(String errorMessageMapping) {
        String errorMessage;
        if (StringUtils.isEmpty(errorMessageMapping)) {
            errorMessage = webConfig.messageSource().getMessage("login.auth.badcredentials", null, Locale.getDefault());
        }else {
            try {
                errorMessage = webConfig.messageSource().getMessage(errorMessageMapping, null, Locale.getDefault());
            } catch (NoSuchMessageException nse) {
                errorMessage = webConfig.messageSource().getMessage("login.auth.badcredentials", null, Locale.getDefault());
            }
        }
        // if we are still missing some resource file mappings
        if (StringUtils.isEmpty(errorMessage)) {
            errorMessage = "Invalid login";
        }
        return errorMessage;
    }
}

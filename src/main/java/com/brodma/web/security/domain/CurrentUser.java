package com.brodma.web.security.domain;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import java.lang.annotation.*;

/**
 * Custom annotation to abstract away Spring's AuthenticationPrincipal annotation to avoid any direct Spring Security
 * annotation dependencies in code
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {
}

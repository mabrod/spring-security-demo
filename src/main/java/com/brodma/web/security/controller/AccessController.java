package com.brodma.web.security.controller;

import com.brodma.web.security.domain.AccountDetails;
import com.brodma.web.security.domain.CurrentUser;
import com.brodma.web.security.domain.dto.LoginForm;
import com.brodma.web.security.domain.dto.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class AccessController {

    @ModelAttribute("loginForm")
    public LoginForm loginForm() {
        return new LoginForm();
    }

    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping(value = "normal")
    public ModelAndView normal() {
        return new ModelAndView("normal");
    }

    @GetMapping(value = "about")
    public ModelAndView about() {
        return new ModelAndView("about");
    }

    @GetMapping(value = "logout")
    public ModelAndView logout() {
        return new ModelAndView("logout");
    }

    @GetMapping(value = "protected/superuser")
    public ModelAndView superUser(@CurrentUser AccountDetails accountDetails) {

        if (accountDetails != null) {
            if (accountDetails.hasSuperUserRole()) {
                ModelAndView mv = new ModelAndView("protected/superuser");
                UserDetails ud = new UserDetails(accountDetails.getUsername(), accountDetails.getRoles());
                mv.addObject("userDetails", ud);
                return mv;
            }
        }
        return new ModelAndView("accessDenied");
    }

    @GetMapping(value = "protected/admin")
    public ModelAndView admin(@CurrentUser AccountDetails accountDetails) {
        if (accountDetails != null) {
            if (accountDetails.hasAdminRole() || accountDetails.hasSuperUserRole()) {
                ModelAndView mv = new ModelAndView("protected/admin");
                UserDetails ud = new UserDetails(accountDetails.getUsername(), accountDetails.getRoles());
                mv.addObject("userDetails", ud);
                return mv;
            }
        }
        return new ModelAndView("accessDenied");
    }

    @GetMapping(value = "accessDenied")
    public ModelAndView accessDeniedPage() {
        return new ModelAndView("accessDenied");
    }

    @GetMapping(value = "accessBlocked")
    public ModelAndView accessBlockedPage() {
        return new ModelAndView("accessBlocked");
    }

    @GetMapping(value = "accessExpired")
    public ModelAndView accessExpiredPage() {
        return new ModelAndView("accessExpired");
    }

    @GetMapping(value = "login")
    public ModelAndView showLoginForm(@ModelAttribute("loginForm") LoginForm loginForm) {
        return new ModelAndView("login");
    }
}

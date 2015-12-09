/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.controllers.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.langboost.controllers.file.FileHelper;
import ru.langboost.domain.file.File;
import ru.langboost.domain.user.Roles;
import ru.langboost.domain.user.UserData;
import ru.langboost.security.AuthenticationService;
import ru.langboost.security.DefaultAuthenticationService;
import ru.langboost.security.Credentials;
import ru.langboost.services.registration.DefaultRegistrationService;
import ru.langboost.services.registration.RegistrationService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author bad
 */
@Controller
public class SecurityController {

    @Inject
    private AuthenticationService authenticationService;

    @Inject
    private RegistrationService registrationService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();
        model.addObject("credentials", new Credentials());
        model.setViewName("security/login");
        return model;
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public String signIn(@ModelAttribute("credentials") Credentials credentials) {
        try {
            authenticationService.authenticate(credentials);
        } catch (SecurityException ex) {
            return "redirect:/login";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView model = new ModelAndView();
        model.addObject("roles", Arrays.asList(Roles.values()));
        model.setViewName("security/registration");
        return model;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/login";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String signUp(
            MultipartFile icon,
            Roles role,
            String name,
            String lastname,
            String patronymic,
            String email,
            String password) {
        try {
            UserData userData = createUserData(name, lastname, patronymic, icon);
            Credentials credentials = createCredentials(email, password);
            registrationService.register(credentials, userData, role.name());
        } catch (SecurityException ex) {
            return "redirect:/registration";
        }
        return "redirect:/";
    }

    private Credentials createCredentials(String email, String password) {
        return new Credentials(email, password);
    }

    private UserData createUserData(String name, String lastname, String patronymic, MultipartFile icon) {
        File iconFile = null;
        try {
            iconFile = FileHelper.createFile(icon);
        } catch (IOException ex) {
        }
        return new UserData(name, lastname, patronymic, iconFile);
    }
}
